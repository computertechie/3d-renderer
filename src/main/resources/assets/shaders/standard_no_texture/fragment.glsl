#version 150 core

uniform vec3 diffuseColor = vec3(-1,-1,-1);

in vec4 worldPos;
in vec3 worldNormal;
in vec3 modelPos;

out vec4 fragColor;

struct Light{
    vec3 color;
    vec3 position;
    float intensity;
};

struct PointLight{
    Light light;
    float range;
};

uniform Light dirLight;

/*uniform PointLights{
    PointLight lights[100];
};

uniform int numPointLights;
*/
void main(void) {

    vec4 ambient = vec4(dirLight.color * dirLight.intensity,0);
    float diffuseFactor = dot(worldNormal, -dirLight.position);
    vec4 diffuse;

    if(diffuseFactor > 0){
        diffuse = vec4(dirLight.color, 0) * dirLight.intensity * diffuseFactor;
    }
    else{
        diffuse = vec4(0,0,0,0);
    }

    fragColor = vec4(diffuseColor,0) * (diffuse + ambient);
}