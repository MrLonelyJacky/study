package refactoring.chapter1.version2;

public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);
}
