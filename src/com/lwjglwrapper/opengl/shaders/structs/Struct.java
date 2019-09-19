/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders.structs;

import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UFloat;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UInt;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UMat4;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec2;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec3;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UVec4;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UniformVariable;
import com.lwjglwrapper.utils.Logger;

/**
 *
 * @author Welcome
 */
public abstract class Struct{
    private Shader shader;
    private String variableName;
    
    public Struct(Shader shader, String variableName) {
        this.shader = shader;
        this.variableName = variableName;
    }
    
    public UFloat floatField(String name) {
        return new UFloat(shader, variableName + "." + name);
    }
    
    public UInt intField(String name) {
        return new UInt(shader, variableName + "." + name);
    }
    
    public UMat4 mat4Field(String name) {
        return new UMat4(shader, variableName + "." + name);
    }
    
    public UVec2 vec2Field(String name) {
        return new UVec2(shader, variableName + "." + name);
    }
    
    public UVec3 vec3Field(String name) {
        return new UVec3(shader, variableName + "." + name);
    }
    
    public UVec4 vec4Field(String name) {
        return new UVec4(shader, variableName + "." + name);
    }
}
