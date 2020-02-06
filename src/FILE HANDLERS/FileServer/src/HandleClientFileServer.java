//Server is receiving file

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HandleClientFileServer extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public HandleClientFileServer(Socket socket) {
//        System.out.println("In the constructor of HandleClientFileServer");
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

        System.out.println("In run method of File Client Handler");

        while(true) {

            int bytesRead;
            String fileName = null;
            try {

                fileName = dataInputStream.readUTF();
                long size = dataInputStream.readLong();
                int type = dataInputStream.readInt();

                OutputStream output = new FileOutputStream(fileName);
                System.out.println(size);
                System.out.println(type);
                byte[] buffer = new byte[1024];
                while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)
                {
                    output.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }
                //File stored on server

//                //Now, sending the boolean status to client
//                System.out.println("Hello");
                Boolean response = process(type);
                System.out.println(response);
                dataOutputStream.writeBoolean(response);
                dataOutputStream.flush();
                System.out.println("Response sent");

            } catch (IOException e) {
//                e.printStackTrace();
            }

        }
    }

    private Boolean process(int type) {
        if(type == 10) {
            return true;
        }
        return false;
    }

}