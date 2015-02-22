struct Light{
    vec3 color;
    vec3 position;
    float intensity;
};

uniform Light dirLight;

 vec4 ambient = vec4(dirLight.color * dirLight.intensity,0);
    float diffuseFactor = dot(worldNormal, -dirLight.position);
    vec4 diffuse;

    if(diffuseFactor > 0){
        diffuse = vec4(dirLight.color, 0) * dirLight.intensity * diffuseFactor;
    }
    else{
        diffuse = vec4(0,0,0,0);
    }

    fragColor = vec4(0.25,0.5,0.75,0);
    if(all(equal(diffuseColor,vec3(-1,-1,-1)))){
        fragColor = texture(texMap, out_tex) * (diffuse + ambient);
    }
    else{
        fragColor = vec4(diffuseColor,0) * (diffuse + ambient);
    }