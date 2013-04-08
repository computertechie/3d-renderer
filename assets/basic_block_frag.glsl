#version 140

in vec4 pos;

void main(void) {
	// Override out_Color with our texture pixel
    gl_FragColor = pos; //colours based on vertex position - gl_* are builtin variables
}