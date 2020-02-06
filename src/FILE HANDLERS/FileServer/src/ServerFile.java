import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {
        private ServerSocket serverSocket;
        private Socket socket;

        int port = 5558;
        void start() {
            while (true) {
                try {
                    serverSocket = new ServerSocket(port);
                    break;
                } catch (IOException e) {
//                    e.printStackTrace();
                    port++;
                }
            }

            System.out.println("File Server started on port " + port);
            run();
        }

    protected void run() {

        while (true) {
            try {
                System.out.println("Accepting sockets");
                socket = serverSocket.accept();
                System.out.println("Client socket accepted");
                Thread t = new Thread(new HandleClientFileServer(socket));
                System.out.println("Handle client for file server created");
                t.start();
                System.out.println("Thread Started");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
