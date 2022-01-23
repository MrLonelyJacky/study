package refactoring.chapter6;

/**
 * 引入解释型变量
 * 注意：当很难提取方法的时候再运用，这可以让我们extract method is easy
 * 变量不如方法来的好
 */
public class IntroduceExplainingVariable {
    private int quantity;
    private int price;

    /**
     * 对于这种 你不好提取方法
     *
     * @return
     */
    double price() {
        return quantity * price - Math.max(0, quantity - 500) * price * 0.05 + Math.min(quantity * price * 0.01, 100);
    }

    /**
     * 看看这样可读性怎么样 是不是很棒
     *
     * @return
     */
    double price2() {
        final double basePrice = quantity * price;
        final double discount = Math.max(0, quantity - 500) * price * 0.05;
        final double shipping = Math.min(quantity * price * 0.01, 100);
        return basePrice - discount + shipping;
    }

    double price3() {
        return basePrice() - discount() + shipping();
    }

    double basePrice() {
        return quantity * price;
    }

    double discount() {
        return Math.max(0, quantity - 500) * price * 0.05;
    }

    double shipping() {
        return Math.min(quantity * price * 0.01, 100);
    }
}
