package concurrent.chapter1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 15151 on 2020/3/27.
 */
public class RecordQuery {
    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    /**
     * benefits of resultHandler is that u can
     * handle result u want
     * every handler handle with it data.txt
     * clear responsibilities and single functions
     * @param handler
     * @param sql
     * @param params
     * @param <T>
     * @return
     * @throws SQLException 执行sql异常
     */
    public <T> T query(ResultHandler<T> handler, String sql, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {

    }
}
