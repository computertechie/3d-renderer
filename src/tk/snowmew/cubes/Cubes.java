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
    int width =854, height=480;

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
        camera.setPosition(new Vector3f(0, 1, 1));
        box = new Model();
        box.translate(0, 1, 0);
        ground = new Model();
        ground.scale(100, 0.1f, 100);
    }

    public void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(854, 480));
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
            renderInstance.render(ground);
            renderInstance.render(box);
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

        int dx = Mouse.getDX(), dy = Mouse.getDY();
        camera.pitch((dy*75)/height);
        camera.bearing((dx*100)/width);
    }
}
