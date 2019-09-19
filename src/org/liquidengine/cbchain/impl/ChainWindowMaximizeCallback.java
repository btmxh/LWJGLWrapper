/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.liquidengine.cbchain.impl;

import java.util.Collection;
import org.liquidengine.cbchain.AbstractChainCallback;
import org.liquidengine.cbchain.IChainWindowMaximizeCallback;
import org.lwjgl.glfw.GLFWWindowMaximizeCallbackI;


public class ChainWindowMaximizeCallback extends AbstractChainCallback<GLFWWindowMaximizeCallbackI> implements IChainWindowMaximizeCallback {

    @Override
    public void invoke(long window, boolean maximized) {
        callbackChain.forEach((c) -> c.invoke(window, maximized));
    }

}
