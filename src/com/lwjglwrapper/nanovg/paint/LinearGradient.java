/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.colors.VaryColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class LinearGradient extends GradientPaint{
    private long nanoVGID;
    private float sx, sy;
    private float ex, ey;

    public LinearGradient(long nanoVGID, float sx, float sy, float ex, float ey,
            AbstractColor icolor, AbstractColor ocolor) {
        super(NanoVG.nvgLinearGradient(nanoVGID, sx, sy, ex, ey, icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create()), icolor, ocolor);
        this.nanoVGID = nanoVGID;
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
    }
    
    public void setBounds(float sx, float sy, float ex, float ey) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        resetPaint();
    }

    @Override
    public void resetPaint() {
        nativePaint = NanoVG.nvgLinearGradient(nanoVGID, sx, sy, ex, ey, icolor.toNanoVGColor(), ocolor.toNanoVGColor(), NVGPaint.create());
    }

    @Override
    public LinearGradient mulAlpha(float alpha) {
        return new LinearGradient(nanoVGID, sx, sy, ex, ey, icolor.alpha(alpha), ocolor.alpha(alpha));
    }
}
