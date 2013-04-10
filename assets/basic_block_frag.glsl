#version 140

uniform sampler2D texture;

in vec4 pos;
in vec2 out_tex;

void main(void) {
	// Override out_Color with our texture pixel
    gl_FragColor = texture2D(texture, out_tex);  //pos; colours based on vertex position - gl_* are builtin variables
}