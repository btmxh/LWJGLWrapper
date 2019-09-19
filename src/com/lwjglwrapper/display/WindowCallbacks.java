/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.LWJGL;
import java.util.Arrays;
import org.liquidengine.cbchain.impl.*;
import org.lwjgl.glfw.GLFWDropCallback;
import org.lwjgl.glfw.GLFWDropCallbackI;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.glfw.GLFWMonitorCallbackI;
import org.lwjgl.glfw.GLFWWindowCloseCallbackI;
import org.lwjgl.glfw.GLFWWindowContentScaleCallbackI;
import org.lwjgl.glfw.GLFWWindowFocusCallbackI;
import org.lwjgl.glfw.GLFWWindowIconifyCallbackI;
import org.lwjgl.glfw.GLFWWindowMaximizeCallbackI;
import org.lwjgl.glfw.GLFWWindowPosCallbackI;
import org.lwjgl.glfw.GLFWWindowRefreshCallbackI;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;

/**
 *
 * @author Welcome
 */
public class WindowCallbacks {

    public static final int WINDOW_ICONIFIED = 0, WINDOW_FOCUSED = 1, WINDOW_MAXIMIZED = 2;

    ChainDropCallback drop;
    ChainErrorCallback error;
    ChainFramebufferSizeCallback framebuffer;
    ChainMonitorCallback monitor;
    ChainWindowCloseCallback windowClose;
    ChainWindowContentScaleCallback windowContentScale;
    ChainWindowFocusCallback windowFocus;
    ChainWindowIconifyCallback windowIconify;
    ChainWindowMaximizeCallback windowMaximize;
    ChainWindowPosCallback position;
    ChainWindowRefreshCallback refresh;
    ChainWindowSizeCallback size;

    public WindowCallbacks() {
        this.drop = new ChainDropCallback();
        drop.add((winID, count, names) -> {
            String[] paths = new String[count];
            for (int i = 0; i < count; i++) {
                paths[i] = GLFWDropCallback.getName(names, i);
            }

            dropFiles(LWJGL.allWindows.get(winID), paths);
        });
        this.error = new ChainErrorCallback();
        error.add((error, description) -> error(error, GLFWErrorCallback.getDescription(description)));
        this.framebuffer = new ChainFramebufferSizeCallback();
        framebuffer.add((winID, width, height) -> framebufferSizeChanged(LWJGL.allWindows.get(winID), width, height));
        this.monitor = new ChainMonitorCallback();
        monitor.add((monitor, event) -> monitorChanged(monitor, event));
        this.windowClose = new ChainWindowCloseCallback();
        windowClose.add((winID) -> windowOnClose(LWJGL.allWindows.get(winID)));
        this.windowContentScale = new ChainWindowContentScaleCallback();
        windowContentScale.add((winID, xscale, yscale) -> windowContentScale(LWJGL.allWindows.get(winID), xscale, yscale));
        this.windowFocus = new ChainWindowFocusCallback();
        windowFocus.add((winID, focused) -> windowFocus(LWJGL.allWindows.get(winID), focused));
        this.windowIconify = new ChainWindowIconifyCallback();
        windowIconify.add((winID, iconified) -> windowIconified(LWJGL.allWindows.get(winID), iconified));
        this.windowMaximize = new ChainWindowMaximizeCallback();
        windowMaximize.add((winID, maximized) -> windowMaximized(LWJGL.allWindows.get(winID), maximized));
        this.position = new ChainWindowPosCallback();
        position.add((winID, x, y) -> windowPosition(LWJGL.allWindows.get(winID), x, y));
        this.refresh = new ChainWindowRefreshCallback();
        refresh.add((winID) -> refreshWindow(LWJGL.allWindows.get(winID)));
        this.size = new ChainWindowSizeCallback();
        size.add((winID, width, height) -> windowSize(LWJGL.allWindows.get(winID), width, height));

    }

    public void dropFiles(Window window, String[] paths) {
//        System.out.println(Arrays.toString(paths));
    }

    public void error(int error, String description) {
    }

    public void framebufferSizeChanged(Window window,
            int width, int height) {
    }

    public void monitorChanged(long monitor, int event) {
    }

    public void windowOnClose(Window window) {
    }

    public void windowContentScale(Window window, float xscale, float yscale) {
        window.setContentScale(xscale, yscale);
    }

    public void windowFocus(Window window, boolean focused) {
    }

    public void windowIconified(Window window,
            boolean iconified) {
    }

    public void windowMaximized(Window window,
            boolean maximized) {
    }

    public void windowPosition(Window window, int x, int y) {
    }

    public void refreshWindow(Window window) {
    }

    public void windowSize(Window window, int width, int height) {
        if (window != null) {
            window.setSizeVariables(width, height);
        }
    }

}
