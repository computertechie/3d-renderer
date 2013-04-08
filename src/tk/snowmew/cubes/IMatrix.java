package tk.snowmew.cubes;

public abstract interface IMatrix
{
    public abstract void translate(float paramFloat1, float paramFloat2, float paramFloat3);

    public abstract void scale(float paramFloat1, float paramFloat2, float paramFloat3);

    public abstract void rotateX(float paramFloat);

    public abstract void rotateY(float paramFloat);

    public abstract void rotateZ(float paramFloat);
}