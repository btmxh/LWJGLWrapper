/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import com.lwjglwrapper.utils.Utils;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class Texture {
    protected int gl_id;
    protected int textureType;
    
    public Texture(int textureType) {
        this.textureType = textureType;
        gl_id = GL11.glGenTextures();
    }
    
    public final void bind() {
        GL11.glBindTexture(textureType, gl_id);
    }
    
    public final int bind(int texSlot) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0 + texSlot);
        bind();
        return texSlot;
    }
    
    public final void unbind() {
        GL11.glBindTexture(textureType, 0);
    }
    
    public void configTexture(int id) {
        GL11.glTexParameteri(textureType, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(textureType, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    }

    public void dispose() {
        GL11.glDeleteTextures(gl_id);
    }
    
    
}
