package tk.snowmew.cubes.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tk.snowmew.cubes.render.Material;
import tk.snowmew.cubes.render.Mesh;
import tk.snowmew.cubes.render.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: Pepper
 * Date: 5/27/13
 * Time: 1:32 PM
 * Project: Cubes
 */


public class SMMFileParser {
    List<Vertex> vertexes;
    List<Mesh> meshes;
    List<Material> materials;
//    List<Bones> bones;

    public SMMFileParser(String filePath){
        this(new File(filePath));
    }

    public SMMFileParser(File file){
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document doc = saxBuilder.build(file);
            parseFile(doc.getRootElement());

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFile(Element element){
        for(Element childElement : element.getChild("mesh").getChildren()){
            if(childElement.getChildren().size() == 0){
                System.out.println(childElement.getName()+" "+childElement.getValue());
                continue;
            }
            for(Element secondChildElement : childElement.getChildren()){
                for(Element third : secondChildElement.getChildren()){
                    System.out.println(third.getName() + " " + third.getValue());
                    System.out.println("hi");
                }
            }
        }
    }
}
