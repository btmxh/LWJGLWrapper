/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.floats;

import com.lwjglwrapper.display.Viewport;
import com.lwjglwrapper.display.Window;

/**
 * A utility class to generate GLFloat
 * @author Welcome
 */
public class GLFloats {
    private static Window currentWindow;
    private static Viewport currentViewport;
    
    public static final GLFloat ZERO = () -> 0, ONE = () -> 1;
    
    public static void setWindow(Window window) {
        currentWindow = window;
    }
    
    public static void setViewport(Viewport viewport) {
        currentViewport = viewport;
    }
    
    public static GLFloat w_fromFarYAxis(GLFloat distance) {
        return () -> currentWindow.getWidth() - distance.get();
    }
    
    public static GLFloat w_fromFarXAxis(GLFloat distance) {
        return () -> currentWindow.getHeight() - distance.get();
    }
    
    public static GLFloat v_fromFarYAxis(GLFloat distance) {
        return () -> currentViewport.getWidth() - distance.get();
    }
    
    public static GLFloat v_fromFarXAxis(GLFloat distance) {
        return () -> currentViewport.getHeight() - distance.get();
    }
    
    public static GLFloat w_fracOfWidth(GLFloat fraction) {
        return () -> currentWindow.getWidth() * fraction.get();
    }
    public static GLFloat w_fracOfHeight(GLFloat fraction) {
        return () -> currentWindow.getHeight() * fraction.get();
    }
    
    public static GLFloat v_fracOfWidth(GLFloat fraction) {
        return () -> currentViewport.getWidth() * fraction.get();
    }
    public static GLFloat v_fracOfHeight(GLFloat fraction) {
        return () -> currentViewport.getHeight() * fraction.get();
    }
    
    
    static float v1 = 100, v2 = 20;
    public static void main(String[] args) {
        GLFloat f1 = GLFloat.memValue(v1);
        GLFloat f2 = () -> v2;
        GLFloat sub = GLFloat.sub(f1, f2);
        System.out.println(sub.get());
        v1 = 80;
        System.out.println(sub.get());
    }
}
