/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.colors.VaryColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class RadialGradient extends GradientPaint{
    private long nanoVGID;
    private float cx, cy;
    private float ir, or;

    public RadialGradient(long nanoVGID, float cx, float cy, float ir, float or,
            AbstractColor icolor, AbstractColor ocolor) {
        super(NanoVG.nvgRadialGradient(nanoVGID, cx, cy, ir, or, icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create()), icolor, ocolor);
        this.nanoVGID = nanoVGID;
        this.cx = cx;
        this.cy = cy;
        this.ir = ir;
        this.or = or;
    }
    
    public void setCenter(float x, float y) {
        cx = x; cy = y;
        resetPaint();
    }
    
    public void setRadius(float inner, float outer) {
        ir = inner; or = outer;
        resetPaint();
    }

    @Override
    public void resetPaint() {
        nativePaint = NanoVG.nvgRadialGradient(nanoVGID, cx, cy, ir, or,
                icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create());
    }

    @Override
    public RadialGradient mulAlpha(float alpha) {
        return new RadialGradient(nanoVGID, cx, cy, ir, or, icolor.alpha(alpha), ocolor.alpha(alpha));
    }
    
}
