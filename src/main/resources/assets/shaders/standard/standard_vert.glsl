#version 150 core

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec4 worldPos;
out vec3 modelPos;
out vec3 worldNormal;
out vec2 out_tex;

in vec3 position;
in vec3 normal;
in vec2 in_tex;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position,1.0);
	worldPos = modelMatrix * vec4(position, 1.0);
	worldNormal = (modelMatrix * vec4(normal, 0)).xyz;
    modelPos = position;
	out_tex = in_tex;
}