#version 140

uniform sampler2D texture;

in vec4 worldPos;
in vec4 worldNormal;
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

    vec3 ambient = dirLight.color * dirLight.intensity;

    float diffuseFactor = dot(worldNormal, vec4(-dirLight.position,1));
    vec3 diffuse;

    if(diffuseFactor > 0){
        diffuse = dirLight.color * dirLight.intensity * diffuseFactor;
    }
    else{
        diffuse = vec3(0,0,0);
    }

    //gl_FragColor = vec4(modelPos, 1) * vec4(ambient+diffuse,1);
    gl_FragColor = vec4(modelPos, 1);
}