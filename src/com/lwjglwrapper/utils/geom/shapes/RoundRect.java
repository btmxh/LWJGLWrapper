/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.floats.GLFloat;

/**
 *
 * @author Welcome
 */
public class RoundRect extends Rect {

    private GLFloat topLeft, topRight, bottomRight, bottomLeft;

    public RoundRect(GLRect rect, GLFloat radius) {
        this(rect, rect, radius);
    }

    public RoundRect(GLRect rect, GLFloat topLeft, GLFloat topRight,
            GLFloat bottomRight, GLFloat bottomLeft) {
        this(rect, rect, topLeft, topRight, bottomRight, bottomLeft);
    }
    
    public RoundRect(GLRect rect, GLRect bounds, GLFloat radius) {
        this(rect, bounds, radius, radius, radius, radius);
    }

    public RoundRect(GLRect rect, GLRect bounds, GLFloat topLeft,
            GLFloat topRight, GLFloat bottomRight, GLFloat bottomLeft) {
        super(rect, bounds);
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    public RoundRect(GLFloat x, GLFloat y, GLFloat w, GLFloat h, GLFloat radius) {
        this(new GLRect(x, y, w, h), radius);
    }

    public RoundRect(GLFloat x, GLFloat y, GLFloat w, GLFloat h, GLFloat topLeft,
            GLFloat topRight, GLFloat bottomRight, GLFloat bottomLeft) {
        this(new GLRect(x, y, w, h), topLeft, topRight, bottomRight, bottomLeft);
    }

    public RoundRect(GLFloat x, GLFloat y, GLFloat w, GLFloat h, GLFloat bx, GLFloat by,
            GLFloat bw, GLFloat bh, GLFloat radius) {
        this(new GLRect(x, y, w, h), new GLRect(bx, by, bw, bh), radius);
    }

    public RoundRect(GLFloat x, GLFloat y, GLFloat w, GLFloat h, GLFloat bx, GLFloat by,
            GLFloat bw, GLFloat bh, GLFloat topLeft, GLFloat topRight, GLFloat bottomRight,
            GLFloat bottomLeft) {
        this(new GLRect(x, y, w, h), new GLRect(bx, by, bw, bh), 
                topLeft, topRight, bottomRight, bottomLeft);
    }

    

    @Override
    public void render(NVGGraphics g) {
        g.roundRect(rect.getX().get(), rect.getY().get(), rect.getWidth().get(), rect.getHeight().get(),
                topLeft.get(), topRight.get(), bottomRight.get(), bottomLeft.get());
    }

    /**
     * @return the topLeft
     */
    public GLFloat getTopLeftRadius() {
        return topLeft;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeftRadius(GLFloat topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * @return the topRight
     */
    public GLFloat getTopRightRadius() {
        return topRight;
    }

    /**
     * @param topRight the topRight to set
     */
    public void setTopRightRadius(GLFloat topRight) {
        this.topRight = topRight;
    }

    /**
     * @return the bottomRight
     */
    public GLFloat getBottomRightRadius() {
        return bottomRight;
    }

    /**
     * @param bottomRight the bottomRight to set
     */
    public void setBottomRightRadius(GLFloat bottomRight) {
        this.bottomRight = bottomRight;
    }

    /**
     * @return the bottomLeft
     */
    public GLFloat getBottomLeftRadius() {
        return bottomLeft;
    }

    /**
     * @param bottomLeft the bottomLeft to set
     */
    public void setBottomLeftRadius(GLFloat bottomLeft) {
        this.bottomLeft = bottomLeft;
    }
    
    
}
