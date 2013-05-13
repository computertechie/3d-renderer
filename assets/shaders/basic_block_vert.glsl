#version 140

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec4 worldPos;
out vec3 modelPos;
out vec4 worldNormal;
out vec2 out_tex;

in vec3 position;
in vec3 normal;
in vec2 in_tex;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position,1.0);
	worldPos = modelMatrix * vec4(position, 1.0);
	worldNormal = modelMatrix * vec4(normalize(normal),1);
    modelPos = position;
	out_tex = in_tex;
}