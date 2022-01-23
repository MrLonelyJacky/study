package designModel.agent.dynamic;

/**
 * 具体代被理类
 */
public class PersonBeanImpl implements PersonBean{
    private String name;
    private int rating;

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public int getHotOrNotRating() {
        return this.rating;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHotOrNotRating(int rating) {
        this.rating = rating;
    }
}
