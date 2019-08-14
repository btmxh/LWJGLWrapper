/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl;

import com.lwjglwrapper.utils.IColor;
import java.util.stream.IntStream;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Welcome
 */
public class GLCalls {
    
    public static void setClearColor(IColor color) {
        GL11.glClearColor(color.r, color.g, color.b, color.a);
    }
    
    public static void clear(int mask) {
        GL11.glClear(mask);
    }
    
    public static void enable(int... mask) {
        IntStream.of(mask).forEach(GL11::glEnable);
    }
    
    public static void disable(int... mask) {
        IntStream.of(mask).forEach(GL11::glDisable);
    }
    
}
