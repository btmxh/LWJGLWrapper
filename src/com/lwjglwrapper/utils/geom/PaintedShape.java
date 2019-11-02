/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.geom;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.nanovg.paint.AdditionalPaint;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.nanovg.paint.types.StrokePaint;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class PaintedShape<S extends Shape>{

    public static final PaintedShape EMPTY = new PaintedShape(Shape.EMPTY, null, null);
    private S shape;
    private FillPaint fill;
    private StrokePaint stroke;
    private AdditionalPaint afterPaint, beforePaint;

    public PaintedShape(S shape, FillPaint fill, StrokePaint stroke) {
        this.shape = shape;
        this.fill = fill;
        this.stroke = stroke;
        this.afterPaint = AdditionalPaint.DO_NOTHING;
        this.beforePaint = AdditionalPaint.DO_NOTHING;
    }

    public PaintedShape(S shape, FillPaint fill, StrokePaint stroke,
            AdditionalPaint afterPaint, AdditionalPaint beforePaint) {
        this.shape = shape;
        this.fill = fill;
        this.stroke = stroke;
        this.afterPaint = afterPaint == null ? AdditionalPaint.DO_NOTHING : afterPaint;
        this.beforePaint = beforePaint == null ? AdditionalPaint.DO_NOTHING : beforePaint;
    }

    public void render(NVGGraphics g) {
        beforePaint.paint(g);
        
        shape.render(g);
        g.fill(fill);
        g.stroke(stroke);

        afterPaint.paint(g);
    }

    /**
     * @return the shape
     */
    public S getShape() {
        return shape;
    }

    /**
     * @return the fill
     */
    public FillPaint getFill() {
        return fill;
    }

    /**
     * @return the stroke
     */
    public StrokePaint getStroke() {
        return stroke;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(S shape) {
        this.shape = shape;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(Paint fill) {
        this.fill = fill;
    }

    /**
     * @param stroke the stroke to set
     */
    public void setStroke(Paint stroke) {
        this.stroke = stroke;
    }

    /**
     * @return the additionalPaint
     */
    public AdditionalPaint getAfterPaint() {
        return afterPaint;
    }

    /**
     * @param afterPaint the additionalPaint to set
     */
    public void setAfterPaint(
            AdditionalPaint afterPaint) {
        this.afterPaint = afterPaint;
    }

    /**
     * @return the beforePaint
     */
    public AdditionalPaint getBeforePaint() {
        return beforePaint;
    }

    /**
     * @param beforePaint the beforePaint to set
     */
    public void setBeforePaint(
            AdditionalPaint beforePaint) {
        this.beforePaint = beforePaint;
    }

}
