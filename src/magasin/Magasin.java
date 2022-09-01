
package magasin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Magasin extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader racine = new FXMLLoader(getClass().getResource("/vue/forme.fxml"));
            Parent root = racine.load();
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Magasin");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException ex) {
            Logger.getLogger(Magasin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
