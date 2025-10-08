public class Rectangle extends Figure{
    public Rectangle(int aWidth, int aHeight){
        super(aWidth, aHeight);
    }

    @Override
    public void getArea() {
        System.out.println("Rectangle");
        System.out.println("S = " + width * height);
        System.out.println("P = " + 2 * width + 2 * height);
    }
}
