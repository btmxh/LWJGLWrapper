/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.liquidengine.cbchain.impl;

import org.liquidengine.cbchain.AbstractChainCallback;
import org.liquidengine.cbchain.IChainWindowContentScaleCallback;
import org.lwjgl.glfw.GLFWWindowContentScaleCallback;
import org.lwjgl.glfw.GLFWWindowContentScaleCallbackI;

/**
 *
 * @author Welcome
 */
public class ChainWindowContentScaleCallback extends AbstractChainCallback<GLFWWindowContentScaleCallbackI> implements IChainWindowContentScaleCallback{

    @Override
    public void invoke(long window, float xscale, float yscale) {
        callbackChain.forEach((c) -> c.invoke(window, xscale, yscale));
    }
    
}
