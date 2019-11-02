/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.colors;

import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.utils.floats.GLFloat;
import com.lwjglwrapper.utils.math.MathUtils;

/**
 *
 * @author Welcome
 */
public class VaryColor extends AbstractColor{
    private GLFloat r, g, b, a;

    public VaryColor(GLFloat r, GLFloat g, GLFloat b, GLFloat a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public VaryColor(GLFloat r, GLFloat g, GLFloat b) {
        this(r, g, b, ()->1f);
    }
    
    public VaryColor(GLFloat gray, GLFloat a) {
        this(gray, gray, gray, a);
    }
    
    public VaryColor(GLFloat gray) {
        this(gray, ()->1f);
    }

    @Override
    public float getRed() {
        return r.get();
    }

    @Override
    public float getGreen() {
        return g.get();
    }

    @Override
    public float getBlue() {
        return b.get();
    }

    @Override
    public float getAlpha() {
        return a.get();
    }
    
    public static VaryColor fromIColor(StaticColor color) {
        return new VaryColor(()->color.r, ()->color.g, ()->color.b, ()->color.a);
    }

    @Override
    public VaryColor mulAlpha(float alpha) {
        return new VaryColor(r, g, b, GLFloat.mul(a, ()->alpha));
    }

    @Override
    public VaryColor vary() {
        return this;
    }
}
