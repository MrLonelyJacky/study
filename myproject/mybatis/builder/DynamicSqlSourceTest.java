/*
package mybatis.builder;/*
package mybatis.builder;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class DynamicSqlSourceTest {

    public static void main(String[] args) {

    }


    private MixedSqlNode mixedContents(SqlNode... contents) {
        return new MixedSqlNode(Arrays.asList(contents));
    }


    private DynamicSqlSource createDynamicSqlSource(SqlNode... contents) throws IOException, SQLException {
        createBlogDataSource();
        final String resource = "org/apache/ibatis/builder/MapperConfig.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        Configuration configuration = sqlMapper.getConfiguration();
        MixedSqlNode sqlNode = mixedContents(contents);
        return new DynamicSqlSource(configuration, sqlNode);
    }


    public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }


    public static DataSource createBlogDataSource() throws IOException, SQLException {
        DataSource ds = createUnpooledDataSource(BLOG_PROPERTIES);
        runScript(ds, BLOG_DDL);
        runScript(ds, BLOG_DATA);
        return ds;
    }


    public static void runScript(DataSource ds, String resource) throws IOException, SQLException {
        Connection connection = ds.getConnection();
        try {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setLogWriter(null);
            runner.setErrorLogWriter(null);
            runScript(runner, resource);
        } finally {
            connection.close();
        }
    }
}
*/
