/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.math.MathUtils;
import static com.lwjglwrapper.utils.ui.Button.NORMAL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.joml.Rectanglef;
import org.joml.Vector2f;

/**
 *
 * @author Welcome
 */
public class Panel extends ComponentCollection{
    
    public static final int NORMAL = 0;
    
    protected List<Component> children = new ArrayList<>();
    
    public Panel(Stage stage, boolean autoAdd) {
        super(stage, autoAdd, 1);
    }

    @Override
    public void tick() {
        if(!isVisible())    return;
        if(shapes == null)  return;
        super.tick();
        for (Component comp : children) {
            comp.tick();
        }
    }

    @Override
    public void render(NVGGraphics g) {
        if(!isVisible())    return;
        if(shapes == null)  return;
        g.push();
        super.render(g);
        for (Component comp : children) {
            comp.render(g);
        }
        g.pop();
    }
    
    public void addChild(Component comp) {
        children.add(comp);
    }
    
    public void removeChild(Component comp) {
        children.remove(comp);
    }

    
    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
    }

    @Override
    public Component findHover() {
        Component hovering = null;
        for(int i = children.size() - 1; i >= 0; i--) {
            Component comp = children.get(i);
            if(!comp.isVisible() || hovering != null)   continue;
            if(comp instanceof Panel)    hovering = ((Panel) comp).findHover();
            if(comp.getCurrentShape() == null)   continue;
            boolean hover = MathUtils.contains(comp.getCurrentShape().getShape().boundBox(), comp.detransformedMousePosition);
            if(hover)   hovering = comp;
            
        }
        return hovering;
    }

    @Override
    public List<Component> children() {
        return children;
    }
}
