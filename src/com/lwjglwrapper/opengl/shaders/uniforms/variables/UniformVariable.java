/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.uniforms.variables;

import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.utils.Logger;

/**
 *
 * @author Welcome
 */
public abstract class UniformVariable<T> {
    
    protected int location;

    public UniformVariable(Shader shader, String variableName) {
        location = shader.getUniformLocation(variableName);
        if(location == -1) {
            Logger.logln("Uniform variable " + variableName + " is unused or not in shader");
        }
    }
    
    public abstract void load(T value);
    
}
