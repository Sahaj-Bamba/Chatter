/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatter;

//import static chatbox.Chatbox.chatwindow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */

public class Listen implements Serializable,Runnable {
    
    private Socket socket = null;
    ObjectInputStream in = null;
    private String name;
    private ChatWindow chatWindow = null;

    Listen (Socket socket, String name, ChatWindow cw) {
        
        this.socket = socket;
        this.chatWindow = cw;
        
        try {
            System.out.println("in stream creation");
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in stream created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.name = name;
    
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
                
                if(!x.isFile){
                  
                //      What to do after message is received
                System.out.println(this.name);
                System.out.println(x.get_to());
                if(x.get_to().equals(this.name)){
                    this.chatWindow.MessageReceived(x.get_from()+": "+x.get_txt());
                }
                
                }else{
                    
                    //  Handelling of file receive
                
                    if(x.get_to().equals(this.name)){
                        File file = new File(x.get_txt());
         
                            FileOutputStream fos = null;

                            try {

                                fos = new FileOutputStream(file);

                                // Writes bytes from the specified byte array to this file output stream 
                                fos.write(x.by);
      this.chatWindow.MessageReceived(x.get_from()+": "+x.get_txt());
              
                            }
                            catch (FileNotFoundException e) {
                                System.out.println("File not found" + e);
                            }
                            catch (IOException ioe) {
                                System.out.println("Exception while writing file " + ioe);
                            }
                            finally {
                                // close the streams using close method
                                try {
                                    if (fos != null) {
                                        fos.close();
                                    }
                                }
                                catch (IOException ioe) {
                                    System.out.println("Error while closing stream: " + ioe);
                                }
 
                    }
                
                    
                }
            }} catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }    
    }
}

