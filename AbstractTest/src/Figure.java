public abstract class Figure {
    protected int width;
    protected int height;

    public abstract void getArea();

    public Figure(int aWidth, int aHeight){
        width = aWidth;
        height = aHeight;
    }
}
