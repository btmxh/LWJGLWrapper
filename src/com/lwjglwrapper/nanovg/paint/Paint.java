/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint;

import com.lwjglwrapper.nanovg.paint.types.TextPaint;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.nanovg.paint.types.StrokePaint;

/**
 *
 * @author Welcome
 */
public abstract class Paint implements FillPaint, StrokePaint, TextPaint{
    public abstract Paint mulAlpha(float alpha);

    @Override
    public FillPaint fmulAlpha(float alpha) {
        return mulAlpha(alpha);
    }

    @Override
    public StrokePaint smulAlpha(float alpha) {
        return mulAlpha(alpha);
    }

    @Override
    public TextPaint tmulAlpha(float alpha) {
        return mulAlpha(alpha);
    }
    
    
    
    
}
