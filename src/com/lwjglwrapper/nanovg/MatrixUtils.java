/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg;

import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * 
 * @author Welcome
 */
public class MatrixUtils {
    public static Vector2f getTranslation(Matrix3f matrix) {
        Vector3f translation = matrix.getColumn(2, new Vector3f());
        return new Vector2f(translation.x, translation.y);
    }
    
    public static float getRotation(Matrix3f matrix) {
        return (float) Math.atan2(matrix.m10, matrix.m11);
    }
    
    public static Vector2f getScale(Matrix3f matrix) {
        float x = Math.signum(matrix.m00) * matrix.getColumn(0, new Vector3f()).length();
        float y = Math.signum(matrix.m11) * matrix.getColumn(1, new Vector3f()).length();
        return new  Vector2f(x, y);
    }
    
    public static void setTranslation(Matrix3f matrix, Vector2f translation) {
        matrix.m02 = translation.x;
        matrix.m12 = translation.y;
    }
    
    public static void setRotation(Matrix3f matrix, float rotation) {
        Vector2f translation = getTranslation(matrix);
        Vector2f scale = getScale(matrix);
        float cos = (float) Math.cos(rotation);
        float sin = (float) Math.sin(rotation);
        matrix.set(cos * scale.x, -sin * scale.x, translation.x, sin * scale.y, cos * scale.y, translation.y, 0, 0, 1);
    }
    
    public static void setScale(Matrix3f matrix, Vector2f scale) {
        Vector2f orgScale = getScale(matrix);
        scale(matrix, new Vector2f(scale.x / orgScale.x, scale.y / orgScale.y));
    }
    
    public static void translate(Matrix3f matrix, Vector2f translate) {
        matrix.m20 += translate.x;
        matrix.m21 += translate.y;
    }
    
    public static void rotate(Matrix3f matrix, float angle) {
        setRotation(matrix, angle + getRotation(matrix));
    }
    
    public static void scale(Matrix3f matrix, Vector2f scale) {
        matrix.m00 *= scale.x;
        matrix.m01 *= scale.x;
        matrix.m10 *= scale.y;
        matrix.m11 *= scale.y;
    } 
    
    public static void main(String[] args) {
        Matrix3f mat = new Matrix3f(-2, -1, 2, -2, 1, -1, 0, 0, 1);
        mat.transpose();
        System.out.println(mat);
        System.out.println(getTranslation(mat));
        System.out.println(getScale(mat));
        System.out.println(Math.toDegrees(getRotation(mat)));
        
        setRotation(mat, (float) Math.toRadians(45));
        System.out.println("\n" + mat);
        
        System.out.println(Math.toDegrees(getRotation(mat)));
        
    }
}
