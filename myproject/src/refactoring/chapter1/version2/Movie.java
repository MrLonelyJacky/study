package refactoring.chapter1.version2;

public class Movie {
	public static final int CHILDRENS = 2;
	//常规电影
	public static final int REGULAR = 0;
	//新电影 过段时间就成为常规电影 这是我们使用状态模式而不使用策略模式的原因
	public static final int NEW_RELEASE = 1;
	private String _title;

	private Price price;

	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);
	}

	public int getPriceCode() {
		return price.getPriceCode();
	}

	public String getTitle() {
		return _title;
	}

	//重构7 使用状态模式修改switch case 为什么不使用策略模式呢？让Movie类有不同的子类
	//使用状态模式是因为一个电影可能会使用不同的价格计算方法，一段时间他是新电影，过一点时间
	//就是常规电影  这是我们使用状态模式而不使用策略模式的原因 他会从不同类型电影切换 但是策略模
	//一旦继承就无法改变父类了
	/**
	 * 状态模式切换类型 这就是为什么不使用策略模式的原因 新发布的电影时间久了就会变成常规电影
	 * @param priceCode
	 */
	public void setPriceCode(int priceCode) {
		switch (priceCode) {
			case REGULAR:
				price = new RegularPrice();
				break;
			case NEW_RELEASE:
				price = new NewRealeasePrice();
				break;
			case CHILDRENS:
				price = new ChildrensPrice();
				break;
			default:
				throw new IllegalArgumentException("无效参数！");
		}
	}

	public double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}

}