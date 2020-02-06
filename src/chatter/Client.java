/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatter;

//import static chatbox.Chatbox.chatwindow;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author admin
 */


public class Client implements Serializable {

    private String IP;
    private int port;
    private String name;
    private String group;
    
    public Client(String name,String IP,int port,String group){
        this.name = name;
        this.IP = IP;
        this.port = port;
        this.group = group;
    }

    
    
    public void run() {
    
        try {
            
            Socket socket = new Socket(this.IP, this.port);
            System.out.println("Client created.");
            MSG x = new MSG();
            x.setter(this.name, this.group, "");
            new ChatWindow(socket,x);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
