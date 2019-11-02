/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom.shapes;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.floats.GLFloat;
import com.lwjglwrapper.utils.geom.Shape;
import com.lwjglwrapper.utils.math.MathUtils;
import java.awt.geom.Rectangle2D;
import org.joml.Rectanglef;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Rect implements Shape{

    protected GLRect rect, bounds;

    public Rect(GLFloat x, GLFloat y, GLFloat w, GLFloat h) {
        this(new GLRect(x, y, w, h));
    }
    
    public Rect(GLFloat x, GLFloat y, GLFloat w, GLFloat h, GLFloat bx, GLFloat by, GLFloat bw, GLFloat bh) {
        this(new GLRect(x, y, w, h), new GLRect(bx, by, bw, bh));
    }
    
    public Rect(GLRect rect) {
        this(rect, rect);
    }

    public Rect(GLRect rect, GLRect bounds) {
        this.rect = rect;
        this.bounds = bounds;
    }
    
    @Override
    public Rectanglef boundBox() {
        return bounds.getJOMLRect();
    }

    @Override
    public void render(NVGGraphics g) {
        g.rect(rect.getX().get(), rect.getY().get(), rect.getWidth().get(), rect.getHeight().get());
    }
    
    public static Rectanglef jomlRect(float x, float y, float w, float h) {
        return new Rectanglef(x, y, x + w, y + h);
    }
    
    public static Rectangle2D.Float awtRect(Rectanglef rect) {
        return new Rectangle2D.Float(rect.minX, rect.minY, rect.maxX - rect.minX, rect.maxY - rect.minY);
    }

    public Rectanglef getJOMLRect() {
        return rect.getJOMLRect();
    }

    public Rectanglef getJOMLBounds() {
        return bounds.getJOMLRect();
    }
    
    public boolean contains(Vector2f point) {
        return MathUtils.contains(rect.getJOMLRect(), point);
    }
    
}
