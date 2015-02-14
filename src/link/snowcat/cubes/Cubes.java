package link.snowcat.cubes;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import link.snowcat.cubes.lights.DirectionalLight;
import link.snowcat.cubes.render.*;

/**
 * User: Pepper
 * Date: 4/6/13
 * Time: 7:22 PM
 * Project: link.snowcat.cubes.Cubes
 */

public class Cubes {
    final int TIME_CONVERSION = 100000000;
    public static Renderer renderInstance = Renderer.getInstance();
    public static TextureManager textureManagerInstance = TextureManager.getInstance();
    public static MaterialManager materialManagerInstance = MaterialManager.getInstance();
    public static ShaderProgramManager shaderProgramManager = ShaderProgramManager.getInstance();
    private String[] vertAttribs = {"position","in_tex","normal"};
    private String[] uniformAttribs= {"texture","diffuseColor"};
    Camera camera = new Camera(0,0);
    Model box;
    public DirectionalLight sun = new DirectionalLight(new Vector3f(-10,0,0), new Vector3f(1,1,1), 0.1f);
    int width =854, height=480;
    float mouseSensitivity = 0.1f;
    long tick = 0;

    double t = 0, dt = 0.01, currentTime = System.nanoTime()/TIME_CONVERSION, accumulator=0,frameTime =0;

    public static void main(String[] args){
        Cubes cube = new Cubes();
        cube.tick();
    }

    public Cubes(){
        createDisplay();
        renderInstance.setCamera(camera);
        renderInstance.setTextureManager(textureManagerInstance);
        renderInstance.setShaderProgramManager(shaderProgramManager);
        renderInstance.setCubeInstance(this);
        renderInstance.createProjectionMatrix();
        camera.setPosition(new Vector3f(0, 1, 0));
        shaderProgramManager.registerProgram("standard",new ShaderProgram(this.getClass().getResource("/assets/shaders/standard/standard_vert.glsl"),this.getClass().getResource("/assets/shaders/standard/standard_frag.glsl"),vertAttribs,uniformAttribs));
        box = new Model(this.getClass().getResource("/assets/models/OBJ/Small_Disc.obj"));
    }

    public void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Display.setResizable(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glViewport(0, 0, width, height);
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
            GL11.glViewport(0,0,width, height);
            camera.reorient();
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void tick(){
        double newTime = 0;
        while(!Display.isCloseRequested()){
            newTime = System.nanoTime();///TIME_CONVERSION;
            frameTime = newTime - currentTime;

            frameTime /= TIME_CONVERSION;
            if(frameTime > 0.25)
                frameTime = 0.25;

            currentTime = newTime;
            accumulator += frameTime;

            while(accumulator >= dt){
                tick++;
                getInput();
                camera.reorient();
                if(tick%10== 0 && tick <=100){
                    float newX=0, newY=0;
                    if(sun.getDirection().x == 10)
                        newX = -9.9f;
                    else
                        newX = sun.getDirection().x+0.1f;

                    newY = (float)Math.sqrt(100-(Math.pow(newX,2)));
                    sun.setDirection(new Vector3f(newX, -newY, 0));
                }
                box.update();
                t+=dt;
                accumulator -= dt;
            }

            checkForResize();
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
            GL11.glClearColor(0.5f, 0.5f, 0.5f, 0);
            renderInstance.render(box);
//            renderInstance.render(ground);
            Display.update();
            Display.sync(60);
        }
    }


    public void getInput(){
        float speedModifier = 0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            System.exit(0);
        if(Keyboard.isKeyDown(Keyboard.KEY_TAB))
            speedModifier = 0.1f;
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            camera.move((float)(0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            camera.move((float)(-0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            camera.strafe((float)(-0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            camera.strafe((float)(0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            camera.moveVertical((float)(0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            camera.moveVertical((float)(-0.1f*speedModifier));
        if(Keyboard.isKeyDown(Keyboard.KEY_T))
            Mouse.setGrabbed(!Mouse.isGrabbed());
        if(Keyboard.isKeyDown(Keyboard.KEY_F4)){
            try {
                Display.setFullscreen(!Display.isFullscreen());
            } catch (LWJGLException e) {
                e.printStackTrace();
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_0)){
            camera.zeroBearing();
            camera.zeroPitch();
        }

        int dx = Mouse.getDX(), dy = Mouse.getDY();
        float temp1 = mouseSensitivity * 0.6f + 0.1f;
        temp1 = (float)Math.pow((double)temp1, 2) * 8F;
        dy *= temp1;
        dx *= temp1;
        camera.pitch(dy);
        camera.bearing(dx);
    }
}