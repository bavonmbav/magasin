/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Article;
import controller.Connections;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import static java.lang.Integer.parseInt;

public class Formulaire1Controller implements Initializable {

    @FXML
    private VBox parent;
    @FXML
    private TextField code;
    @FXML
    private TextField Nom_produit;
    @FXML
    private TextField Prix;
    @FXML
    private TextField Amballage;

       public boolean reponse()
    {
      String codes = code.getText();
      String nom = Nom_produit.getText();
      String pris = Prix.getText();
      String amba = Amballage.getText();
        String requette = "INSERT INTO articles (code,nomProduit,prix,amballage) VALUES(?,?,?,?)";
         try{
         PreparedStatement pst = Connections.getInstance().getConnection().prepareStatement(requette);
         pst.setString(1, codes);
         pst.setString(2, nom);
         pst.setString(3, pris);
         pst.setString(4,amba);
         pst.executeUpdate();
             JOptionPane.showMessageDialog(null,"vous avez inseret : " + nom + ", prix : " + pris + " emballage :"+amba);
          
          }catch(Exception ex)
        {
            ex.printStackTrace();
        } 
         return true;
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
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
       boolean result = reponse();
        if (result) {
             JOptionPane.showMessageDialog(null, "insertion bien effectuer");
              vider();
        }
        else
           JOptionPane.showMessageDialog(null, "pas de modication ou insertion");  
    }

    @FXML
    private void getCancel(ActionEvent event) {
          Stage stage = (Stage) parent.getScene().getWindow();
         stage.close();
    }
     public void vider()
    {
        code.setText("");
        Nom_produit.setText("");
        Prix.setText("");
        Amballage.setText("");
    }
}
