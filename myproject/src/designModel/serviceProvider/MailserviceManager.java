package designModel.serviceProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理工厂类
 */
public class MailserviceManager {

    private static final Map<String, MailServiceProvider> providers = new ConcurrentHashMap<>();

    /**
     * 服务注册api 相当于jdbc中的DriverManager.registerDriver
     * @param name
     * @param p
     */
    public static void  registerProvider(String name, MailServiceProvider p){
        providers.put(name, p);
    }

    /**
     * 服务访问api 相当于jdbc中的DriverManager.getConnection
     * @param name
     * @return
     */
    public static MailService getMailService(String name){
        MailServiceProvider p = providers.get(name);
        if(null == p)
            throw new IllegalArgumentException(
                    "No provider redistered with name : "+ name);
        return p.getMailService();
    }
}
