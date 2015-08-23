package link.snowcat.cubes;

import com.google.gson.Gson;
import link.snowcat.cubes.entity.Entity;
import link.snowcat.cubes.lights.Light;
import link.snowcat.cubes.lights.PointLight;
import link.snowcat.cubes.lights.SpotLight;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
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
    private static boolean sync = false;

    public GBuffer gBuffer;

    Camera camera = new Camera(0,0);
    Entity ring, plane, plane2;

    public DirectionalLight sun = new DirectionalLight(new Vector3f(1,1,1), 0.75f, 60, 70);
    PointLight pointLight = new PointLight(new Vector3f(-1, 10, -1), new Vector3f(1,1,0), 1f, 1, 0.045f, 0.0075f);
    SpotLight spotLight = new SpotLight(new Vector3f(-1, 10, -1), new Vector3f(1,1,0), new Vector3f(1,1,0), 15, 1f, 1, 0.045f, 0.0075f);

    int width =854, height=480, fps = 0, smoothCount = 0, frametimeQuery;
    float mouseSensitivity = 0.5f;
    long physicsTick = 0, renderTick = 0;

    double designatedTickTime = 15, currentTime = System.nanoTime()/TIME_CONVERSION, fpsTime = currentTime, availableTime = 0, frameTime = 0;

    public static void main(String[] args){
        if(args.length > 0) {
            sync = true;
            FRAMES = Integer.parseInt(args[0]);
        }
        Cubes cube = new Cubes();
        cube.tick();
    }

    public Cubes(){
        createDisplay();
        System.out.println("oGL Version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("GLSL Version: " + GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION));
        System.out.println("Vendor: " + GL11.glGetString(GL11.GL_VENDOR));
        System.out.println("Renderer: " + GL11.glGetString(GL11.GL_RENDERER));
        renderInstance.setCamera(camera);
        renderInstance.setShaderProgramManager(shaderProgramManager);
        renderInstance.setCubeInstance(this);
        renderInstance.createProjectionMatrix();
        camera.setPosition(new Vector3f(0, 1, 0));
        shaderProgramManager.loadAndBindProgram("deferred_directional");
        shaderProgramManager.loadAndBindProgram("deferred_point");
        shaderProgramManager.loadAndBindProgram("deferred_spot");

        Gson gson = new Gson();
        ModelManager.getInstance().loadModel("quad");
        ModelManager.getInstance().loadModel("sphere");
        plane = gson.fromJson(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/assets/json/entities/jet.json"))), Entity.class);
        plane.initialize();
        plane2 = gson.fromJson(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/assets/json/entities/jet.json"))), Entity.class);
        plane2.initialize();
        ring = gson.fromJson(new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/assets/json/models/disc.json"))), Entity.class);
        ring.initialize();
//        ring.scale(5,5,5);
    }

    public void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create(new PixelFormat(), new ContextAttribs(3, 2).withProfileCore(true).withForwardCompatible(true));
//            Display.create(new PixelFormat(), new ContextAttribs(3, 2).withProfileCore(true).withForwardCompatible(true).withDebug(true));
            Display.setResizable(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glViewport(0, 0, width, height);
            GL11.glClearColor(0,0,0f, 0);
//            ARBDebugOutput.glDebugMessageCallbackARB(new ARBDebugOutputCallback());
            gBuffer = new GBuffer(width, height);
            frametimeQuery = GL15.glGenQueries();
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
            gBuffer.resize(width, height);
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
                    if(sun.getAltitute() > 360){
                        sun.setAltitute(sun.getAltitute()-359);
                    }
                    else{
                        sun.setAltitute(sun.getAltitute()+1);
                    }

                    if(sun.getAzimuth() > 360){
                        sun.setAzimuth(sun.getAzimuth() - 359);
                    }
                    else {
                        sun.setAzimuth(sun.getAzimuth() + 1);
                    }

                }
//                plane.move(0.01f, 0.01f, 0);
//                plane2.rotate(0, 0.01f, 0);
//                ring.scale(0.01f, 0.01f, 0.01f);
                availableTime -= designatedTickTime;
            }

            fps++;
            renderTick++;
            checkForResize();
            GL15.glBeginQuery(GL33.GL_TIME_ELAPSED, frametimeQuery);
            gBuffer.bindForWrite();
            renderInstance.beginGeometryPass();
            renderInstance.render(ring.getModelName(), ring.getModelMatrix());
            renderInstance.render(plane.getModelName(), plane.getModelMatrix());
            renderInstance.render(plane2.getModelName(), plane2.getModelMatrix());
//            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
//            renderInstance.render("sphere", new Matrix4f().translate(pointLight.getPosition()));
//            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
            renderInstance.endGeometryPass();
            renderInstance.beginLightPasses();
            renderInstance.directionalLightPass();
            renderInstance.pointLightPass(pointLight);
            renderInstance.spotLightPass(spotLight);
            GL15.glEndQuery(GL33.GL_TIME_ELAPSED);
            System.out.println("Frame time: "+ (float)GL33.glGetQueryObjecti64(frametimeQuery, GL15.GL_QUERY_RESULT)/1000000f + "ms");
            Display.update();
//            System.out.println("------------------------------------FRAME END--------------------------");
            if(sync) {
                Display.sync(FRAMES);
            }
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
        spotLight.setDirection(camera.getDirection());
        spotLight.setPosition(camera.getPosition());
    }

    public static void checkGLError(String message){
        int error = GL11.glGetError();
        if(error!=0){
            System.err.println("Error: " + error + " " + message);
        }
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