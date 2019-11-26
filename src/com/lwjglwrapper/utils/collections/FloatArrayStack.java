/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.collections;

/**
 * This class may cause a lot of performance issues
 * @author Welcome
 */
@Deprecated
public class FloatArrayStack {
    private float[] array;
    private int pointer;

    public FloatArrayStack(int len) {
        array = new float[len];
        pointer = 0;
    }
    
    public int size() {
        return array.length;
    }
    
    public void put(float... floats) {
        for (float f : floats) {
            array[pointer++] = f;
        }
    }
    
    public float[] getArray() {
        return array;
    }
    
}
