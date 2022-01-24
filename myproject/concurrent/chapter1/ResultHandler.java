package concurrent.chapter1;

import java.sql.ResultSet;

/**
 * Created by 15151 on 2020/3/27.
 * strategy mode show
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet);
}
