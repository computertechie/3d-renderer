#version 140

//uniform sampler2D texture;

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

    vec3 ambient = dirLight.color * dirLight.intensity;
    //vec3 ambient = vec3(0.25,0.5,0.75) * dirLight.intensity;

    float diffuseFactor = dot(worldNormal, -dirLight.position);
    vec4 diffuse;

    if(diffuseFactor > 0){
        diffuse = vec4(dirLight.color, 1f) * dirLight.intensity * diffuseFactor;
    }
    else{
        diffuse = vec4(0,0,0,0);
    }

    //gl_FragColor = vec4(dirLight.color,0);// * diffuse;
    //gl_FragColor = diffuse;// + ambient;
    //gl_FragColor = vec4(ambient,0);
    //gl_FragColor = vec4(diffuseFactor,0,0,0);
    gl_FragColor = vec4(1,1,1,0) * (diffuse +ambient);
    //gl_FragColor = vec4(dirLight.color, 0);
}