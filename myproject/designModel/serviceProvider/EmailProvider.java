package designModel.serviceProvider;

/**
 * 服务提供者具体的实现 相当于jdbc中的Driver
 */
public class EmailProvider implements MailServiceProvider{
    static{
        MailserviceManager.registerProvider("Email", new EmailProvider());
    }

    @Override
    public MailService getMailService() {
        return new Email();
    }

    private static class Email implements MailService{
        @Override
        public void transTheMail(){
            System.out.println("电子邮件发送");
        }
    }
}
