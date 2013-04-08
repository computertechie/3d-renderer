package tk.snowmew.cubes;

/**
 * User: Pepper
 * Date: 3/30/13
 * Time: 7:12 PM
 * Project: tk.snowmew.cubes.Cubes
 */
public class ShaderProgram {
    private int vertexShader, fragmentShader, programID;
    private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;
    private int[] vertexAttributes;


    public ShaderProgram(String vertFile, String fragFile, int... vertAtts){
        vertexAttributes = vertAtts;
    }
}
