#version 150 core

uniform vec3 diffuseUniform;

in vec3 worldPos;
in vec3 worldNormal;
in vec3 modelPos;

out vec3 worldPosition;
out vec3 diffuseColor;
out vec3 normal;

void main(void) {
    worldPosition = worldPos;
    normal = normalize(worldNormal);
    diffuseColor = diffuseUniform;
}