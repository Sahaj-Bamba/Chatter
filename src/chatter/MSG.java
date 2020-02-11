/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatter;
import java.io.Serializable;
/**
 *
 * @author admin
 */
public class MSG implements Serializable{
    private String txt;
    private String from;
    private String to;
    public boolean isFile;
    public byte []by; 
    public String org;
    
    public void setter(String a, String b, String c){
        this.from = a;
        this.to = b;
        this.txt = c;
        this.isFile = false;
    }
    public void setterFile(String a, String b, String c, byte[] by){
        this.from = a;
        this.to = b;
        this.txt = c;
        this.isFile = true;
        this.by = by;
    }
    
    public void set_txt(String txt){
        this.txt = txt;
    }
    
    public String get_from(){
        return this.from;
    }
    
    public String get_to(){
        return this.to;
    }
    public String get_txt(){
        return this.txt;
    }
    
}
