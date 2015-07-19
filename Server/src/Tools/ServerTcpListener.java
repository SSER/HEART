package Tools;

import java.net.*;
import java.io.*;

public class ServerTcpListener implements Runnable {


    public void run() {
    }

    public static void receiveFile(Socket socket) {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            try {
                dis = new DataInputStream(socket.getInputStream());
                fos = new FileOutputStream(new File("./cc.jpg"));
                inputByte = new byte[1024];
                System.out.println("��ʼ��������...");
                while ((length = dis.read(inputByte, 0, inputByte.length)) > 0) {
                    System.out.println(length);
                    fos.write(inputByte, 0, length);
                    fos.flush();
                }
                System.out.println("��ɽ���");
            } finally {
                if (fos != null)
                    fos.close();
                if (dis != null)
                    dis.close();
                if (socket != null)
                    socket.close();
            }
        } catch (Exception e) {
        }
    }
}