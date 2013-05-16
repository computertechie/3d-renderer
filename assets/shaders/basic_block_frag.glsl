#version 140

//uniform sampler2D texture;

in vec4 worldPos;
in vec3 worldNormal;
in vec3 modelPos;
in vec2 out_tex;

struct Light{
    vec3 position;
    float intensity;
    vec3 color;
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

    float diffuseFactor = dot(worldNormal, -dirLight.position);
    vec4 diffuse;

    if(diffuseFactor > 0){
        //diffuse = vec4(dirLight.color, 1f) * dirLight.intensity * diffuseFactor;
        diffuse = vec4(modelPos,0) * diffuseFactor;
    }
    else{
        diffuse = vec4(0,0,0,0);
    }

    //gl_FragColor = vec4(dirLight.color,0);// * diffuse;
    //gl_FragColor = diffuse;// + ambient;
    gl_FragColor = vec4(diffuseFactor,0,0,0);
    //gl_FragColor = vec4(0.25,0.5,0.75,0) * diffuse;//+ambient);
}