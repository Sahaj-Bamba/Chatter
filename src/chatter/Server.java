/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatter;


//import static chatbox.Chatbox.chatwindow;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server{
    
    private boolean is_on ;
    private int port;
    private String name;
    private ObjectOutputStream out[] = new ObjectOutputStream[100];
    private int count;
    
    Server(int Port,String name){
        this.port = Port;
        this.name = name;
    }
    
    public void close(){    
        is_on = false;
    }   
    
    
    public void run() {
        count = 0;
        this.is_on = true;
        ServerSocket serverSocket = null;
        Socket socket;
        System.out.println("Server started");
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            
            /*this.is_on = false;
            error.run(" Something went wrong please try again .");
            menu.start();
            System.out.print("njvsdkvsdnjsdv dslvknkjdsvnjhdsbvhjbdskjvbsajhvhgbxchfgvsdcgfvgsdcvjhcsvjkscvjdskfcvkuzsdvcsghn");*/
            
            e.printStackTrace();
        }
        

//      enclose this in while loop for multiple clients handelling        

            try {
                System.out.println("Server listen on.");
                while(true){
                socket = serverSocket.accept();
                this.out[count++]=new ObjectOutputStream(socket.getOutputStream());
            
                new Thread(new handle_client(socket,this)).start();
                }
        
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    
    public void send_all(MSG x){
        for(int i=0;i<count;i++){
            try {
                System.out.println("Sending try");

                this.out[i].writeObject(x);

                this.out[i].flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
