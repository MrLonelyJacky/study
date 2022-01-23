package designModel.serviceProvider;

/**
 * 服务提供者接口，由具体的服务提供者实现 相当于jdbc中的driver
 */
public interface MailServiceProvider {
    MailService getMailService();
}
