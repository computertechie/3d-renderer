#version 140

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec4 pos;
out vec2 out_tex;

in vec3 position;
in vec2 in_tex;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position,1.0);
	pos = vec4(position, 1.0);

	out_tex = in_tex;
}