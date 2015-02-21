package link.snowcat.cubes;

import com.google.gson.Gson;
import link.snowcat.cubes.entity.Entity;
import link.snowcat.cubes.generated.ShaderProgram;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector3f;
import link.snowcat.cubes.lights.DirectionalLight;
import link.snowcat.cubes.render.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Pepper
 * Date: 4/6/13
 * Time: 7:22 PM
 * Project: link.snowcat.cubes.Cubes
 */

public class Cubes {
    final int TIME_CONVERSION = 1_000_000;
    static int FRAMES = 60;
    public static Renderer renderInstance = Renderer.getInstance();
    public static TextureManager textureManagerInstance = TextureManager.getInstance();
    public static MaterialManager materialManagerInstance = MaterialManager.getInstance();
    public static ShaderProgramManager shaderProgramManager = ShaderProgramManager.getInstance();
    public static ModelManager modelManagerInstance = ModelManager.getInstance();

    private boolean quit = false, wireframe = false;
    Camera camera = new Camera(0,0);
    Entity ring, plane;
    public DirectionalLight sun = new DirectionalLight(new Vector3f(-10,0,0), new Vector3f(1,1,1), 0.1f);
    int width =854, height=480, fps = 0;
    float mouseSensitivity = 0.5f;
    long physicsTick = 0, renderTick = 0;

    double designatedTickTime = 15, currentTime = System.nanoTime()/TIME_CONVERSION, fpsTime = currentTime, availableTime = 0, frameTime = 0;

    public static void main(String[] args){
        if(args.length > 0) {
            FRAMES = Integer.parseInt(args[0]);
        }
        Cubes cube = new Cubes();
        cube.tick();
    }

    public Cubes(){
        createDisplay();
        System.out.println("oGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("GLSL Version: "+GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));
        System.out.println("Vendor: "+GL11.glGetString(GL11.GL_VENDOR));
        System.out.println("Renderer: " + GL11.glGetString(GL11.GL_RENDERER));
        renderInstance.setCamera(camera);
        renderInstance.setShaderProgramManager(shaderProgramManager);
        renderInstance.setCubeInstance(this);
        renderInstance.createProjectionMatrix();
        camera.setPosition(new Vector3f(0, 1, 0));

        Gson gson = new Gson();
        plane = gson.fromJson(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/assets/json/entities/jet.json"))), Entity.class);
        plane.initialize();
        ring = gson.fromJson(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/assets/json/models/disc.json"))), Entity.class);
        ring.initialize();
    }

    public void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create(new PixelFormat(), new ContextAttribs(3,2).withProfileCore(true).withForwardCompatible(true));
            Display.setResizable(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glViewport(0, 0, width, height);
            GL11.glClearColor(0.5f, 0.5f, 0.5f, 0);
            Mouse.create();
            Mouse.setGrabbed(true);
            Keyboard.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, width, height);
    }

    public void checkForResize(){
        if(width != Display.getWidth() || height != Display.getHeight()){
            width = Display.getWidth();
            height = Display.getHeight();
            renderInstance.createProjectionMatrix();
            GL11.glViewport(0, 0, width, height);
        }
    }

    public int getWidth(){ return width; }

    public int getHeight(){
        return height;
    }

    public void tick(){
        double newTime = 0;
        while(!Display.isCloseRequested()){
            newTime = System.nanoTime()/TIME_CONVERSION;
            frameTime = newTime - currentTime;
            if(frameTime > 25)
                frameTime = 25;

            if(currentTime - fpsTime > 1000){
                Display.setTitle("FPS: "+fps);
                fps = 0;
                fpsTime += 1000;
            }
            currentTime = newTime;
            availableTime += frameTime;

            while(availableTime >= designatedTickTime){
                if(quit) {
                    Display.destroy();
                    return;
                }

                physicsTick++;

                getInput();
                if(physicsTick % 100 == 0){
                    float newX=0, newY=0;
                    if(sun.getDirection().x == 10)
                        newX = -9.9f;
                    else
                        newX = sun.getDirection().x+0.1f;

                    newY = (float)Math.sqrt(100-(Math.pow(newX,2)));
                    sun.setDirection(new Vector3f(newX, -newY, 0));
                }

                availableTime -= designatedTickTime;
            }

            fps++;
            renderTick++;
            checkForResize();
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
            renderInstance.render(ring.getModelName());
            renderInstance.render(plane.getModelName());
            Display.update();
            Display.sync(FRAMES);
        }
    }

    public void getInput(){
        Vector3f movement = new Vector3f();

        float speedModifier = 1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            quit = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
            speedModifier = 2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            movement.setZ(movement.getZ() + (0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            movement.setZ(movement.getZ() + (-0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            movement.setX(movement.getX() + (0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            movement.setX(movement.getX() + (-0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            movement.setY(movement.getY() + (-0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            movement.setY(movement.getY() + (0.1f * speedModifier));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_T)) {
            Mouse.setGrabbed(!Mouse.isGrabbed());
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F4)){
            try {
                Display.setFullscreen(!Display.isFullscreen());
            } catch (LWJGLException e) {
                e.printStackTrace();
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_1)){
            if(wireframe){
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            }
            else{
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            }
            wireframe = !wireframe;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_0)){
            camera.zeroBearing();
            camera.zeroPitch();
        }

        if(Mouse.isGrabbed()) {
            int dx = (int)(Mouse.getDX()*mouseSensitivity), dy = (int)(Mouse.getDY()*mouseSensitivity);
            camera.changePitch(dy);
            camera.changeBearing(dx);
        }
        camera.update(movement);
    }

    static{
        Map<String, String> envMap = new HashMap<String, String>();
        envMap.put("create", "true");

        StringBuilder nativePath = new StringBuilder("/natives");

        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("windows")){
            nativePath.append("/windows");
        }
        else if(osName.contains("mac")){
            nativePath.append("/osx");
        }
        else if(osName.contains("nux")){
            nativePath.append("/linux");
        }
        else{
            throw new RuntimeException("The hell OS are you using?");
        }

        try (FileSystem fileSystem = FileSystems.newFileSystem(Cubes.class.getResource("/natives").toURI(), envMap)){
            Path tempDir = Files.createTempDirectory("3d_renderer");
            Path nativesPath = fileSystem.getPath(nativePath.toString());
            Path destination;
            File tempFile = new File(tempDir.toUri());
                tempFile.deleteOnExit();
            String filePath;

            try(DirectoryStream<Path> stream = Files.newDirectoryStream(nativesPath)){
                for(Path file : stream){
                    filePath = file.toString();
                    if(filePath.contains("META"))
                        continue;

                    filePath = filePath.substring(filePath.lastIndexOf('/'));

                    destination = FileSystems.getDefault().getPath(tempDir.toString(), filePath);
                    Files.copy(file, destination);

                    tempFile = new File(destination.toUri());
                    tempFile.deleteOnExit();
                }
            }
            System.setProperty("org.lwjgl.librarypath", tempDir.toString());
        }
        catch(URISyntaxException uriSyntaxException){
            uriSyntaxException.printStackTrace();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}