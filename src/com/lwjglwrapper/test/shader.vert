#version 410 core

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoord;
layout (location = 2) in vec3 normal;

out vec3 color;
out vec2 pass_texCoord;

uniform mat4 projectionViewMatrix, modelMatrix;

void main() {
    gl_Position = projectionViewMatrix * modelMatrix * vec4(position, 1.0);
    pass_texCoord = texCoord;
    color = vec3((position + 1.0) / 2.0);
}
