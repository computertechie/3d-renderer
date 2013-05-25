package tk.snowmew.cubes.render;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * User: Pepper
 * Date: 4/23/13
 * Time: 6:43 PM
 * Project: Cubes
 */
public class Material {

    private float diffuseR, diffuseG, diffuseB;
    private float specularR, specularG, specularB, specularPower;
    private float ambientR, ambientG, ambientB;
    private float transparency;
    private float transFilterR, transFilterG, transFilterB;
    private float refraction;
    private int illumModel;
    private String name, ambientMap, diffuseMap, specColourMap, specHighlightMap, alphaMap, bumpMap;
    private boolean diffuseMapped=false, ambientMapped=false, specularMapped=false, alphaMapped=false, bumpMapped=false;

    public Material(){
    }

    public boolean isDiffuseMapped(){
        return diffuseMapped;
    }

    public void setDiffuseMapped(boolean bool){
        diffuseMapped = bool;
    }

    public void setDiffuse(float r, float g, float b){
        diffuseR = r;
        diffuseG = g;
        diffuseB = b;
    }

    public void setSpecular(float r, float g, float b){
        specularR = r;
        specularG = g;
        specularB = b;
    }

    public void setAmbient(float r, float g, float b){
        ambientR = r;
        ambientG = g;
        ambientB = b;
    }

    public void setSpecPower(float power){
        specularPower = power;
    }

    public void setTransparency(float trans){
        transparency = trans;
    }

    public void setTransFilter(float r, float g, float b){
        transFilterR = r;
        transFilterG = g;
        transFilterB = b;
    }

    public void setRefraction(float ref){
        refraction = ref;
    }

    public void setIllumModel(int model){
        illumModel = model;
    }

    public void setName(String n){
        name = n;
    }

    public void setAmbientMap(String map){
        ambientMap = map;
    }

    public void setDiffuseMap(String map){
        diffuseMap = map;
    }

    public void setSpecColourMap(String map){
        specColourMap = map;
    }

    public void setSpecHighlightMap(String map){
        specHighlightMap = map;
    }

    public void setAlphaMap(String map){
        alphaMap = map;
    }

    public void setBumpMap(String map){
        bumpMap = map;
    }


    public String getName(){
        return name;
    }

    public float getDiffuseR() {
        return diffuseR;
    }

    public float getDiffuseG() {
        return diffuseG;
    }

    public float getDiffuseB() {
        return diffuseB;
    }

    public FloatBuffer getDiffuseColor(){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(3);
        buffer.put(diffuseR);
        buffer.put(diffuseG);
        buffer.put(diffuseB);
        buffer.flip();
        return buffer;
    }

    public float getSpecularR() {
        return specularR;
    }

    public float getSpecularG() {
        return specularG;
    }

    public float getSpecularB() {
        return specularB;
    }

    public float getSpecularPower() {
        return specularPower;
    }

    public float getAmbientR() {
        return ambientR;
    }

    public float getAmbientG() {
        return ambientG;
    }

    public float getAmbientB() {
        return ambientB;
    }

    public float getTransparency() {
        return transparency;
    }

    public float getTransFilterR() {
        return transFilterR;
    }

    public float getTransFilterG() {
        return transFilterG;
    }

    public float getTransFilterB() {
        return transFilterB;
    }

    public float getRefraction() {
        return refraction;
    }

    public int getIllumModel() {
        return illumModel;
    }

    public String getAmbientMap() {
        return ambientMap;
    }

    public String getDiffuseMap() {
        return diffuseMap;
    }

    public String getSpecColourMap() {
        return specColourMap;
    }

    public String getSpecHighlightMap() {
        return specHighlightMap;
    }

    public String getAlphaMap() {
        return alphaMap;
    }

    public String getBumpMap() {
        return bumpMap;
    }
}
