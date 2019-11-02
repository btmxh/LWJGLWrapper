/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.utils.floats.GLFloat;
import org.joml.Rectanglef;

/**
 *
 * @author Welcome
 */
public class GLRect {
    private GLFloat x, y, width, height;

    public GLRect(GLFloat x, GLFloat y, GLFloat w, GLFloat h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    /**
     * @return the x
     */
    public GLFloat getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(GLFloat x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public GLFloat getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(GLFloat y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public GLFloat getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(GLFloat width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public GLFloat getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(GLFloat height) {
        this.height = height;
    }

    public Rectanglef getJOMLRect() {
        return Rect.jomlRect(x.get(), y.get(), width.get(), height.get());
    }
    
    public static GLRect fromJOML(Rectanglef rect) {
        return new GLRect(GLFloat.memValue(rect.minX), GLFloat.memValue(rect.minY), GLFloat.memValue(rect.maxX - rect.minX), GLFloat.memValue(rect.maxY - rect.minY));
    }

    public GLFloat centerX() {
        return () -> x.get() + width.get() / 2;
    }

    public GLFloat centerY() {
        return () -> y.get() + height.get() / 2;
    }

    public GLFloat getMaxX() {
        return GLFloat.add(x, width);
    }
    
    public GLFloat getMaxY() {
        return GLFloat.add(y, height);
    }
    
}
