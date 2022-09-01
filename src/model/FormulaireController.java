
package model;

import controller.Article;
import controller.Connections;
import controller.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

import static java.lang.Integer.parseInt;


public class FormulaireController implements Initializable {

    @FXML
    private TextField code;
    @FXML
    private TextField Nom_produit;
    @FXML
    private TextField Prix;
    @FXML
    private TextField Amballage;

    Model model = new Model();
    Connections con = new Connections();
    Connection conn;
    
     Connections nets ;
    @FXML
    private VBox parent;
 
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
 public void update() {
      
            String codes = code.getText();
            String nom = Nom_produit.getText();
            String pris = Prix.getText();
            String amba = Amballage.getText();
        try {
            String update = "UPDATE articles SET code=?, nomProduit=?, prix=?,amballage=? WHERE code=?";
             PreparedStatement pst = Connections.getInstance().getConnection().prepareStatement(update);
          
         pst.setString(1, codes);
         pst.setString(2, nom);
         pst.setString(3, pris);
         pst.setString(4, amba);
         pst.setString(5, codes);
         pst.executeUpdate();
         
                     JOptionPane.showMessageDialog(null,"avec succes la mise a jour");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    private void getAdds(ActionEvent event) {
           Stage stage = (Stage) parent.getScene().getWindow();
             stage.close();
      String codes = code.getText();
      String nom = Nom_produit.getText();
      String pris = Prix.getText();
      String amba = Amballage.getText();
   
          if (codes.isEmpty() || nom.isEmpty() || pris.isEmpty()|| amba.isEmpty()) {
              JOptionPane.showMessageDialog(null, "y a des case qui sont vide");
            return;
        }
      else{
            update();
          
            return;
        }
     
    }

    @FXML
    private void getCancel(ActionEvent event) {
        Stage stage = (Stage) parent.getScene().getWindow();
         stage.close();
    }
     public void inflateUI(Article article) {
        code.setText(article.getCode());
        Nom_produit.setText(article.getNom());
        Prix.setText(""+article.getPrix());
        Amballage.setText(article.getAmballage()); 
    }
}

