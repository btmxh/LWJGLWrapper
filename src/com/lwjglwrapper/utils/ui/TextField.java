/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.utils.math.MathUtils;
import static com.lwjglwrapper.utils.ui.Button.NORMAL;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class TextField extends CharComponent{
    
    public static final int UNSELECTED = 0, UNSELECTED_HOVERING = 1, CLICKED = 2, SELECTED = 3;
    
    protected StringBuilder text;
    protected boolean selected = false;
    protected long l, lastBackspace;
    
    private TextOnChangeListener textOnChangeListener = (s, i) -> {};
    
    public TextField(Stage stage, boolean autoAdd) {
        super(stage, autoAdd, 4);
        text = new StringBuilder();
    }

    @Override
    public void tick() {
        super.tick();
        Vector2f translatedCursorPosition = stage.window.getMouse().getCursorPosition().sub(offset);
        if(!isVisible())    return;
        if(MathUtils.contains(getCurrentShape().getShape().boundBox(), translatedCursorPosition)) {
            stage.window.setStandardCursor(GLFW.GLFW_IBEAM_CURSOR);
        } else {
            stage.window.setStandardCursor(GLFW.GLFW_ARROW_CURSOR);
        }
        if (selected) {
            setMode(SELECTED);
            if (stage.window.getMouse().mouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)
                    && !MathUtils.contains(getCurrentShape().getShape().boundBox(), translatedCursorPosition)) {
                selected = false;
                setMode(UNSELECTED);
            }
            if(stage.window.getKeyboard().keyPressed(GLFW.GLFW_KEY_ENTER)) {
                text.append("\n");
                textOnChangeListener.textOnChange("\n", TextOnChangeListener.ADD);
            }
            if (stage.window.getKeyboard().keyPressed(GLFW.GLFW_KEY_BACKSPACE) && text.length() != 0) {
                char delete = text.charAt(text.length() - 1);
                text.deleteCharAt(text.length() - 1);
                lastBackspace = System.currentTimeMillis();
                l = 500;
                textOnChangeListener.textOnChange("" + delete, TextOnChangeListener.DELETE);
            }
            if (stage.window.getKeyboard().keyReleased(GLFW.GLFW_KEY_BACKSPACE)) {
                lastBackspace = -1;
                l = 500;
            }
            if (stage.window.getKeyboard().keyDown(GLFW.GLFW_KEY_BACKSPACE)) {
                long time = (System.currentTimeMillis() - lastBackspace) - l;
                if (time > 0 && text.length() != 0) {
                    char delete = text.charAt(text.length() - 1);
                    text.deleteCharAt(text.length() - 1);
                    lastBackspace = System.currentTimeMillis();
                    l = 50;
                    textOnChangeListener.textOnChange("" + delete, TextOnChangeListener.DELETE);
                }
            }
        } else if (MathUtils.contains(getCurrentShape().getShape().boundBox(), translatedCursorPosition)) {
            if (stage.window.getMouse().mouseDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                setMode(CLICKED);
            } else {
                setMode(UNSELECTED_HOVERING);
            }
            if (stage.window.getMouse().mouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                selected = true;
            }
        } else {
            setMode(UNSELECTED);
        }
    }
    
    public String getText() {
        return text.toString();
    }
    
    public void setText(String s) {
        text.delete(0, text.length());
        text.append(s);
    }

    @Override
    public void charEntered(int codePoint) {
        if(selected) {
            text.append((char) codePoint);
            textOnChangeListener.textOnChange((char) codePoint + "", TextOnChangeListener.ADD);
        }
    }
    
    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
    }

    public void setTextOnChangeListener(TextOnChangeListener listener) {
        this.textOnChangeListener = listener;
    }
    
    public static interface TextOnChangeListener {
        public static final int DELETE = 0, ADD = 1;
        public void textOnChange(String change, int changeType);
    }
}
