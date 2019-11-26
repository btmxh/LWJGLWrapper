/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.floats;

/**
 * A object storing a float memValue.
 * @author Welcome
 */
public interface GLFloat {

    public float get();
    
    public default GLFloat neg() {
        return () -> -get();
    }
    
    public static GLFloat memValue(float value) {
        return () -> value;
    }
    
    public static int hash(GLFloat value) {
        return Float.hashCode(value.get());
    }

    public static String string(GLFloat value) {
        return String.valueOf(value.get());
    }
    
    public static GLFloat memAdd(GLFloat f1, GLFloat f2) {
        final float val = f1.get() + f2.get();
        return () -> val;
    }
    
    public static GLFloat add(GLFloat f1, GLFloat f2) {
        return () -> f1.get() + f2.get();
    }
    
    public static GLFloat memSub(GLFloat f1, GLFloat f2) {
        final float val = f1.get() - f2.get();
        return () -> val;
    }
    
    public static GLFloat sub(GLFloat f1, GLFloat f2) {
        return () -> f1.get() - f2.get();
    }
    
    public static GLFloat memMul(GLFloat f1, GLFloat f2) {
        final float val = f1.get() * f2.get();
        return () -> val;
    }
    
    public static GLFloat mul(GLFloat f1, GLFloat f2) {
        return () -> f1.get() * f2.get();
    }
    
    public static GLFloat memDiv(GLFloat f1, GLFloat f2) {
        final float val = f1.get() / f2.get();
        return () -> val;
    }
    
    public static GLFloat div(GLFloat f1, GLFloat f2) {
        return () -> f1.get() / f2.get();
    }
    
    
}
