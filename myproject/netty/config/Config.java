package netty.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    static Properties properties;
    static {
        InputStream resourceAsStream = Config.class.getResourceAsStream("application.properties");
        properties=new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }finally {
            //这里是否是这样关闭我也不知道
            try {
                resourceAsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
