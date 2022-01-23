package designModel.serviceProvider;

public class ProviderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //加载类 该类会将自己注册 TODO 当然还可以通过类加载器去实现 现在的jdbc已经不用下面的代码了，而是在getConnection中通过加载器实现
        Class.forName("designModel.serviceProvider.EmailProvider");
        MailService email = MailserviceManager.getMailService("Email");
        email.transTheMail();
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
    }
}
