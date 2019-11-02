/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.LWJGL;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.liquidengine.cbchain.impl.ChainCursorEnterCallback;
import org.liquidengine.cbchain.impl.ChainCursorPosCallback;
import org.liquidengine.cbchain.impl.ChainMouseButtonCallback;
import org.liquidengine.cbchain.impl.ChainScrollCallback;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class Mouse {

    private ChainCursorEnterCallback enterCallback;
    private ChainCursorPosCallback mousePositionCallback;
    private ChainMouseButtonCallback buttonCallback;
    private ChainScrollCallback scrollCallback;

    public Mouse() {
        this.scrollCallback = new ChainScrollCallback();
        scrollCallback.add((winID, xOff, yOff) -> scroll(LWJGL.allWindows.get(winID), xOff, yOff));
        this.buttonCallback = new ChainMouseButtonCallback();
        buttonCallback.add((winID, button, action, mods) -> mouseButton(LWJGL.allWindows.get(winID), button, action, mods));
        this.mousePositionCallback = new ChainCursorPosCallback();
        mousePositionCallback.add((winID, xPos, yPos) -> mousePosition(LWJGL.allWindows.get(winID), xPos, yPos));
        this.enterCallback = new ChainCursorEnterCallback();
        enterCallback.add((winID, entered) -> cursorEnter(LWJGL.allWindows.get(winID), entered));
        
        mousePos = new Vector2i();
        lastFrameMousePos = new Vector2i();
        
        buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
        lastFrameButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    }

    public void cursorEnter(Window window, boolean entered) {}
    public void mousePosition(Window window, double x, double y) {
        mousePos = new Vector2i((int) x, (int) y);
    }
    public void mouseButton(Window window, int button, int action, int mods) {
        buttons[button] = action != GLFW.GLFW_RELEASE;
    }
    public void scroll(Window window, double xOff, double yOff) {
        scroll = new Vector2d(xOff, yOff);
    }
    
    private Vector2i mousePos, lastFrameMousePos;
    private boolean[] buttons, lastFrameButtons;
    private Vector2d scroll, lastFrameScroll;
    
    public void storeLastFrame() {
        lastFrameMousePos = new Vector2i(mousePos);
        lastFrameButtons = Arrays.copyOf(buttons, buttons.length);
        if(scroll == null)  scroll = new Vector2d();
        lastFrameScroll = new Vector2d(scroll);
    }
    
    public boolean mouseDown(int button)     {  return buttons[button]; }
    public boolean mousePressed(int button)  {  return buttons[button] && !lastFrameButtons[button]; }
    public boolean mouseReleased(int button) {  return !buttons[button] && lastFrameButtons[button]; }
    
    public void lockButton(int button) {   buttons[button] = false;    }
    
    public boolean anyMouseDown(int... buttons)     {  return IntStream.of(buttons).anyMatch(this::mouseDown); }
    public boolean anyMousePressed(int... buttons)  {  return IntStream.of(buttons).anyMatch(this::mousePressed); }
    public boolean anyMouseReleased(int... buttons) {  return IntStream.of(buttons).anyMatch(this::mouseReleased); }
    
    public boolean allMouseDown(int... buttons)     {  return IntStream.of(buttons).allMatch(this::mouseDown); }
    public boolean allMousePressed(int... buttons)  {  return IntStream.of(buttons).allMatch(this::mousePressed); }
    public boolean allMouseReleased(int... buttons) {  return IntStream.of(buttons).allMatch(this::mouseReleased); }
    
    public boolean anyMouseDown(Collection<Integer> buttons)     {  return buttons.stream().anyMatch(this::mouseDown); }
    public boolean anyMousePressed(Collection<Integer> buttons)  {  return buttons.stream().anyMatch(this::mousePressed); }
    public boolean anyMouseReleased(Collection<Integer> buttons) {  return buttons.stream().anyMatch(this::mouseReleased); }
    
    public boolean allMouseDown(Collection<Integer> buttons)     {  return buttons.stream().allMatch(this::mouseDown); }
    public boolean allMousePressed(Collection<Integer> buttons)  {  return buttons.stream().allMatch(this::mousePressed); }
    public boolean allMouseReleased(Collection<Integer> buttons) {  return buttons.stream().allMatch(this::mouseReleased); }
    
    public int getCursorX() { return mousePos.x;  }
    public int getCursorY() { return mousePos.y;  }
    public float getDeltaX() {  return (float) ((mousePos.x - lastFrameMousePos.x) * LWJGL.currentLoop.getDeltaTime());   }
    public float getDeltaY() {  return (float) ((mousePos.y - lastFrameMousePos.y) * LWJGL.currentLoop.getDeltaTime());   }

    public Vector2f getCursorPosition() {
        return new Vector2f(mousePos);
    }

    public Vector2d getDeltaScroll() {
        return new Vector2d(scroll).sub(lastFrameScroll).mul(LWJGL.currentLoop.getDeltaTime());
    }

    /**
     * @return the enterCallback
     */
    public ChainCursorEnterCallback getEnterCallback() {
        return enterCallback;
    }

    /**
     * @return the mousePositionCallback
     */
    public ChainCursorPosCallback getMousePositionCallback() {
        return mousePositionCallback;
    }

    /**
     * @return the buttonCallback
     */
    public ChainMouseButtonCallback getButtonCallback() {
        return buttonCallback;
    }

    /**
     * @return the scrollCallback
     */
    public ChainScrollCallback getScrollCallback() {
        return scrollCallback;
    }
    
}
