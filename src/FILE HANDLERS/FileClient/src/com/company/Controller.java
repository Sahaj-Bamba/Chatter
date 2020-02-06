package com.company;

import javafx.stage.FileChooser;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class Controller {

    public void onPressed(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All","*");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            if(selectedFile != null) {
                Main.FILEGAMER.sendFile(selectedFile.getAbsolutePath(), 10);
                System.out.println("File sent from server");
                System.out.println(Main.FILEGAMER.recieveResponse());
                System.out.println("Response from server received");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
