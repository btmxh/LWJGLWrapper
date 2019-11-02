/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import com.lwjglwrapper.nanovg.NVGGraphics;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class Viewport {
    private int x, y, width, height;
    private boolean scissor;

    public Viewport(Window window) {
        this(window, false);
    }

    public Viewport(int x, int y, int width, int height) {
        this(x, y, width, height, false);
    }
    
    public Viewport(Window window, boolean scissor) {
        this(0, 0, window.getWidth(), window.getHeight(), scissor);
    }

    public Viewport(int x, int y, int width, int height, boolean scissor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scissor = scissor;
    }
    
    public void set(Window window) {
        window.setViewport(this);
    }
    
    void updateViewport() {
        GL11.glViewport(x, y, width, height);
        if(scissor) updateScissor();
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    public void setScissor(boolean scissor) {
        this.scissor = scissor;
    }

    public boolean useScissor() {
        return scissor;
    }

    public void updateScissor(NVGGraphics g) {
        g.setScissorArea(x, y, width, height);
    }
    
    public void updateScissor() {
        GL11.glScissor(x, y, width, height);
    }
    
}
