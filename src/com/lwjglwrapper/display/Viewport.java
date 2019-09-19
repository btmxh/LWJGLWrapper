/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.display;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class Viewport {
    private int x, y, width, height;

    public Viewport(Window window) {
        this(0, 0, window.getWidth(), window.getHeight());
    }

    public Viewport(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void set(Window window) {
        window.setViewport(this);
    }
    
    
    
    void updateViewport() {
        GL11.glViewport(x, y, width, height);
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
    
}
