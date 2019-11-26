/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class VBO {
    private int id;
    private int type;
    private int usage;
    
    public VBO(int type, int usage) {
        this.type = type;
        id = GL20.glGenBuffers();
        this.usage = usage;
    }

    public void dispose() {
        GL20.glDeleteBuffers(id);
        
    }
    
    public void bind() {
        GL20.glBindBuffer(type, id);
    }
    
    public void unbind() {
        GL20.glBindBuffer(type, 0);
    }
    
    public void storeData(long size) {
        GL15.glBufferData(type, size, usage);
    }
    
    public void storeData(float[] data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(int[] data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(FloatBuffer data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void storeData(IntBuffer data) {
        GL20.glBufferData(type, data, usage);
    }
    
    public void updateVBO(float[] data) {
        bind();
        storeData(data.length * Float.BYTES);
        GL15.glBufferSubData(type, 0, data);
        unbind();
    }
    
    public void updateVBO(int offset, float[] data) {
        bind();
        GL15.glBufferSubData(type, offset, data);
        unbind();
    }

    public void updateVBO(FloatBuffer buffer) {
        bind();
        buffer.flip();
        storeData(buffer.capacity());
        GL15.glBufferSubData(type, 0, buffer);
        unbind();
    }
}
