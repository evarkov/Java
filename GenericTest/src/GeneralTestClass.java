public class GeneralTestClass<K,V> {
    private K key;
    private V value;

    public GeneralTestClass(K aKey,V aValue){
        key = aKey;
        value = aValue;
    }

    @Override
    public String toString(){
       return key.toString() + " = " + value.toString();
    }
}
