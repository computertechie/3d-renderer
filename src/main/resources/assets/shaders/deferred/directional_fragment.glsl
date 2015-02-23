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

    vec3 ambientColor = color / 0.5;
    vec3 specularColor = vec3(1,1,1);
    vec3 lightDirection = normalize(dirLight.position - worldPosition);

    float lambertian = max(dot(lightDirection, normal), 0);
    float specular = 0;

    if(lambertian > 0){
        vec3 viewDirection = normalize(- worldPosition);

        vec3 halfDirection = normalize(lightDirection + viewDirection);
        float specAngle = max(dot(halfDirection, normal), 0);

        specular = pow(specAngle, 16);
    }
    fragColor = vec4(ambientColor + lambertian * color + specular * specularColor, 1);
}