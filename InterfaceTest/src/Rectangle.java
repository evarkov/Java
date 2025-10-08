public class Rectangle implements Figure4{
    private int width;
    private int height;

    public Rectangle(int aWidth, int aHeight){
        width = aWidth;
        height = aHeight;
    }

    @Override
    public void getPerimeter() {
        System.out.println("Прямоугольник");
        System.out.println("P = " + (2 * (width + height)));
    }

    @Override
    public void getArea() {
        System.out.println("Прямоугольник");
        System.out.println("S = " + (width * height));
    }
}
