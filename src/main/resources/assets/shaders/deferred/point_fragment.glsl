#version 150 core

struct Light{
    vec3 color;
    vec3 position;
    float intensity;
    float constant;
    float linear;
    float quadratic;
};

uniform sampler2D positionTexture;
uniform sampler2D colorTexture;
uniform sampler2D normalTexture;

uniform Light pointLight;

uniform vec3 eyePosition;

uniform float specularIntensity;
uniform float specularPower;

out vec4 fragColor;

void main(){
    ivec2 tc = ivec2(gl_FragCoord.xy);
    vec3 worldPosition = texelFetch(positionTexture, tc, 0).xyz;
    vec3 color = texelFetch(colorTexture, tc, 0).xyz;
    vec3 normal = normalize(texelFetch(normalTexture, tc, 0).xyz);

    vec3 ambientColor = color * 0.5;

    //fragColor = vec4(normal, 1);
    //vec3 specularColor = vec3(1,1,1);
    vec3 lightDirection = normalize(pointLight.position - worldPosition);

    float diff = max(dot(normal, lightDirection), 0);
    vec3 diffuseColor = diff * color;

    float specular = 0;
    vec3 viewDirection = normalize(eyePosition - worldPosition);
    vec3 reflectDir = reflect(-lightDirection, normal);
    float spec = pow(max(dot(viewDirection, reflectDir), 0), specularPower);
    vec3 specularColor = spec * color;

    float distance = length(pointLight.position - worldPosition);
    float attenuation = 1.0f / (pointLight.constant + pointLight.linear * distance + pointLight.quadratic * (distance * distance));

    ambientColor *= attenuation;
    diffuseColor *= attenuation;
    specularColor *= attenuation;

    fragColor = vec4(ambientColor*pointLight.intensity + diffuseColor*pointLight.intensity  + specularColor*pointLight.intensity , 1);
}