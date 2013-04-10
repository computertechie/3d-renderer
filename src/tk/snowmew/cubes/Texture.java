package tk.snowmew.cubes;

import java.nio.ByteBuffer;

/**
 * User: Pepper
 * Date: 4/8/13
 * Time: 9:32 AM
 */
public class Texture {
    private ByteBuffer buffer;
    private String texName;
    private int texID, texTarget, width, height, dataFormat, dataType, texUnit;

    public Texture(String name, int textureTarget, int id, int width, int height, int format, int type, int unit, ByteBuffer buf){
        texName = name;
        texID = id;
        texTarget = textureTarget;
        this.width = width;
        this.height = height;
        dataFormat = format;
        dataType = type;
        texUnit = unit;
        buffer = buf;
    }

    public int getTexUnit(){
        return texUnit;
    }

    public String getTexName(){
        return texName;
    }

    public int getTexID(){
        return texID;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getTexTarget(){
        return texTarget;
    }

    public int getDataFormat(){
        return dataFormat;
    }

    public int getDataType(){
        return dataType;
    }

    public ByteBuffer getBuffer(){
        return buffer;
    }
}
