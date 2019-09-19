/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.exceptions;


public class AssimpException extends LWJGLException {

    public AssimpException() {
    }

    public AssimpException(String message) {
        super(message);
    }

    public AssimpException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssimpException(Throwable cause) {
        super(cause);
    }
    
}