package effectiveJava.chapter2;

import java.util.EnumSet;
import java.util.Set;

public abstract class Pizza {
    protected Pizza(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public enum Topping {HAM, ONION}

    private final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> enumSet = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            enumSet.add(topping);
            return (T) this;
        }
    }

}
