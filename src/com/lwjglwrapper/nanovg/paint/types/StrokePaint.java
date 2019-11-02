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
public interface StrokePaint {
    public void stroke(long nvgID);
    public StrokePaint smulAlpha(float alpha);
}
