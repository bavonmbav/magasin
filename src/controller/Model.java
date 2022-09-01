
package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Model {
    public Stage stage(String page, String titre)
    {
        Stage s = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            s.setScene(new Scene(root));
            s.initStyle(StageStyle.DECORATED);
            s.setTitle(titre);
            s.show();
          
            return s;
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
       
}
