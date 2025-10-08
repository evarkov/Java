public class Main {
    public static void main(String[] args) {
       GeneralTestClass<Integer,Integer> intTest = new GeneralTestClass<>(10,78);
       GeneralTestClass<String,String> strTest = new GeneralTestClass<>("Aaaa","hhhhh");

        System.out.println(intTest);
        System.out.println(strTest);

    }
}
