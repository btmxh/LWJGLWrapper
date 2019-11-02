
package com.lwjglwrapper.utils.colors;

import com.lwjglwrapper.nanovg.paint.Paint;
import com.lwjglwrapper.nanovg.paint.types.FillPaint;
import com.lwjglwrapper.utils.math.MathUtils;
import java.awt.Color;
import java.io.Serializable;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.assimp.AIColor4D;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

public class StaticColor extends AbstractColor implements Serializable{

    public static final StaticColor TRANSPARENT = new StaticColor(0, 0, 0, 0);
    
    //Colors taken from javafx.scene.paint.Color class
    public static final StaticColor ALICEBLUE = new StaticColor(0.9411765f, 0.972549f, 1.0f);

    public static final StaticColor ANTIQUEWHITE = new StaticColor(0.98039216f, 0.92156863f, 0.84313726f);

    public static final StaticColor AQUA = new StaticColor(0.0f, 1.0f, 1.0f);

    public static final StaticColor AQUAMARINE = new StaticColor(0.49803922f, 1.0f, 0.83137256f);

    public static final StaticColor AZURE = new StaticColor(0.9411765f, 1.0f, 1.0f);

    public static final StaticColor BEIGE = new StaticColor(0.9607843f, 0.9607843f, 0.8627451f);

    public static final StaticColor BISQUE = new StaticColor(1.0f, 0.89411765f, 0.76862746f);

    public static final StaticColor BLACK = new StaticColor(0.0f, 0.0f, 0.0f);

    public static final StaticColor BLANCHEDALMOND = new StaticColor(1.0f, 0.92156863f, 0.8039216f);

    public static final StaticColor BLUE = new StaticColor(0.0f, 0.0f, 1.0f);

    public static final StaticColor BLUEVIOLET = new StaticColor(0.5411765f, 0.16862746f, 0.8862745f);

    public static final StaticColor BROWN = new StaticColor(0.64705884f, 0.16470589f, 0.16470589f);

    public static final StaticColor BURLYWOOD = new StaticColor(0.87058824f, 0.72156864f, 0.5294118f);

    public static final StaticColor CADETBLUE = new StaticColor(0.37254903f, 0.61960787f, 0.627451f);

    public static final StaticColor CHARTREUSE = new StaticColor(0.49803922f, 1.0f, 0.0f);

    public static final StaticColor CHOCOLATE = new StaticColor(0.8235294f, 0.4117647f, 0.11764706f);

    public static final StaticColor CORAL = new StaticColor(1.0f, 0.49803922f, 0.3137255f);

    public static final StaticColor CORNFLOWERBLUE = new StaticColor(0.39215687f, 0.58431375f, 0.92941177f);

    public static final StaticColor CORNSILK = new StaticColor(1.0f, 0.972549f, 0.8627451f);

    public static final StaticColor CRIMSON = new StaticColor(0.8627451f, 0.078431375f, 0.23529412f);

    public static final StaticColor CYAN = new StaticColor(0.0f, 1.0f, 1.0f);

    public static final StaticColor DARKBLUE = new StaticColor(0.0f, 0.0f, 0.54509807f);

    public static final StaticColor DARKCYAN = new StaticColor(0.0f, 0.54509807f, 0.54509807f);

    public static final StaticColor DARKGOLDENROD = new StaticColor(0.72156864f, 0.5254902f, 0.043137256f);

    public static final StaticColor DARKGRAY = new StaticColor(0.6627451f);

    public static final StaticColor DARKGREEN = new StaticColor(0.0f, 0.39215687f, 0.0f);

    public static final StaticColor DARKGREY  = DARKGRAY;

    public static final StaticColor DARKKHAKI = new StaticColor(0.7411765f, 0.7176471f, 0.41960785f);

    public static final StaticColor DARKMAGENTA = new StaticColor(0.54509807f, 0.0f, 0.54509807f);

    public static final StaticColor DARKOLIVEGREEN = new StaticColor(0.33333334f, 0.41960785f, 0.18431373f);

    public static final StaticColor DARKORANGE = new StaticColor(1.0f, 0.54901963f, 0.0f);

    public static final StaticColor DARKORCHID = new StaticColor(0.6f, 0.19607843f, 0.8f);

    public static final StaticColor DARKRED = new StaticColor(0.54509807f, 0.0f, 0.0f);

    public static final StaticColor DARKSALMON = new StaticColor(0.9137255f, 0.5882353f, 0.47843137f);

    public static final StaticColor DARKSEAGREEN = new StaticColor(0.56078434f, 0.7372549f, 0.56078434f);

    public static final StaticColor DARKSLATEBLUE = new StaticColor(0.28235295f, 0.23921569f, 0.54509807f);

    public static final StaticColor DARKSLATEGRAY = new StaticColor(0.18431373f, 0.30980393f, 0.30980393f);

    public static final StaticColor DARKSLATEGREY = DARKSLATEGRAY;

    public static final StaticColor DARKTURQUOISE = new StaticColor(0.0f, 0.80784315f, 0.81960785f);

    public static final StaticColor DARKVIOLET = new StaticColor(0.5803922f, 0.0f, 0.827451f);

    public static final StaticColor DEEPPINK = new StaticColor(1.0f, 0.078431375f, 0.5764706f);

    public static final StaticColor DEEPSKYBLUE = new StaticColor(0.0f, 0.7490196f, 1.0f);

    public static final StaticColor DIMGRAY = new StaticColor(0.4117647f, 0.4117647f, 0.4117647f);

    public static final StaticColor DIMGREY = DIMGRAY;

    public static final StaticColor DODGERBLUE = new StaticColor(0.11764706f, 0.5647059f, 1.0f);

    public static final StaticColor FIREBRICK = new StaticColor(0.69803923f, 0.13333334f, 0.13333334f);

    public static final StaticColor FLORALWHITE = new StaticColor(1.0f, 0.98039216f, 0.9411765f);

    public static final StaticColor FORESTGREEN = new StaticColor(0.13333334f, 0.54509807f, 0.13333334f);

    public static final StaticColor FUCHSIA = new StaticColor(1.0f, 0.0f, 1.0f);

    public static final StaticColor GAINSBORO = new StaticColor(0.8627451f, 0.8627451f, 0.8627451f);

    public static final StaticColor GHOSTWHITE = new StaticColor(0.972549f, 0.972549f, 1.0f);

    public static final StaticColor GOLD = new StaticColor(1.0f, 0.84313726f, 0.0f);

    public static final StaticColor GOLDENROD = new StaticColor(0.85490197f, 0.64705884f, 0.1254902f);

    public static final StaticColor GRAY = new StaticColor(0.5019608f, 0.5019608f, 0.5019608f);

    public static final StaticColor GREEN = new StaticColor(0.0f, 0.5019608f, 0.0f);

    public static final StaticColor GREENYELLOW = new StaticColor(0.6784314f, 1.0f, 0.18431373f);

    public static final StaticColor GREY = GRAY;

    public static final StaticColor HONEYDEW = new StaticColor(0.9411765f, 1.0f, 0.9411765f);

    public static final StaticColor HOTPINK = new StaticColor(1.0f, 0.4117647f, 0.7058824f);

    public static final StaticColor INDIANRED = new StaticColor(0.8039216f, 0.36078432f, 0.36078432f);

    public static final StaticColor INDIGO = new StaticColor(0.29411766f, 0.0f, 0.50980395f);

    public static final StaticColor IVORY = new StaticColor(1.0f, 1.0f, 0.9411765f);

    public static final StaticColor KHAKI = new StaticColor(0.9411765f, 0.9019608f, 0.54901963f);

    public static final StaticColor LAVENDER = new StaticColor(0.9019608f, 0.9019608f, 0.98039216f);

    public static final StaticColor LAVENDERBLUSH = new StaticColor(1.0f, 0.9411765f, 0.9607843f);

    public static final StaticColor LAWNGREEN = new StaticColor(0.4862745f, 0.9882353f, 0.0f);

    public static final StaticColor LEMONCHIFFON = new StaticColor(1.0f, 0.98039216f, 0.8039216f);

    public static final StaticColor LIGHTBLUE = new StaticColor(0.6784314f, 0.84705883f, 0.9019608f);

    public static final StaticColor LIGHTCORAL = new StaticColor(0.9411765f, 0.5019608f, 0.5019608f);

    public static final StaticColor LIGHTCYAN = new StaticColor(0.8784314f, 1.0f, 1.0f);

    public static final StaticColor LIGHTGOLDENRODYELLOW = new StaticColor(0.98039216f, 0.98039216f, 0.8235294f);

    public static final StaticColor LIGHTGRAY = new StaticColor(0.827451f, 0.827451f, 0.827451f);

    public static final StaticColor LIGHTGREEN = new StaticColor(0.5647059f, 0.93333334f, 0.5647059f);

    public static final StaticColor LIGHTGREY = LIGHTGRAY;

    public static final StaticColor LIGHTPINK = new StaticColor(1.0f, 0.7137255f, 0.75686276f);

    public static final StaticColor LIGHTSALMON = new StaticColor(1.0f, 0.627451f, 0.47843137f);

    public static final StaticColor LIGHTSEAGREEN = new StaticColor(0.1254902f, 0.69803923f, 0.6666667f);

    public static final StaticColor LIGHTSKYBLUE = new StaticColor(0.5294118f, 0.80784315f, 0.98039216f);

    public static final StaticColor LIGHTSLATEGRAY = new StaticColor(0.46666667f, 0.53333336f, 0.6f);

    public static final StaticColor LIGHTSLATEGREY       = LIGHTSLATEGRAY;

    public static final StaticColor LIGHTSTEELBLUE = new StaticColor(0.6901961f, 0.76862746f, 0.87058824f);

    public static final StaticColor LIGHTYELLOW = new StaticColor(1.0f, 1.0f, 0.8784314f);

    public static final StaticColor LIME = new StaticColor(0.0f, 1.0f, 0.0f);

    public static final StaticColor LIMEGREEN = new StaticColor(0.19607843f, 0.8039216f, 0.19607843f);

    public static final StaticColor LINEN = new StaticColor(0.98039216f, 0.9411765f, 0.9019608f);

    public static final StaticColor MAGENTA = new StaticColor(1.0f, 0.0f, 1.0f);

    public static final StaticColor MAROON = new StaticColor(0.5019608f, 0.0f, 0.0f);

    public static final StaticColor MEDIUMAQUAMARINE = new StaticColor(0.4f, 0.8039216f, 0.6666667f);

    public static final StaticColor MEDIUMBLUE = new StaticColor(0.0f, 0.0f, 0.8039216f);

    public static final StaticColor MEDIUMORCHID = new StaticColor(0.7294118f, 0.33333334f, 0.827451f);

    public static final StaticColor MEDIUMPURPLE = new StaticColor(0.5764706f, 0.4392157f, 0.85882354f);

    public static final StaticColor MEDIUMSEAGREEN = new StaticColor(0.23529412f, 0.7019608f, 0.44313726f);

    public static final StaticColor MEDIUMSLATEBLUE = new StaticColor(0.48235294f, 0.40784314f, 0.93333334f);

    public static final StaticColor MEDIUMSPRINGGREEN = new StaticColor(0.0f, 0.98039216f, 0.6039216f);

    public static final StaticColor MEDIUMTURQUOISE = new StaticColor(0.28235295f, 0.81960785f, 0.8f);

    public static final StaticColor MEDIUMVIOLETRED = new StaticColor(0.78039217f, 0.08235294f, 0.52156866f);

    public static final StaticColor MIDNIGHTBLUE = new StaticColor(0.09803922f, 0.09803922f, 0.4392157f);

    public static final StaticColor MINTCREAM = new StaticColor(0.9607843f, 1.0f, 0.98039216f);

    public static final StaticColor MISTYROSE = new StaticColor(1.0f, 0.89411765f, 0.88235295f);

    public static final StaticColor MOCCASIN = new StaticColor(1.0f, 0.89411765f, 0.70980394f);

    public static final StaticColor NAVAJOWHITE = new StaticColor(1.0f, 0.87058824f, 0.6784314f);

    public static final StaticColor NAVY = new StaticColor(0.0f, 0.0f, 0.5019608f);

    public static final StaticColor OLDLACE = new StaticColor(0.99215686f, 0.9607843f, 0.9019608f);

    public static final StaticColor OLIVE = new StaticColor(0.5019608f, 0.5019608f, 0.0f);

    public static final StaticColor OLIVEDRAB = new StaticColor(0.41960785f, 0.5568628f, 0.13725491f);

    public static final StaticColor ORANGE = new StaticColor(1.0f, 0.64705884f, 0.0f);

    public static final StaticColor ORANGERED = new StaticColor(1.0f, 0.27058825f, 0.0f);

    public static final StaticColor ORCHID = new StaticColor(0.85490197f, 0.4392157f, 0.8392157f);

    public static final StaticColor PALEGOLDENROD = new StaticColor(0.93333334f, 0.9098039f, 0.6666667f);

    public static final StaticColor PALEGREEN = new StaticColor(0.59607846f, 0.9843137f, 0.59607846f);

    public static final StaticColor PALETURQUOISE = new StaticColor(0.6862745f, 0.93333334f, 0.93333334f);

    public static final StaticColor PALEVIOLETRED = new StaticColor(0.85882354f, 0.4392157f, 0.5764706f);

    public static final StaticColor PAPAYAWHIP = new StaticColor(1.0f, 0.9372549f, 0.8352941f);

    public static final StaticColor PEACHPUFF = new StaticColor(1.0f, 0.85490197f, 0.7254902f);

    public static final StaticColor PERU = new StaticColor(0.8039216f, 0.52156866f, 0.24705882f);

    public static final StaticColor PINK = new StaticColor(1.0f, 0.7529412f, 0.79607844f);

    public static final StaticColor PLUM = new StaticColor(0.8666667f, 0.627451f, 0.8666667f);

    public static final StaticColor POWDERBLUE = new StaticColor(0.6901961f, 0.8784314f, 0.9019608f);

    public static final StaticColor PURPLE = new StaticColor(0.5019608f, 0.0f, 0.5019608f);

    public static final StaticColor RED = new StaticColor(1.0f, 0.0f, 0.0f);

    public static final StaticColor ROSYBROWN = new StaticColor(0.7372549f, 0.56078434f, 0.56078434f);

    public static final StaticColor ROYALBLUE = new StaticColor(0.25490198f, 0.4117647f, 0.88235295f);

    public static final StaticColor SADDLEBROWN = new StaticColor(0.54509807f, 0.27058825f, 0.07450981f);

    public static final StaticColor SALMON = new StaticColor(0.98039216f, 0.5019608f, 0.44705883f);

    public static final StaticColor SANDYBROWN = new StaticColor(0.95686275f, 0.6431373f, 0.3764706f);

    public static final StaticColor SEAGREEN = new StaticColor(0.18039216f, 0.54509807f, 0.34117648f);

    public static final StaticColor SEASHELL = new StaticColor(1.0f, 0.9607843f, 0.93333334f);

    public static final StaticColor SIENNA = new StaticColor(0.627451f, 0.32156864f, 0.1764706f);

    public static final StaticColor SILVER = new StaticColor(0.7529412f, 0.7529412f, 0.7529412f);

    public static final StaticColor SKYBLUE = new StaticColor(0.5294118f, 0.80784315f, 0.92156863f);

    public static final StaticColor SLATEBLUE = new StaticColor(0.41568628f, 0.3529412f, 0.8039216f);

    public static final StaticColor SLATEGRAY = new StaticColor(0.4392157f, 0.5019608f, 0.5647059f);

    public static final StaticColor SLATEGREY            = SLATEGRAY;

    public static final StaticColor SNOW = new StaticColor(1.0f, 0.98039216f, 0.98039216f);

    public static final StaticColor SPRINGGREEN = new StaticColor(0.0f, 1.0f, 0.49803922f);

    public static final StaticColor STEELBLUE = new StaticColor(0.27450982f, 0.50980395f, 0.7058824f);

    public static final StaticColor TAN = new StaticColor(0.8235294f, 0.7058824f, 0.54901963f);

    public static final StaticColor TEAL = new StaticColor(0.0f, 0.5019608f, 0.5019608f);

    public static final StaticColor THISTLE = new StaticColor(0.84705883f, 0.7490196f, 0.84705883f);

    public static final StaticColor TOMATO = new StaticColor(1.0f, 0.3882353f, 0.2784314f);

    public static final StaticColor TURQUOISE = new StaticColor(0.2509804f, 0.8784314f, 0.8156863f);

    public static final StaticColor VIOLET = new StaticColor(0.93333334f, 0.50980395f, 0.93333334f);

    public static final StaticColor WHEAT = new StaticColor(0.9607843f, 0.87058824f, 0.7019608f);

    public static final StaticColor WHITE = new StaticColor(1.0f, 1.0f, 1.0f);

    public static final StaticColor WHITESMOKE = new StaticColor(0.9607843f, 0.9607843f, 0.9607843f);
    
    public static final StaticColor YELLOW = new StaticColor(1.0f, 1.0f, 0.0f);
    
    public static final StaticColor YELLOWGREEN = new StaticColor(0.6039216f, 0.8039216f, 0.19607843f);

    public final float r, g, b, a;

    public StaticColor(float r, float g, float b, float a) {
        r = MathUtils.clamp(0f, r, 1f);
        g = MathUtils.clamp(0f, g, 1f);
        b = MathUtils.clamp(0f, b, 1f);
        a = MathUtils.clamp(0f, a, 1f);
        
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public StaticColor(float r, float g, float b) {
        this(r, g, b, 1);
    }
    
    public StaticColor(float gray, float a) {
        this(gray, gray, gray, a);
    }
    
    public StaticColor(float gray) {
        this(gray, 1);
    }

    public StaticColor(StaticColor color) {
        this(color.r, color.g, color.b, color.a);
    }

    @Override
    public float getRed() {
        return r;
    }

    @Override
    public float getGreen() {
        return g;
    }

    @Override
    public float getBlue() {
        return b;
    }

    @Override
    public float getAlpha() {
        return a;
    }
    
    
    
    public static StaticColor hsv(float h, float s, float v) {
        return awt(Color.getHSBColor(h, s, v));
    }
    
    public static StaticColor array(float[] comp) {
        switch (comp.length) {
            case 0: return null;
            case 1: return new StaticColor(comp[0]);
            case 2: return new StaticColor(comp[0], comp[1]);
            case 3: return new StaticColor(comp[0], comp[1], comp[2]);
            default:return new StaticColor(comp[0], comp[1], comp[2], comp[3]);
        }
    }
    
    public static StaticColor awt(Color color) {
        return StaticColor.array(color.getComponents(new float[4]));
    }
    
    public static StaticColor nanovg(NVGColor nvgColor) {
        return new StaticColor(nvgColor.r(), nvgColor.g(), nvgColor.b(), nvgColor.a());
    }
    
    public static StaticColor vec3(Vector3f vec3) {
        return new StaticColor(vec3.x, vec3.y, vec3.z);
    }
    
    public static StaticColor vec4(Vector4f vec4) {
        return new StaticColor(vec4.x, vec4.y, vec4.z, vec4.w);
    }
    
    public static StaticColor assimp(AIColor4D assimpColor) {
        return new StaticColor(assimpColor.r(), assimpColor.g(), assimpColor.b(), assimpColor.a());
    }
    
    public StaticColor brighter() {
        return awt(toAWTColor().brighter());
    }
    
    public StaticColor darker() {
        return awt(toAWTColor().darker());
    }

    @Override
    public StaticColor mulAlpha(float alpha) {
        return new StaticColor(r, g, b, a * alpha);
    }

    
}
