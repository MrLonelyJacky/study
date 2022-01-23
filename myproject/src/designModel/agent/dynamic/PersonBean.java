package designModel.agent.dynamic;

/**
 * 被代理接口
 */
public interface PersonBean {
    String getName();

    int getHotOrNotRating();

    void setName(String name);

    void setHotOrNotRating(int rating);
}
