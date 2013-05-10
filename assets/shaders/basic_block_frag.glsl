#version 140

uniform sampler2D texture;

in vec4 pos;
in vec2 out_tex;

struct Light{
    vec3 position;
    int color;
};

struct PointLight{
    Light light;
    float range;
};

uniform Light dirLight;

uniform PointLights{
    PointLight lights[];
};

uniform int numPointLights;

void main(void) {
//    gl_FragColor = texture2D(texture, out_tex);
    gl_FragColor = pos;
}