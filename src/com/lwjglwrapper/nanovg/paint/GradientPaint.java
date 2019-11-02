/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.colors.StaticColor;
import com.lwjglwrapper.utils.colors.VaryColor;
import org.lwjgl.nanovg.NVGPaint;

/**
 *
 * @author Welcome
 */
public abstract class GradientPaint extends NanoVGPaint{
    protected AbstractColor icolor;
    protected AbstractColor ocolor;

    public GradientPaint(NVGPaint paint, AbstractColor icolor, AbstractColor ocolor) {
        super(paint);
        this.icolor = icolor;
        this.ocolor = ocolor;
    }
    
    
    
    public void setColor(AbstractColor icolor, AbstractColor ocolor) {
        this.icolor = icolor;
        this.ocolor = ocolor;
        resetPaint();
    }


    /**
     * @return the icolor
     */
    public AbstractColor getIColor() {
        return icolor;
    }

    /**
     * @return the ocolor
     */
    public AbstractColor getOColor() {
        return ocolor;
    }
    
}
