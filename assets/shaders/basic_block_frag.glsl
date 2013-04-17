#version 140

uniform sampler2D texture;

in vec4 pos;
in vec2 out_tex;

void main(void) {
//    gl_FragColor = texture2D(texture, out_tex);
    gl_FragColor = pos;
}