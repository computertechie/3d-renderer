#version 150 core

struct Light{
    vec3 color;
    float intensity;

    vec3 position;
    vec3 direction;
    float cutOff;

    float constant;
    float linear;
    float quadratic;
};

uniform Light spotLight;

uniform sampler2D positionTexture;
uniform sampler2D colorTexture;
uniform sampler2D normalTexture;

uniform vec3 eyePosition;

uniform float specularIntensity;
uniform float specularPower;

out vec4 fragColor;

void main(){
    ivec2 tc = ivec2(gl_FragCoord.xy);
    vec3 worldPosition = texelFetch(positionTexture, tc, 0).xyz;
    vec3 color = texelFetch(colorTexture, tc, 0).xyz;
    vec3 normal = normalize(texelFetch(normalTexture, tc, 0).xyz);

    vec3 lightDirection = normalize(spotLight.position - worldPosition);
    float theta = dot(lightDirection, normalize(-spotLight.direction));

    vec3 ambientColor = color * 0.5;
    if(theta < spotLight.cutOff){

            float diff = max(dot(normal, lightDirection), 0);
            vec3 diffuseColor = diff * color;

            float specular = 0;
            vec3 viewDirection = normalize(eyePosition - worldPosition);
            vec3 reflectDir = reflect(-lightDirection, normal);
            float spec = pow(max(dot(viewDirection, reflectDir), 0), specularPower);
            vec3 specularColor = spec * color;

            float distance = length(spotLight.position - worldPosition);
            float attenuation = 1.0f / (spotLight.constant + spotLight.linear * distance + spotLight.quadratic * (distance * distance));

            //ambientColor *= attenuation;
            //diffuseColor *= attenuation;
            //specularColor *= attenuation;

            fragColor = vec4(ambientColor*spotLight.intensity + diffuseColor*spotLight.intensity  + specularColor*spotLight.intensity , 1);
    }
    else{
        fragColor = vec4(ambientColor, 1);
    }
}