package refactoring.chapter6;

/**
 * 替换临时变量为查询 这样的好处是方便提取代码，临时变量往往造成代码不易提取
 */
public class ReplaceTempWithQuery {
    private int quantity;
    private int price;

    /**
     * 下面函数中的变量如何替换成为查询呢？
     *
     * @return
     */
    double getPrice() {
        int basePrice = quantity * price;
        double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    /**
     * 首先把变量变为final 看是否有影响
     *
     * @return
     */
    double getPrice(int first) {
        final int basePrice = quantity * price;
        final double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    /**
     * 如果你不把basePrice提出来，你发现你很难提取出disCountFactor
     * @param second
     * @return
     */
    double getPrice(int second, int end) {

       /* final double discountFactor;
        if (basePrice()>1000){
            discountFactor =0.95;
        }else {
            discountFactor=0.98;
        }
        return basePrice()*discountFactor;*/
        return basePrice() * disCountFactor();
    }

    private int basePrice() {
        return quantity * price;
    }

    private double disCountFactor() {
        if (basePrice() > 1000) {
            return 0.95;
        } else {
            return 0.98;
        }
    }
}
