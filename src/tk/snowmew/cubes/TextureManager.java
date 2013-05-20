package tk.snowmew.cubes;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Pepper
 * Date: 4/9/13
 * Time: 3:26 PM
 * Project: Cubes
 */

public class TextureManager {
    private static TextureManager instance = new TextureManager();
    private Map<String,Texture> nameTexMap = new HashMap<String, Texture>();
    private Map<Integer,String> idNameMap = new HashMap<Integer, String>();
    private int textureUnit = 0;

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

                int pos = file.getName().lastIndexOf('.');
                String name = pos > 0 ? file.getName().substring(0, pos) : file.getName();
                Texture texture = new Texture(name, GL11.GL_TEXTURE_2D, GL11.glGenTextures(), decoder.getWidth(), decoder.getHeight(), GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, textureUnit, buf);

                GL13.glActiveTexture(texture.getTexUnit());
                GL11.glBindTexture(texture.getTexTarget(), texture.getTexID());

                GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT,1);

                GL11.glTexImage2D(texture.getTexTarget(), 0, texture.getDataFormat(), texture.getWidth(), texture.getHeight(), 0, texture.getDataFormat(), texture.getDataType(), texture.getBuffer());

                GL30.glGenerateMipmap(texture.getTexTarget());

                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

                addTexture(texture);
                textureUnit++;
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
        GL13.glActiveTexture(GL13.GL_TEXTURE0+texture.getTexUnit());
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
