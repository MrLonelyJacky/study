package designModel.agent.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "hello ";
    }

    /**
     * 注册服务
     * @param args
     */
    public static void main(String[] args) {
        MyRemote service;
        try {
            service = new MyRemoteImpl();
            //注册你的服务
            Naming.rebind("RemoteHello",service);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
