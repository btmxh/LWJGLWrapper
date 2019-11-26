/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils;

import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.utils.cameras.Camera;
import com.lwjglwrapper.utils.math.MathUtils;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 *
 * @author Welcome
 */
public class MousePicker {

    protected final Window window;

    public MousePicker(Window window) {
        this.window = window;
    }
    
    public Vector3f calculateMouseRay(Camera camera) {
        return calculateMouseRay(camera.getViewMatrix(), camera.getProjectionMatrix());
    }
    
    public Vector3f calculateMouseRay(Matrix4f viewMatrix, Matrix4f projectionMatrix) {
        Vector2f normalizedCoords = getNormalisedDeviceCoordinates(window.getMouse().getCursorPosition());
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1.0f, 1.0f);
        Vector4f eyeCoords = toEyeCoords(clipCoords, projectionMatrix);
        Vector3f worldRay = toWorldCoords(eyeCoords, viewMatrix);
        return worldRay;
    }

    private Vector3f toWorldCoords(Vector4f eyeCoords, Matrix4f viewMatrix) {
        Matrix4f invertedView = viewMatrix.invert(new Matrix4f());
        Vector4f rayWorld = invertedView.transform(eyeCoords, new Vector4f());
        Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
        mouseRay.normalize();
        return mouseRay;
    }

    private Vector4f toEyeCoords(Vector4f clipCoords, Matrix4f projectionMatrix) {
        Matrix4f invertedProjection = projectionMatrix.invert(new Matrix4f());
        Vector4f eyeCoords = invertedProjection.transform(clipCoords, new Vector4f());
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);
    }

    private Vector2f getNormalisedDeviceCoordinates(Vector2f mousePos) {
        float x = (2.0f * mousePos.x) / window.getWidth() - 1f;
        float y = 1f - (2.0f * mousePos.y) / window.getHeight();
        return new Vector2f(x, y);
    }
}
