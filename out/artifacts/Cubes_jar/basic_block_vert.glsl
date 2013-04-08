# version 140

uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelMatrix;

out vec4 pos;
in vec3 position;

void main(void) {
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position,1.0);

	pos = vec4(position, 1.0);//copy to go to fragment for colour based on position - simplest way
}