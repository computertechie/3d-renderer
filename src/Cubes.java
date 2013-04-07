import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * User: Pepper
 * Date: 4/6/13
 * Time: 7:22 PM
 * Project: Cubes
 */

public class Cubes {
    Renderer renderInstance = Renderer.getInstance();
    CameraQuat camera = new CameraQuat();
    Model box, ground;
    static final int WIDTH=854, HEIGHT=480;

    public static void main(String[] args){
        Cubes cube = new Cubes();
        cube.tick();
    }

    public Cubes(){
        createDisplay();
        box = new Model();
//        box.translate(0,1,-2);
        ground = new Model();
        ground.scale(100,0.1f,100);
        renderInstance.setCamera(camera);
    }

    public void createDisplay(){
        try {
            Display.setDisplayMode(new DisplayMode(854, 480));
            Display.create();
            GL11.glViewport(0,0,WIDTH,HEIGHT);
        } catch (LWJGLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void tick(){
        while(!Display.isCloseRequested()){
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT|GL11.GL_COLOR_BUFFER_BIT);
            GL11.glClearColor(0.5f,0.5f,0.5f,0);
            renderInstance.render(ground);
            renderInstance.render(box);
            Display.update();
            Display.sync(60);
        }
    }
}
