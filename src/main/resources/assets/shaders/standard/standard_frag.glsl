#version 130

uniform sampler2D texture;
uniform vec3 diffuseColor = vec3(-1,-1,-1);

in vec4 worldPos;
in vec3 worldNormal;
in vec3 modelPos;
in vec2 out_tex;

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

    gl_FragColor = vec4(0.25,0.5,0.75,0);
    if(all(equal(diffuseColor,vec3(-1,-1,-1)))){
        gl_FragColor = texture2D(texture,out_tex) * (diffuse + ambient);
    }
    else{
        gl_FragColor = vec4(diffuseColor,0) * (diffuse + ambient);
    }
}