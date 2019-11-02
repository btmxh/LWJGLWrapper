/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.NVGImage;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public class ImagePaint extends NanoVGPaint{
    
    private long nanoVGID;
    private float sx, sy, ex, ey;
    private NVGImage image;
    private float angle, alpha;
    
    
    public ImagePaint(long nanoVGID, float sx, float sy, float ex, float ey, NVGImage image, float angle, float alpha) {
        super(NanoVG.nvgImagePattern(nanoVGID, sx, sy, ex, ey, angle, image.getID(), alpha, NVGPaint.create()));
        this.nanoVGID = nanoVGID;
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        this.image = image;
        this.angle = angle;
        this.alpha = alpha;
    }
    

    public void setBounds(float sx, float sy, float ex, float ey) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        resetPaint();
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(NVGImage image) {
        this.image = image;
        resetPaint();
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(float angle) {
        this.angle = angle;
        resetPaint();
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        resetPaint();
    }
    
    

    protected void resetPaint() {
        nativePaint = NanoVG.nvgImagePattern(nanoVGID, sx, sy, ex, ey, angle, image.getID(), alpha, NVGPaint.create());
    }

    @Override
    public ImagePaint mulAlpha(float alpha) {
        return new ImagePaint(nanoVGID, sx, sy, ex, ey, image, angle, this.alpha * alpha);
    }
    
}
