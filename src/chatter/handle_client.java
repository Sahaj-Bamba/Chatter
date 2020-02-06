/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class handle_client implements Serializable,Runnable {
    

    private Socket socket ;
    private ObjectInputStream in;
    //private ObjectOutputStream out;
    private Server sv;
    public handle_client (Socket socket1,Server sv){
        this.socket = socket1;
        this.sv = sv;
        try {
            System.out.println("stream creation");
            this.in = new ObjectInputStream(socket.getInputStream());
            //this.out=new ObjectOutputStream(socket.getOutputStream());
            System.out.println("stream created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    @Override
    public void run() {
        
        while (true) {
            
            try {
                
                MSG x = new MSG();
                
                String txt = null;
                
                try {
                    x = (MSG) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Message received");
                
                //      What to do after message is received                
              this.sv.send_all(x);
            /*    System.out.println("Sending try");

                this.out.writeObject(x);

                this.out.flush();
            */    
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }    
    }
    
    
}
