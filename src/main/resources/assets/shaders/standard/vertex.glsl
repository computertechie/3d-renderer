#version 150 core

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec3 worldPos;
out vec3 worldNormal;
out vec2 texCoord;

in vec3 position;
in vec3 normal;
in vec2 UV;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position,1.0);
	worldPos = (modelMatrix * vec4(position, 1.0)).xyz;
	worldNormal = (modelMatrix * vec4(normal, 0)).xyz;
	texCoord = UV;
}