package refactoring.chapter1.version2;

public class NewRealeasePrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3.0;
    }

}
