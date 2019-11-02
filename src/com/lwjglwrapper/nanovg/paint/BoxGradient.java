/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.colors.VaryColor;
import org.joml.Rectanglef;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class BoxGradient extends GradientPaint{
    private long nanoVGID;
    private float x, y, w, h, r, f;

    public BoxGradient(long nanoVGID, float x, float y, float w, float h, float r, float f,
            AbstractColor icolor, AbstractColor ocolor) {
        super(NanoVG.nvgBoxGradient(nanoVGID, x, y, w, h, r, f, icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create()), icolor, ocolor);
        this.nanoVGID = nanoVGID;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
        this.f = f;
    }
    
    
    public void setBounds(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        resetPaint();
    }

    public void setRadius(float r) {
        this.r = r;
        resetPaint();
    }

    public void setFeather(float f) {
        this.f = f;
        resetPaint();
    }

    @Override
    public void resetPaint() {
        nativePaint = NanoVG.nvgBoxGradient(nanoVGID, x, y, w, h, r, f, icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create());
    }

    @Override
    public BoxGradient mulAlpha(float alpha) {
        return new BoxGradient(nanoVGID, x, y, w, h, r, f, icolor.alpha(alpha), ocolor.alpha(alpha));
    }
}
