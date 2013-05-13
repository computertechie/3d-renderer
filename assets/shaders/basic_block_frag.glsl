#version 140

uniform sampler2D texture;

in vec4 worldPos;
in vec3 worldNormal;
in vec3 modelPos;
in vec2 out_tex;

struct Light{
    vec3 position;
    vec3 color;
    float intensity;
};

struct PointLight{
    Light light;
    float range;
};

uniform Light dirLight;

//uniform PointLights{
  //  PointLight lights[100];
//};

//uniform int numPointLights;

void main(void) {

    vec4 ambient = vec4(dirLight.color, 1f) * dirLight.intensity;

    float diffuseFactor = dot(worldNormal, -dirLight.position);
    vec4 diffuse;

    if(diffuseFactor > 0){
        diffuse = vec4(dirLight.color, 1f) * dirLight.intensity * diffuseFactor;
    }
    else{
        diffuse = vec4(1,0.5,0.25,0);
    }
    //gl_FragColor = vec4(modelPos, 0);

    gl_FragColor = vec4(0.5,0.5,0.5,1) * (diffuse);
}