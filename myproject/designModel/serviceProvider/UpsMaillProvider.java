package designModel.serviceProvider;

/***
 * 另一种服务提供接口的具体实现，相当于Driver有mysql的实现也有oracle的实现
 */
public class UpsMaillProvider implements MailServiceProvider {

    static {
        MailserviceManager.registerProvider("ups", new UpsMaillProvider());
    }

    @Override
    public MailService getMailService() {
        return new UpsMail();
    }

    private static class UpsMail implements MailService {

        @Override
        public void transTheMail() {
            System.out.println("ups mail....");
        }
    }
}
