/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.nanovg;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.display.Window;
import com.lwjglwrapper.nanovg.paint.BoxGradient;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.nanovg.paint.ImagePaint;
import com.lwjglwrapper.nanovg.paint.LinearGradient;
import com.lwjglwrapper.utils.geom.Shape;
import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.nanovg.paint.RadialGradient;
import com.lwjglwrapper.nanovg.paint.types.StrokePaint;
import com.lwjglwrapper.nanovg.paint.types.TextPaint;
import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.Utils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Stack;
import org.joml.Matrix3f;
import org.joml.Rectanglef;
import org.joml.Vector2f;
import org.lwjgl.nanovg.NVGLUFramebuffer;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NVGTextRow;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;

/**
 *
 * @author Welcome
 */
public class NVGGraphics {
    private long nanoVGID;
    private int width, height, pixelRatio;
    private Stack<TransformationState> transformationStates;
    private Stack<AlphaState> alphaStates;

    public NVGGraphics(Window window) {
        nanoVGID = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_DEBUG | NanoVGGL3.NVG_STENCIL_STROKES);
        this.width = window.getWidth();
        this.height = window.getHeight();
        this.pixelRatio = 1;
        transformationStates = new Stack<>();
        alphaStates = new Stack<>();
    }
    
    public NVGGraphics() {
        this(LWJGL.window);
    }

    public void translate(float x, float y) {
        NanoVG.nvgTranslate(nanoVGID, x, y);
        getCurrentState().translate(new Vector2f(x, y));
    }
    
    public void translate(Vector2f vector) {
        translate(vector.x, vector.y);
    }

    public void scale(float x, float y) {
        NanoVG.nvgScale(nanoVGID, x, y);
        getCurrentState().scale(new Vector2f(x, y));
    }
    
    public void pushAlpha() {
        alphaStates.push(alphaStates.peek().clone());
    }
    
    public void popAlpha() {
        alphaStates.pop();
    }

    public void fill(FillPaint p) {
        if(p == null)   return;
        p.fmulAlpha(alphaStates.peek().alpha).fill(nanoVGID);
    }
    
    public void stroke(StrokePaint p) {
        if(p == null)   return;
        p.smulAlpha(alphaStates.peek().alpha).stroke(nanoVGID);
    }

    public void beginPath() {
        NanoVG.nvgBeginPath(nanoVGID);
    }

    public void rotate(float angdeg) {
        NanoVG.nvgRotate(nanoVGID, NanoVG.nvgDegToRad(angdeg));
        getCurrentState().rotate(-angdeg);
    }

    public void line(float x1, float y1, float x2, float y2) {
        beginPath();
        NanoVG.nvgMoveTo(nanoVGID, x1, y1);
        NanoVG.nvgLineTo(nanoVGID, x2, y2);
    }

    public void rect(float x, float y, float width, float height) {
        beginPath();
        NanoVG.nvgRect(nanoVGID, x, y, width, height);
    }

    public void square(float x, float y, float side) {
        rect(x, y, side, side);
    }

    public void roundRect(float x, float y, float width, float height, float radius) {
        beginPath();
        NanoVG.nvgRoundedRect(nanoVGID, x, y, width, height, radius);
    }

    public void roundRect(float x, float y, float width, float height, float radTL, float radTR, float radBR, float radBL) {
        beginPath();
        NanoVG.nvgRoundedRectVarying(nanoVGID, x, y, width, height, radTL, radTR, radBR, radBL);
    }

    public void ellipse(float x, float y, float width, float height) {
        beginPath();
        NanoVG.nvgEllipse(nanoVGID, x, y, width, height);
    }

    public void arc(float x, float y, float radius, float startAngle, float endAngle, boolean clockWise) {
        beginPath();
        NanoVG.nvgArc(nanoVGID, x, y, radius, -NanoVG.nvgDegToRad(startAngle),
                -NanoVG.nvgDegToRad(endAngle), clockWise ? NanoVG.NVG_CW : NanoVG.NVG_CCW);
    }

    public void polyline(float[] xPos, float[] yPos, int n) {
        if (n == -1 || (n < xPos.length || n < yPos.length)) {
            n = Math.min(xPos.length, yPos.length);
        }
        beginPath();
        if (n == 0) {
            return;
        }
        NanoVG.nvgMoveTo(nanoVGID, xPos[0], yPos[0]);
        for (int i = 1; i < n; i++) {
            NanoVG.nvgLineTo(nanoVGID, xPos[i], yPos[i]);
            NanoVG.nvgMoveTo(nanoVGID, xPos[i], yPos[i]);
        }
    }

    public void polygon(float[] xPos, float[] yPos, int n) {
        if (n == -1 || (n < xPos.length || n < yPos.length)) {
            n = Math.min(xPos.length, yPos.length);
        }
        beginPath();
        if (n == 0) {
            return;
        }
        NanoVG.nvgMoveTo(nanoVGID, xPos[0], yPos[0]);
        for (int i = 1; i < n; i++) {
            NanoVG.nvgLineTo(nanoVGID, xPos[i], yPos[i]);
            //NanoVG.nvgMoveTo(nanoVGID, xPos[i], yPos[i]);
        }
        NanoVG.nvgLineTo(nanoVGID, xPos[0], yPos[0]);
        NanoVG.nvgMoveTo(nanoVGID, xPos[0], yPos[0]);
    }
    
    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        polygon(new float[]{x1, x2, x3}, new float[]{y1, y2, y3}, 3);
    }

    public NVGImage createNanoVGImage(String path, int flags) {
        int id = NanoVG.nvgCreateImage(nanoVGID, path, flags);
        NVGImage image = new NVGImage(nanoVGID, id);
        return image;
    }
    
    public NVGImage createNanoVGImageFromResource(ByteBuffer buffer, int flags) {
        int id = NanoVG.nvgCreateImageMem(nanoVGID, flags, buffer);
        NVGImage image = new NVGImage(nanoVGID, id);
        return image;
    }

    public void image(NVGImage img, float x, float y) {
        image(img, x, y, img.getWidth(), img.getHeight());
    }

    public void image(NVGImage img, float x, float y, float width, float height) {
        rect(x, y, width, height);
        fill(img, x, y, width, height, 0, 1);
    }

    public void image(NVGImage img, float x, float y, Paint bgcolor) {
        image(img, x, y, img.getWidth(), img.getHeight(), bgcolor);
    }

    public void image(NVGImage img, float x, float y, float width, float height, Paint bgcolor) {
        rect(x, y, width, height);
        fill(bgcolor);
        image(img, x, y, width, height);
    }

//    public void image(NVGImage img, float dx, float dy, float dwidth, float dheight,
//            float sx, float sy, float swidth, float sheight) {
//        image(img, dx, dy, dwidth, dheight, sx, sy, swidth, sheight, new AbstractColor(0, 0, 0, 0));
//    }
//
//    public void image(NVGImage img, float dx, float dy, float dwidth, float dheight,
//            float sx, float sy, float swidth, float sheight, Paint bgcolor) {
//
//        //sx = img.getWidth() 
//        NVGPaint paint = NVGPaint.create();
//        NanoVG.nvgImagePattern(nanoVGID, dx - sx, dx - sy, swidth - sx, sheight - sy, 0, img.id, 1, paint);
//        rect(dx, dy, dwidth, dheight);
//        NanoVG.nvgFillPaint(nanoVGID, paint);
//        NanoVG.nvgFill(nanoVGID);
//    }

    public void fill(NVGImage img, float x, float y, float width, float height, float angle, float alpha) {
        NVGPaint paint = NVGPaint.create();
        NanoVG.nvgImagePattern(nanoVGID, x, y, width, height, NanoVG.nvgDegToRad(angle),
                img.id, alpha, paint);
        NanoVG.nvgFillPaint(nanoVGID, paint);
        NanoVG.nvgFill(nanoVGID);
    }

    public NVGPaint imagePaint(NVGImage img, float x, float y, float width, float height, float angle, float alpha) {
        NVGPaint paint = NVGPaint.create();
        NanoVG.nvgImagePattern(nanoVGID, x, y, width, height, NanoVG.nvgDegToRad(angle),
                img.id, alpha, paint);
        return paint;
    }

    public void begin() {
        NanoVG.nvgBeginFrame(nanoVGID, width, height, pixelRatio);
        transformationStates.clear();
        transformationStates.push(new TransformationState(new Matrix3f()));
        alphaStates.clear();
        alphaStates.push(new AlphaState(1f));
    }

    public void end() {
        NanoVG.nvgEndFrame(nanoVGID);
    }

    public long getID() {
        return nanoVGID;
    }

    public void circle(float x, float y, float r) {
        beginPath();
        NanoVG.nvgCircle(nanoVGID, x, y, r);
    }

    public LinearGradient linearPaint(Rectanglef bounds, AbstractColor icol, AbstractColor ocol) {
        return new LinearGradient(nanoVGID, bounds.minX, bounds.minY, bounds.maxX, bounds.maxY,
                icol, ocol);
    }

    public LinearGradient linearPaintTLBR(Shape shape, AbstractColor icol, AbstractColor ocol) {
        return linearPaint(shape.boundBox(), icol, ocol);
    }

    public LinearGradient linearPaintBLTR(Rectanglef bounds, AbstractColor icol, AbstractColor ocol) {
        return new LinearGradient(nanoVGID, bounds.minX, bounds.maxY, bounds.maxX, bounds.minY, icol, ocol);
    }

    public LinearGradient linearPaintBLTR(Shape shape, AbstractColor icol, AbstractColor ocol) {
        return linearPaintBLTR(shape.boundBox(), icol, ocol);
    }

    public LinearGradient linearPaintCLCR(Rectanglef bounds, AbstractColor icol, AbstractColor ocol) {
        return new LinearGradient(nanoVGID, bounds.minX, bounds.maxY, bounds.maxX, bounds.maxY, icol, ocol);
    }

    public LinearGradient linearPaintCLCR(Shape shape, AbstractColor icol, AbstractColor ocol) {
        return linearPaintBLTR(shape.boundBox(), icol, ocol);
    }

//    public RadialGradient radialPaint(float cx, float cy, AbstractColor icol, float ir, AbstractColor ocol, float or) {
//        return new RadialGradient(nanoVGID, cx, cy, ir, or, icol, ocol);
//    }
    
    public BoxGradient boxGradient(Rectanglef rect, float radius, float feather, AbstractColor icol, AbstractColor ocol) {
        return new BoxGradient(nanoVGID, rect.minX, rect.minY, rect.maxX - rect.minX, rect.maxY - rect.minY, radius, feather, icol, ocol);
    }
    
    public ImagePaint imagePaint(NVGImage image, Rectanglef bounds, float angle, float alpha) {
        return new ImagePaint(nanoVGID, bounds.minX, bounds.minY, bounds.maxX - bounds.minX, bounds.maxY - bounds.minY, image, angle, alpha);
    }

//    public NVGPaint monoColorPaint(AbstractColor color) {
//        return linearPaint(new Rectangle2D.Float(0, 0, 100, 100), color, color);
//    }

    public NVGFont createFont(String path, String name) {
        int id = NanoVG.nvgCreateFont(nanoVGID, name, path);
        return new NVGFont(nanoVGID, id, name);
    }

    public void textAlign(int align) {
        NanoVG.nvgTextAlign(nanoVGID, align);
    }

    public void text(String text, float x, float y) {
        NanoVG.nvgText(nanoVGID, x, y, text);
    }
    
    public void text(ByteBuffer text, float x, float y) {
        NanoVG.nvgText(nanoVGID, x, y, text);
    }
    
    public void textBox(String text, float x, float y, float width) {
        NanoVG.nvgTextBox(nanoVGID, x, y, width, text);
    }
    
    public int textBoxLines(String text, float width, int maxLines) {
        NVGTextRow.Buffer buffer = NVGTextRow.create(maxLines);
        int lines = NanoVG.nvgTextBreakLines(nanoVGID, text, width, buffer);
        return lines;
    }

    public void textSize(float size) {
        NanoVG.nvgFontSize(nanoVGID, size);
    }

    public void textPaint(TextPaint paint) {
        paint.tmulAlpha(alphaStates.peek().alpha).text(nanoVGID);
    }
    
//    public void textStroke(AbstractColor color) {
//        NanoVG.nvgStrokeColor(nanoVGID, color.toNanoVGColor());
//    }

    public void push() {
        NanoVG.nvgSave(nanoVGID);
        transformationStates.push(getCurrentState().clone());
    }

    public void pop() {
        NanoVG.nvgRestore(nanoVGID);
        transformationStates.pop();
    }

    public float textLength(String text) {
        float[] bounds = new float[4];
        return NanoVG.nvgTextBounds(nanoVGID, 0, 0, text, bounds);
    }

    public NVGLUFramebuffer createFBO(int width, int height, boolean mipmap, boolean repeatX, boolean repeatY, boolean preMultiplyAlpha, boolean flipY, boolean nearest) {
        int flags = ((mipmap ? 1 : 0) * NanoVG.NVG_IMAGE_GENERATE_MIPMAPS)
                | ((repeatX ? 1 : 0) * NanoVG.NVG_IMAGE_REPEATX)
                | ((repeatY ? 1 : 0) * NanoVG.NVG_IMAGE_REPEATY)
                | ((preMultiplyAlpha ? 1 : 0) * NanoVG.NVG_IMAGE_PREMULTIPLIED)
                | ((flipY ? 1 : 0) * NanoVG.NVG_IMAGE_FLIPY)
                | ((nearest ? 1 : 0) * NanoVG.NVG_IMAGE_NEAREST);
        return NanoVGGL3.nvgluCreateFramebuffer(nanoVGID, width, height, flags);
    }
    
    public void bindFBO(NVGLUFramebuffer fbo) {
        NanoVGGL3.nvgluBindFramebuffer(nanoVGID, fbo);
    }
    
    public void unbindFBO() {
        NanoVGGL3.nvgluBindFramebuffer(nanoVGID, null);
    }
    
    public void disposeFBO(NVGLUFramebuffer fbo) {
        NanoVGGL3.nvgluDeleteFramebuffer(nanoVGID, fbo);
    }

    public void dispose() {
        NanoVGGL3.nvgDelete(nanoVGID);
    }
    
    public NVGImage getImage(NVGLUFramebuffer fbo) {
        return new NVGImage(nanoVGID, fbo.image());
    }

    public void updateSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void strokeWeight(float weight) {
        NanoVG.nvgStrokeWidth(nanoVGID, weight);
    }

    public NVGFont loadInJarFont(Class cl, String path, String name, int filesize) {
        try {
            ByteBuffer buffer = Utils.ioResourceToByteBuffer(cl.getResourceAsStream(path), filesize);
            int fontID = NanoVG.nvgCreateFontMem(nanoVGID, name, buffer, 0);
            if(fontID == -1)   throw new InternalError("fnsdklfjsdlkf");
            return new NVGFont(nanoVGID, fontID, name);
        } catch (IOException ex) {
            throw new InternalError("fnsdklfjsdlkf");
        }
//        return null;
    }
    
    public void setScissorArea(float x, float y, float width, float height) {
        NanoVG.nvgScissor(nanoVGID, x, y, width, height);
    }

    public void mulAlpha(float alpha) {
        alphaStates.peek().alpha *= alpha;
    }
    
    public static class TransformationState {
        private Matrix3f transformationMatrix;

        public TransformationState(Matrix3f transformationMatrix) {
            this.transformationMatrix = transformationMatrix;
        }

        public Matrix3f getTransformationMatrix() {
            return transformationMatrix;
        }
        
        public Vector2f getTranslation() {
            return MatrixUtils.getTranslation(transformationMatrix);
        }
        
        public float getRotation() {
            return (float) Math.toDegrees(MatrixUtils.getRotation(transformationMatrix));
        }
        
        public Vector2f getScale() {
            return MatrixUtils.getScale(transformationMatrix);
        }
        
        public void translate(Vector2f translate) {
            MatrixUtils.translate(transformationMatrix, translate);
        }
        
        public void rotate(float angle) {
            MatrixUtils.rotate(transformationMatrix, (float) Math.toRadians(angle));
        }
        
        public void scale(Vector2f scale) {
            MatrixUtils.scale(transformationMatrix, scale);
        }

        @Override
        public TransformationState clone() {
            return new TransformationState(new Matrix3f(transformationMatrix));
        }
        
        public Matrix3f inverse() {
            return new Matrix3f(transformationMatrix).invert();
        }
    }

    public TransformationState getCurrentState() {
        return transformationStates.peek();
    }
    
    public void setUpText(NVGFont font, Paint paint, float size, int align) {
        font.use();
        textSize(size);
        textAlign(align);
        textPaint(paint);
    }

    public static class AlphaState {
        private float alpha;

        public AlphaState(float alpha) {
            this.alpha = alpha;
        }

        @Override
        public AlphaState clone() {
            return new AlphaState(alpha);
        }
        
        
    }
    
}
