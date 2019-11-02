/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.colors;

import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.utils.floats.WrappedFloat;
import java.awt.Color;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.assimp.AIColor4D;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

/**
 *
 * @author Welcome
 */
public abstract class AbstractColor extends Paint{
    
    public NVGColor toNanoVGColor() {
        return NanoVG.nvgRGBAf(getRed(), getGreen(), getBlue(), getAlpha(), NVGColor.create());
    }
    
    public Color toAWTColor() {
        return new Color(getRed(), getGreen(), getBlue(), getAlpha());
    }
    
    public Vector3f to3DVector() {
        return new Vector3f(getRed(), getGreen(), getBlue());
    }
    
    public Vector4f to4DVector() {
        return new Vector4f(getRed(), getGreen(), getBlue(), getAlpha());
    }
    
    @Override
    public void fill(long nvgID) {
        NanoVG.nvgFillColor(nvgID, toNanoVGColor());
        NanoVG.nvgFill(nvgID);
    }

    @Override
    public void stroke(long nvgID) {
        NanoVG.nvgStrokeColor(nvgID, toNanoVGColor());
        NanoVG.nvgStroke(nvgID);
    }

    @Override
    public void text(long nvgID) {
        NanoVG.nvgFillColor(nvgID, toNanoVGColor());
    }
    
    public float[] hsv() {
        Color awtColor = toAWTColor();
        return Color.RGBtoHSB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), new float[3]);
    }

    public StaticColor alpha(float a) {
        return new StaticColor(getRed(), getGreen(), getBlue(), a);
    }

    @Override
    public String toString() {
        return "(" + getRed() + ", " + getGreen() + ", " + getBlue() + ", " + getAlpha() + ")";
    }
    
    public StaticColor scale(float factor) {
        return new StaticColor(getRed() * factor, getGreen() * factor, getBlue() * factor, getAlpha());
    }

    public StaticColor tint(float tint) {
        return new StaticColor(getRed() + tint, getGreen() + tint, getBlue() + tint, getAlpha());
    }
    
    public abstract float getRed();
    public abstract float getGreen();
    public abstract float getBlue();
    public abstract float getAlpha();
    
    public VaryColor vary() {
        AbstractColor t = this;
        return new VaryColor(t::getRed, t::getGreen, t::getBlue, new WrappedFloat(getAlpha()));
    }
    
    
}
