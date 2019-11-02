/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.utils.ui;

import java.util.List;

/**
 *
 * @author Welcome
 */
public abstract class ComponentCollection extends Component{

    public ComponentCollection(Stage stage, boolean autoAdd, int maxModes) {
        super(stage, autoAdd, maxModes);
    }
    
    public abstract Component findHover();
    public abstract List<? extends Component> children();
}
