/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg.paint.types;

/**
 *
 * @author Welcome
 */
public interface TextPaint {
    public void text(long nvgID);
    public TextPaint tmulAlpha(float alpha);
}
