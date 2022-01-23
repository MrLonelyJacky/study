package designModel.agent.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * remote是一个记号接口，没有方法 代表被代理的远程接口
 * myRemote即表示远程接口，又有自己的方法
 */
public interface MyRemote extends Remote {
    /**
     * 声明的所有方法都该有异常抛出，因为客户端调用其实是通过代理
     * 而代理要通过网络，必然要有该异常，让客户端必须认识到风险
     * @return 返回值一定要实现序列化
     * @throws RemoteException
     */
    public String sayHello() throws RemoteException;
}
