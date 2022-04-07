package mybatis.proxy;

public class CglibTest {
    public String print(){
        System.out.println("hahhahahhahah");
        return "hahahha";
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibTest proxy = (CglibTest) cglibProxy.getProxy(CglibTest.class);
        proxy.print();

    }
}
