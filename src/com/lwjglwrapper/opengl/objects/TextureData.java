/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.objects;

import com.lwjglwrapper.utils.Utils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

/**
 *
 * @author Welcome
 */
public class TextureData {

    int width, height;
    ByteBuffer pixels;

    public TextureData(int width, int height, ByteBuffer pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getPixelData() {
        return pixels;
    }

    public void free() {
        if(pixels == null)  return;
        MemoryUtil.memFree(pixels);
    }

    public static TextureData fromPath(String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer pixels = STBImage.stbi_load(path, w, h, comp, 4);

            return new TextureData(w.get(), h.get(), pixels);
        }
    }
    
    public static TextureData fromResource(Class cl, String path) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer buffer = Utils.ioResourceToByteBuffer(cl.getResourceAsStream(path), 8 * 1024);
            
            ByteBuffer pixels = STBImage.stbi_load_from_memory(buffer, w, h, comp, 4);

            return new TextureData(w.get(), h.get(), pixels);
        } catch (IOException ex) {
            Logger.getLogger(TextureData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public float getAspectRatio() {
        return (float) width / (float) height;
    }
}
