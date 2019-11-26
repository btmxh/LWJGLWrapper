/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

/**
 *
 * @author Welcome
 */
public class Shader {
    int id;
    private List<ShaderFile> shaders;
    
    private Map<String, Integer> uniformLocations = new HashMap<>();
    private Map<Class, UniformLoader> uniformLoaders = UniformLoader.sul_clone();
    
    private Shader() {
        id = GL20.glCreateProgram();
        shaders = new ArrayList<>();
    }
    
    public Shader(ShaderFile vertexShader, ShaderFile fragmentShader) {
        this();
        
        ShaderFile.verifyType(vertexShader, GL20.GL_VERTEX_SHADER);
        ShaderFile.verifyType(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        
        shaders.add(vertexShader);
        shaders.add(fragmentShader);
        
        vertexShader.attach(this);
        fragmentShader.attach(this);
        
        GL20.glLinkProgram(id);
        GL20.glValidateProgram(id);
    }

    public Shader(ShaderFile vertexShader, ShaderFile geometryShader, ShaderFile fragmentShader) {
        this();
        
        ShaderFile.verifyType(vertexShader, GL20.GL_VERTEX_SHADER);
        ShaderFile.verifyType(geometryShader, GL32.GL_GEOMETRY_SHADER);
        ShaderFile.verifyType(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        
        shaders.add(vertexShader);
        shaders.add(geometryShader);
        shaders.add(fragmentShader);
        
        vertexShader.attach(this);
        geometryShader.attach(this);
        fragmentShader.attach(this);
        
        GL20.glLinkProgram(id);
        GL20.glValidateProgram(id);
    }
    
    public void bind() {
        GL20.glUseProgram(id);
    }
    
    public void unbind() {
        GL20.glUseProgram(0);
    }
    
    public int getUniformLocation(String variableName) {
        return GL20.glGetUniformLocation(id, variableName);
    }
    
    @SuppressWarnings("unchecked")
    public int loadUniformVariable(String variableName, Object value) {
        Integer location = uniformLocations.get(variableName);
        if(location == null) {
            location = getUniformLocation(variableName);
            uniformLocations.put(variableName, location);
        }
        loadUniformVariable(location, value);
        return location;
    }
    
    @SuppressWarnings("unchecked")
    public void loadUniformVariable(int location, Object value) {
        Objects.requireNonNull(value);
        for (Map.Entry<Class, UniformLoader> entry : uniformLoaders.entrySet()) {
            Class clazz = entry.getKey();
            UniformLoader loader = entry.getValue();
            if(clazz.isInstance(value)) {
                loader.load(location, value);
                return;
            }
        }
    }
    
    public void dispose() {
        unbind();
        
        for (ShaderFile shaderFile : shaders) {
            shaderFile.detach(this);
            shaderFile.delete();
        }
        
        GL20.glDeleteProgram(id);
    }
    
}
