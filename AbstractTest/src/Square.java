public class Square extends Figure{
    public Square(int aWidth, int aHeight) {
        super(aWidth, aHeight);
    }
    public Square(int aSize){
        super(aSize,aSize);
    }
    @Override
    public void getArea() {
        System.out.println("Square");
        System.out.println("S = " + width * height);
        System.out.println("P = " + 2 * width + 2 * height);
    }
}
