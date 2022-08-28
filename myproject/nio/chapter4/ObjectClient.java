package nio.chapter4;



import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: zhukaishengy
 * @Date: 2020/6/19 14:55
 * @Description:
 */
public class ObjectClient {

    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 9000)
        ){
            OutputStream outputStream = socket.getOutputStream();
            Userinfo genericUserInfo = Userinfo.builder().id("1").name("haha").age(20).build();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(genericUserInfo);

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Userinfo userinfo = (Userinfo) objectInputStream.readObject();
            System.out.println("receive:{}"+ userinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
