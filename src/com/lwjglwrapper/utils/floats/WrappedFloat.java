/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.floats;

/**
 *
 * @author Welcome
 */
public class WrappedFloat implements GLFloat{
    
    private float f;

    public WrappedFloat(float f) {
        this.f = f;
    }

    public void set(float f) {
        this.f = f;
    }
    
    @Override
    public float get() {
        return f;
    }
    
}
