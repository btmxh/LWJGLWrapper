 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lwjglwrapper.test;

import com.lwjglwrapper.LWJGL;
import com.lwjglwrapper.display.Loop;
import com.lwjglwrapper.nanovg.NVGFont;
import com.lwjglwrapper.openal.ALContext;
import com.lwjglwrapper.openal.SoundBuffer;
import com.lwjglwrapper.openal.Source;
import com.lwjglwrapper.opengl.objects.VAO;
import com.lwjglwrapper.opengl.shaders.Shader;
import com.lwjglwrapper.opengl.shaders.ShaderFile;
import com.lwjglwrapper.opengl.shaders.uniforms.variables.UMat4;
import com.lwjglwrapper.utils.GameUtils;
import com.lwjglwrapper.utils.colors.AbstractColor;
import com.lwjglwrapper.utils.cameras.FirstPersonCamera;
import com.lwjglwrapper.utils.colors.StaticColor;
import com.lwjglwrapper.utils.input.KeyBindings;
import com.lwjglwrapper.utils.models.ModelGenerator;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

/**
 *
 * @author Welcome
 */
public class Test extends Loop {

    private VAO vao;
    
    private Shader shader;
    private UMat4 projectionViewMatrix, modelMatrix;
    
    private FirstPersonCamera camera;
    private KeyBindings keyBindings;
    
    private NVGFont font;
    
    private ALContext al;
    private Source source;
    private SoundBuffer sound;
            
    @Override
    protected void init() throws Exception{
        super.init();
        window.createNVGGraphics();
        window.createStage();
        
//        al = new ALContext();
//        sound = al.loadSound("src/com/lwjglwrapper/test/bounce.ogg");
//        source = new Source(sound);
//        source.repeat(false);
        
        font = LWJGL.graphics.createFont("src/assets/GALS.ttf", "some font");
        
        shader = new Shader(ShaderFile.fromFile("src/com/lwjglwrapper/test/shader.vert", GL20.GL_VERTEX_SHADER),
                            ShaderFile.fromFile("src/com/lwjglwrapper/test/shader.frag", GL20.GL_FRAGMENT_SHADER));
        
        projectionViewMatrix = new UMat4(shader, "projectionViewMatrix");
        modelMatrix = new UMat4(shader, "modelMatrix");
        
        keyBindings = new KeyBindings().defaultKeyBindings().debugKeyBindings();
        camera = new FirstPersonCamera(70, window, 0.1f, 1000f);
        camera.setSpeed(1, 1, 5);
        camera.setFirstPersonCameraMode(true);
        
        font = LWJGL.graphics.createFont("res/GALS.ttf", "GALS");
    }

    @Override
    protected void update(float delta) throws Exception {
        super.update(delta);
        
        window.getStage().tick();
        camera.move(keyBindings, delta);
        
//        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)? GL11.GL_FILL:GL11.GL_LINE);
        
        if(keyBindings.anyPressed("exit")) {
            hideCursor = window.hideCursor(!hideCursor);
//            source.play();
        } else if(LWJGL.keyboard.anyKeyPressed(GLFW.GLFW_KEY_G)) {
            System.gc();
        } 
    }

    @Override
    protected void render() throws Exception{
        super.render();
        
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL43.GL_PRIMITIVE_RESTART_FIXED_INDEX);
        
        
        GL11.glClearColor(0, 0, 0.25f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        ModelGenerator gen = new ModelGenerator();
        shader.bind();
        
//        vao.bindAll();
//        modelMatrix.load(new Matrix4f());
//        projectionViewMatrix.load(camera.getCombinedMatrix());
//        vao.renderElement(GL11.GL_TRIANGLE_STRIP, 0, VAO.RENDER_ALL);
//        vao.unbindByLastBind();
        
        shader.unbind();
        
        LWJGL.graphics.begin();
        
        window.getStage().render();
        
        font.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_TOP);
        LWJGL.graphics.textPaint(StaticColor.WHITE);
        LWJGL.graphics.textSize(32);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        
        LWJGL.graphics.end();
        
        LWJGL.graphics.begin();
        
        window.getStage().render();
        
        font.use();
        LWJGL.graphics.textAlign(NanoVG.NVG_ALIGN_LEFT | NanoVG.NVG_ALIGN_TOP);
        LWJGL.graphics.textPaint(StaticColor.WHITE);
        LWJGL.graphics.textSize(32);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        LWJGL.graphics.text(GameUtils.formatMem(GameUtils.getJavaHeap()) + "/" + GameUtils.formatMem(GameUtils.getMaxMemory()), 0, 0);
        
        LWJGL.graphics.end();
        
    }

    @Override
    protected void dispose() {
        super.dispose();
        vao.dispose();
        shader.dispose();
        
//        al.dispose();
//        source.dispose();
//        sound.dispose();
    }
    
    public static void main(String[] args) {
        new Test().run();
    }

    private static float[] normalize(float[] fs, int width, int height) {
        for (int i = 0; i < fs.length; i++) {
            fs[i] /= (i % 2 == 0? width:height);
        }
        return fs;
    }

    @Override
    protected void createWindow() {
        super.createWindow();
    }
    private boolean hideCursor = true;
    
    
}
