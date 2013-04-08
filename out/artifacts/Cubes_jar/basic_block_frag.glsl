#version 140

//out vec4 out_Color;// gl_FragColor;
in vec4 pos;

void main(void) {
	// Override out_Color with our texture pixel
    gl_FragColor = pos; //colours based on vertex position - gl_* are builtin variables
}