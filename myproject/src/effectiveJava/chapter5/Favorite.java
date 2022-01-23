package effectiveJava.chapter5;


import java.util.*;

/**
 * Created by 15151 on 2019/12/13.
 * 优先考虑类型安全的异构容器
 * 类型安全异构容器，通过key的非具体化类型，来存放不同的value
 */
public class Favorite {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    /**
     * put时转换是为了防止客户端用原生态类型进行破坏
     * 你可以很容易地利用原生态类型HashSet将String 放进HashSet<Integer＞中
     * @param type
     * @param instance
     * @param <T>
     */
    public <T> void putFavorite(Class<T> type, T instance) {
        //type.cast保证运行时安全
        favorites.put(type, type.cast(instance));
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorite favorite = new Favorite();
        //favorite.putFavorite(String.class, "java");
        String favoriteGet = favorite.getFavorite(String.class);
        System.out.println(favoriteGet);

    }
}
