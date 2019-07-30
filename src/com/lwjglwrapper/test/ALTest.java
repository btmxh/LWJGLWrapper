/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.test;

import com.lwjglwrapper.openal.ALContext;
import com.lwjglwrapper.openal.Listener;
import com.lwjglwrapper.openal.SoundBuffer;
import com.lwjglwrapper.openal.Source;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import org.joml.Vector3f;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

/**
 *
 * @author Welcome
 */
public class ALTest {
    public static void main(String[] args) throws InterruptedException, IOException {
//        ALContext context = new ALContext();
//        SoundBuffer sound = context.loadSound(ALTest.class, "/com/lwjglwrapper/test/bounce.ogg");
//        Source source = new Source(sound);
//        source.repeat(true);
//        
//        source.setPosition(new Vector3f());
//        source.setReferenceDistance(6);
//        source.setMaxDistance(15);
//        source.setVolume(0.5f);
//        
//        context.setDistanceModel(AL11.AL_EXPONENT_DISTANCE_CLAMPED);
//        
//        
//        source.play();
//        
//        
//        System.out.println(context.getListener().getPosition());
//        Scanner sc = new Scanner(System.in);
//        char c = ' ';
//        context.getListener().getPosition().set(-10, 0, 0);
//        while (true) {
////            c = (char) System.in.read();
////            if(c == 'q')    break;
//            Listener listener = context.getListener();
//            listener.getPosition().add(0.01f, 0, 0);
//            context.updateListener();
//            System.out.println(listener.getPosition().toString(new DecimalFormat()));
//            Thread.sleep(10);
//            
//        }
//        
//        context.dispose();
//        sound.dispose();
//        source.dispose();
    }
}
