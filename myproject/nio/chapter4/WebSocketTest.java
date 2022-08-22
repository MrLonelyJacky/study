package nio.chapter4;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class WebSocketTest {

    @Test
    public void test1() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9000);
             Socket socket = serverSocket.accept();
             InputStream inputStream = socket.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             OutputStream outputStream = socket.getOutputStream()) {
            String getString;
            while (!"".equals(getString = bufferedReader.readLine())) {
                System.out.println(getString);
            }
            outputStream.write("HTTP/1.1 200 OK \r\n\r\n".getBytes());
            outputStream.write("<html><body><a href='http://www.google.com'>Google</a></body></html>".getBytes());
            outputStream.flush();
        }


    }


    @Test
    public void test2() {

        try (
                ServerSocket serverSocket = new ServerSocket(9000);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream()
        ){
            byte[] bytes = new byte[64];
            System.out.println("wait for client data");
            inputStream.read(bytes);
            System.out.println(new String(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server() {
        try (
                ServerSocket serverSocket = new ServerSocket(9000);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream()
        ){
            byte[] bytes = new byte[9];
            while (inputStream.read(bytes) != -1) {
                String result = new String(bytes, Charset.forName("utf-8"));
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client() {
        try (
                Socket socket = new Socket("localhost", 9000);
                OutputStream outputStream = socket.getOutputStream()
        ){
            String message = "朱开生";
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            Thread.sleep(5000);
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[3];
            while (inputStream.read(bytes) != -1) {
                String result = new String(bytes, Charset.forName("utf-8"));
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test9() {
        try {
            Socket socket = new Socket("localhost", 9000);
            OutputStream outputStream = socket.getOutputStream();
            String line1 = "朱开生";
            String line2 = "呼呼";
            String line3 = "嘻嘻";
            outputStream.write(line1.getBytes(StandardCharsets.UTF_8));
            Thread.sleep(3000);
            outputStream.write(line2.getBytes(StandardCharsets.UTF_8));
            Thread.sleep(3000);
            outputStream.write(line3.getBytes(StandardCharsets.UTF_8));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test10() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);

            Socket socket = serverSocket.accept();
            // 输入开始
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            int byteLength = objectInputStream.readInt();
            byte[] byteArray = new byte[byteLength];
            objectInputStream.readFully(byteArray);
            String newString = new String(byteArray);
            System.out.println(newString);
            // 输入结束
            // 输出开始
            OutputStream outputStream = socket.getOutputStream();
            String strA = "客户端你好A\n";
            String strB = "客户端你好B\n";
            String strC = "客户端你好C\n";
            int allStrByteLength = (strA + strB + strC).getBytes().length;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeInt(allStrByteLength);
            objectOutputStream.flush();
            objectOutputStream.write(strA.getBytes());
            objectOutputStream.write(strB.getBytes());
            objectOutputStream.write(strC.getBytes());
            objectOutputStream.flush();
            // 输出结束
            // 输入开始
            byteLength = objectInputStream.readInt();
            byteArray = new byte[byteLength];
            objectInputStream.readFully(byteArray);
            newString = new String(byteArray);
            System.out.println(newString);
            // 输入结束
            // 输出开始
            strA = "客户端你好D\n";
            strB = "客户端你好E\n";
            strC = "客户端你好F\n";
            allStrByteLength = (strA + strB + strC).getBytes().length;
            objectOutputStream.writeInt(allStrByteLength);
            objectOutputStream.flush();
            objectOutputStream.write(strA.getBytes());
            objectOutputStream.write(strB.getBytes());
            objectOutputStream.write(strC.getBytes());
            objectOutputStream.flush();
            // 输出结束
            inputStream.close();

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        try {
            Socket socket = new Socket("localhost", 9000);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            // 输出开始
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            String strA = "服务端你好A\n";
            String strB = "服务端你好B\n";
            String strC = "服务端你好C\n";
            int allStrByteLength = (strA + strB + strC).getBytes().length;
            objectOutputStream.writeInt(allStrByteLength);
            objectOutputStream.flush();
            objectOutputStream.write(strA.getBytes());
            objectOutputStream.write(strB.getBytes());
            objectOutputStream.write(strC.getBytes());
            objectOutputStream.flush();
            // 输出结束
            // 输入开始
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            int byteLength = objectInputStream.readInt();
            byte[] byteArray = new byte[byteLength];
            objectInputStream.readFully(byteArray);
            String newString = new String(byteArray);
            System.out.println(newString);
            // 输入结束
            // 输出开始
            strA = "服务端你好D\n";
            strB = "服务端你好E\n";
            strC = "服务端你好F\n";
            allStrByteLength = (strA + strB + strC).getBytes().length;
            objectOutputStream.writeInt(allStrByteLength);
            objectOutputStream.flush();
            objectOutputStream.write(strA.getBytes());
            objectOutputStream.write(strB.getBytes());
            objectOutputStream.write(strC.getBytes());
            objectOutputStream.flush();
            // 输出结束
            // 输入开始
            byteLength = objectInputStream.readInt();

            byteArray = new byte[byteLength];
            objectInputStream.readFully(byteArray);
            newString = new String(byteArray);
            System.out.println(newString);
            // 输入结束
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
