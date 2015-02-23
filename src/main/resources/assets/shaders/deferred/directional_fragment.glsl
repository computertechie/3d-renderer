#version 150 core

struct Light{
    vec3 color;
    vec3 position;
    float intensity;
};

uniform sampler2D positionTexture;
uniform sampler2D colorTexture;
uniform sampler2D normalTexture;

uniform Light dirLight;

uniform vec3 eyePosition;

uniform float specularIntensity;
uniform float specularPower;

out vec4 fragColor;

void main(){
    ivec2 tc = ivec2(gl_FragCoord.xy);
    vec3 worldPosition = texelFetch(positionTexture, tc, 0).xyz;
    vec3 color = texelFetch(colorTexture, tc, 0).xyz;
    vec3 normal = normalize(texelFetch(normalTexture, tc, 0).xyz);

    vec3 ambientColor = vec3(dirLight.color * dirLight.intensity);
    vec3 diffuseColor = vec3(0,0,0);
    vec3 specularColor = vec3(0,0,0);

    float lambertian = dot(normal, dirLight.position);

    if(lambertian > 0){
        diffuseColor = dirLight.color * dirLight.intensity * lambertian;

        vec3 viewDirection = -normalize(eyePosition - worldPosition);
        vec3 halfDirection = normalize(dirLight.position + viewDirection);
        float specAngle = max(dot(halfDirection, normal), 0);

        specularColor = vec3(1,1,1) * pow(specAngle, 16);
    }
    fragColor = vec4(ambientColor + lambertian * color + specularColor, 1);
    //fragColor = vec4(normal, 0);
}