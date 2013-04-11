package tk.snowmew.cubes;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * User: Pepper
 * Date: 4/9/13
 * Time: 3:26 PM
 * Project: Cubes
 */

public class TextureManager {
    private static TextureManager instance = new TextureManager();
    private HashMap<String,Texture> nameTexMap = new HashMap<String, Texture>();
    private HashMap<Integer,String> idNameMap = new HashMap<Integer, String>();

    private TextureManager(){

    }

    public void createTexture(String filePath){
        InputStream in;
        File file =  new File(filePath);
        try{
            in = new FileInputStream(file);
            try{
                PNGDecoder decoder = new PNGDecoder(in);
                ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
                decoder.decode(buf, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
                buf.flip();

                int error = GL11.glGetError();

                if(error != 0){
                    System.err.println("some gl error " + error);
                }

                int pos = file.getName().lastIndexOf('.');
                String name = pos > 0 ? file.getName().substring(0, pos) : file.getName();

                int id = GL11.glGenTextures();
                Texture texture = new Texture(name, GL11.GL_TEXTURE_2D, id, decoder.getWidth(), decoder.getHeight(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, GL13.GL_TEXTURE0, buf);
                 error = GL11.glGetError();

                if(error != 0){
                    System.err.println("post genTextures" + error);
                }
                GL13.glActiveTexture(texture.getTexUnit());
                error = GL11.glGetError();

                if(error != 0){
                    System.err.println("post glActiveTexture" + error);
                }
                GL11.glBindTexture(texture.getTexTarget(), texture.getTexID());
                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("post glBindTexture " + error);
                }
                GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT,1);
                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("glPixelStore" + error);
                }

                GL11.glTexImage2D(texture.getTexTarget(), 0, texture.getDataFormat(), texture.getWidth(), texture.getHeight(), 0, texture.getDataFormat(), texture.getDataType(), texture.getBuffer());
                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("glTexImage2D " + error);
                }
                GL30.glGenerateMipmap(texture.getTexTarget());
                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("glGenerateMipmap" + error);
                }
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);//_MIPMAP_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);//_MIPMAP_LINEAR);
                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("glTexParameter " + error);
                }
//                GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);
//                GL13.glActiveTexture(0);

                error = GL11.glGetError();
                if(error != 0){
                    System.err.println("unbind texture" + error);
                }

                addTexture(texture);
            }
            catch(FileNotFoundException ex){
                System.err.println("Texture file not found");
            }
        }
        catch(IOException iex){
            System.err.println("I/O exception occurred.");
        }
    }

    public void bindTexture(String name){
        Texture texture = nameTexMap.get(name);
        GL13.glActiveTexture(texture.getTexUnit());
        GL11.glBindTexture(texture.getTexTarget(), texture.getTexID());
    }

    public void bindTexture(int texID){
        Texture texture = nameTexMap.get(idNameMap.get(texID));
        GL13.glActiveTexture(texture.getTexUnit());
        GL11.glBindTexture(texture.getTexTarget(), texID);
    }

    public Texture getTexture(String name){
        return nameTexMap.get(name);
    }

    public Texture getTexture(int id){
        return nameTexMap.get(idNameMap.get(id));
    }

    public static TextureManager getInstance(){
        return instance;
    }

    public void addTexture(Texture texture){
        nameTexMap.put(texture.getTexName(), texture);
        idNameMap.put(texture.getTexID(), texture.getTexName());
    }
}
