#version 410 core

in vec2 pass_texCoord;
in vec3 color;

out vec4 out_color;

uniform sampler2D tex;

void main() {
    out_color = vec4(1.0);
}
