public class Square implements Figure4{
    private int length;

    public Square(int aLength){
        length = aLength;
    }

    @Override
    public void getArea() {
        System.out.println("Квадрат");
        System.out.println("S = " + Math.pow(length,2));
    }

    @Override
    public void getPerimeter() {
        System.out.println("Квадрат");
        System.out.println("P = " + length*4);
    }
}
