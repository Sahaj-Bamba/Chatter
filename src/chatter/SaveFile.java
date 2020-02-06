/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatter;

/**
 *
 * @author Sahaj
 */

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SaveFile {

    DataInputStream dataInputStream;
    public SaveFile(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public void saveFile(String dirName, String fileName) throws IOException {

        System.out.println("Save file called");

        System.out.println(fileName);
        long size = dataInputStream.readLong();
        System.out.println(size);
        String filePath = dirName + "/" + fileName;
        System.out.println(filePath);

        int bytesRead = 0;

        OutputStream output = new FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        while (size > 0 && (bytesRead = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)
        {
            output.write(buffer, 0, bytesRead);
            size -= bytesRead;
        }
        //File stored on server
    }
}