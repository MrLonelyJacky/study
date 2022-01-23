package refactoring.chapter1.version2;

public class Rental {
    private Movie _movie; // 影片
    private int _daysRented; // 租期

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    /**
     * 重构1.提取代码  重构2.修改变量名  重构3，从customer类中搬迁到Rental类中
     * 根据租赁类型和天数计算价格  重构4.去除customer中的无用变量
     * 重构7 注释掉下面代码 使用状态模式
     *
     * @param
     * @return
     */
   /* public double getCharge() {
        double result = 0;
        // determine amounts for each line
        switch (getMovie().getPriceCode()) { // 取得影片出租价格
            case Movie.REGULAR: // 普通片
                result += 2;
                if (getDaysRented() > 2)
                    result += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE: // 新片
                result += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS: // 儿童。
                result += 1.5;
                if (getDaysRented() > 3)
                    result += (getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }*/

    //重构5
    public int getFrequentRenterPoints() {
        // add frequent renter points （累计常客积点。
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE)
                && getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    public double getCharge() {
        return this._movie.getCharge(this._daysRented);
    }
}
