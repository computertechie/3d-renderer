package tk.snowmew.cubes;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 * User: Pepper
 * Date: 4/6/13
 * Time: 7:22 PM
 * Project: tk.snowmew.cubes.Cubes
 */

public class Cubes {
    Renderer renderInstance = Renderer.getInstance();
    TextureManager textureManagerInstance = TextureManager.getInstance();
    CameraQuat camera = new CameraQuat(0,0);
    Model box, ground;
    DirectionalLight sun = new DirectionalLight(new Vector3f(50,50,50), new Vector3f(0.75f, 0.75f, 0.75f), 0.5f);
    int width =854, height=480;
    float mouseSensitivity = 0.2f;

    public static void main(String[] args){
        Cubes cube = new Cubes();
        cube.tick();
    }

    public Cubes(){
        createDisplay();
        renderInstance.setCamera(camera);
        renderInstance.setTextureManager(textureManagerInstance);
        renderInstance.setCubeInstance(this);
        renderInstance.createProjectionMatrix();
        textureManagerInstance.createTexture("assets/textures/creeper.png");
        camera.setPosition(new Vector3f(0, 1, 0));
        box = new Model("assets/models/cube.obj");
//        ground = new Model("assets/models/cube.obj");
//        ground.scale(100, 0.1f, 100);
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
        width = Display.getWidth();
        height = Display.getHeight();
        renderInstance.createProjectionMatrix();
        GL11.glViewport(0,0,width, height);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void tick(){
        while(!Display.isCloseRequested()){
            checkForResize();
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
            GL11.glClearColor(0.5f, 0.5f, 0.5f, 0);
            getInput();
            camera.reorient();
            box.update();
//            ground.update();
            renderInstance.render(box);
//            renderInstance.render(ground);
            Display.update();
            Display.sync(60);
        }
    }


    public void getInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            System.exit(0);
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            camera.move(0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            camera.move(-0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            camera.strafe(-0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            camera.strafe(0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            camera.moveVertical(0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            camera.moveVertical(-0.1f);
        if(Keyboard.isKeyDown(Keyboard.KEY_T))
            Mouse.setGrabbed(!Mouse.isGrabbed());
        if(Keyboard.isKeyDown(Keyboard.KEY_F11)){
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
