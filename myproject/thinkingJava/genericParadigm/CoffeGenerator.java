package thinkingJava.genericParadigm;


import java.util.Iterator;

/**
 * Created by 15151 on 2019/3/28.
 */
public class CoffeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private int size = 0;

    public CoffeGenerator() {

    }

    public CoffeGenerator(int size) {
        this.size = size;
    }

    @Override
    public Coffee next() {

        return null;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }


    class CoffeeIterator implements Iterator {
        int count = size;

        @Override
        public boolean hasNext() {

            return count > 0;
        }

        @Override
        public Object next() {
            count--;
            return CoffeGenerator.this.next();
        }

    }

    public static void main(String[] args) {
        CoffeGenerator coffees = new CoffeGenerator();
        for (Coffee coffee: coffees) {

        }

        while (coffees.iterator().hasNext()){
            coffees.iterator().next();
        }
    }
}
