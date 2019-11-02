/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import com.lwjglwrapper.nanovg.NVGGraphics;
import com.lwjglwrapper.utils.floats.GLFloat;
import com.lwjglwrapper.utils.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

/**
 *
 * @author Welcome
 */
public class ComboBox extends Component{
    
    public static final int NORMAL = 0, HOVER = 1, CLICKED = 2;
    protected List<ComboBoxCell> cells;
    protected float cellHeight;
    protected int selectedIndex = 0;
    private OnChangeListener ocl;
    private boolean expand = false;
    protected GLFloat cellCornerX;
    protected GLFloat cellCornerY;
    
    public ComboBox(Stage stage, boolean autoAdd, GLFloat cellCornerX, GLFloat cellCornerY, float cellHeight) {
        super(stage, autoAdd, 3);
        cells = new ArrayList<>();
        this.cellCornerX = cellCornerX;
        this.cellCornerY = cellCornerY;
        this.cellHeight = cellHeight;
    }
    
    public void addCell(ComboBoxCell cell) {
        cells.add(cell);
    }
    
    public void createCells(BiFunction<Integer, Object, ComboBoxCell> factory, List elements) {
        for (int i = 0; i < elements.size(); i++) {
            Object element = elements.get(i);
            addCell(factory.apply(i, element));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!isVisible())    return;
        
        if(MathUtils.contains(getCurrentShape().getShape().boundBox(), detransformedMousePosition)) {
            if(stage.window.getMouse().anyMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                setMode(CLICKED);
                expand = !expand;
            } else setMode(HOVER);
        } else {
            setMode(NORMAL);
            if(stage.window.getMouse().anyMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                expand = false;
            }
        }
        
        for (int i = 0; i < cells.size(); i++) {
            ComboBoxCell cell = cells.get(i);
            cell.tick();
        }
    }

    @Override
    public void render(NVGGraphics g) {
        super.render(g);
        if(!expand) return;
        g.push();
        g.translate(cellCornerX.get(), cellCornerY.get());
        for (int i = 0; i < cells.size(); i++) {
            ComboBoxCell cell = cells.get(i);
            cell.render(g);
        }
        g.pop();
    }
    
    @Override
    public void resetState() {
        super.resetState();
        setMode(NORMAL);
    }
    
    public void setOnChangeListener(OnChangeListener ocl) {
        this.ocl = ocl;
    }

    private int select(ComboBoxCell cell) {
        this.selectedIndex = cells.indexOf(cell);
        return selectedIndex;
    }
    
    private void select(int idx) {
        this.selectedIndex = idx;
    }

    public float getCellHeight() {
        return cellHeight;
    }

    public static interface OnChangeListener {
        public void changed(int idx, ComboBoxCell cell);
    }
    
    public static class ComboBoxCell extends Component {
        
        public static final int NORMAL = 0, HOVER = 1, CLICKED = 2;
        
        protected ComboBox comboBox;
        
        public ComboBoxCell(ComboBox comboBox, boolean autoAdd) {
            super(comboBox.stage, false, 3);
            this.comboBox = comboBox;
            if(autoAdd) comboBox.addCell(this);
            setMode(0);
        }

        @Override
        public void tick() {
            super.tick();
            if(MathUtils.contains(getCurrentShape().getShape().boundBox(), detransformedMousePosition)) {
                if(stage.window.getMouse().anyMouseReleased(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                    setMode(CLICKED);
                    int index = comboBox.select(this);
                    comboBox.ocl.changed(index, this);
                } else setMode(HOVER);
            } else {
                setMode(NORMAL);
            }
        }

        @Override
        public void render(NVGGraphics g) {
            super.render(g);
            g.translate(0, comboBox.cellHeight);
        }
        
        
        
    }
    
}
