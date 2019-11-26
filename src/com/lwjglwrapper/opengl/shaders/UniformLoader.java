/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.opengl.shaders;

import com.lwjglwrapper.opengl.objects.Texture;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.function.BiConsumer;
import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

/**
 *
 * @author Welcome
 */
public class UniformLoader<T> {
    
    private final Class<T> clazz;
    private final BiConsumer<Integer, T> loader;

    public UniformLoader(Class<T> clazz, BiConsumer<Integer, T> loader) {
        this.clazz = clazz;
        this.loader = loader;
    }

    public void load(int location, T value) {
        loader.accept(location, value);
    }

    public Class<T> getLoadClass() {
        return clazz;
    }
    
    
    
    public static final UniformLoader<Integer> INT_UL = new UniformLoader<>(Integer.class, GL20::glUniform1i);
    public static final UniformLoader<Float> FLOAT_UL = new UniformLoader<>(Float.class, GL20::glUniform1f);
    public static final UniformLoader<Vector2fc> VEC2_UL = new UniformLoader<>(Vector2fc.class, (loc, vec2) -> {
        GL20.glUniform2f(loc, vec2.x(), vec2.y());
    });
    public static final UniformLoader<Vector3fc> VEC3_UL = new UniformLoader<>(Vector3fc.class, (loc, vec3) -> {
        GL20.glUniform3f(loc, vec3.x(), vec3.y(), vec3.z());
    });
    public static final UniformLoader<Vector4fc> VEC4_UL = new UniformLoader<>(Vector4fc.class, (loc, vec4) -> {
        GL20.glUniform4f(loc, vec4.x(), vec4.y(), vec4.z(), vec4.w());
    });
    public static final UniformLoader<Matrix3fc> MAT3_UL = new UniformLoader<>(Matrix3fc.class, (loc, mat3) -> {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = mat3.get(stack.mallocFloat(9));
            GL20.glUniformMatrix3fv(loc, false, buffer);
        }
    });
    public static final UniformLoader<Matrix4fc> MAT4_UL = new UniformLoader<>(Matrix4fc.class, (loc, mat4) -> {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = mat4.get(stack.mallocFloat(16));
            GL20.glUniformMatrix4fv(loc, false, buffer);
        }
    });
    
    private static HashMap<Class, UniformLoader> sul() {
        HashMap<Class, UniformLoader> map = new HashMap<>();
        
        map.put(INT_UL.clazz, INT_UL);
        map.put(FLOAT_UL.clazz, FLOAT_UL);
        map.put(VEC2_UL.clazz, VEC2_UL);
        map.put(VEC3_UL.clazz, VEC3_UL);
        map.put(VEC4_UL.clazz, VEC4_UL);
        map.put(MAT3_UL.clazz, MAT3_UL);
        map.put(MAT4_UL.clazz, MAT4_UL);
        
        return map;
    }
    
    public static HashMap<Class, UniformLoader> sul_clone() {
        return (HashMap<Class, UniformLoader>) sul().clone();
    }
    
    
}
