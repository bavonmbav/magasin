
package model;

import controller.Article;
import controller.Connections;
import controller.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class FormeController implements Initializable {

    Connections con = new Connections();
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Article> tableaux;
    @FXML
    private TableColumn<Article, String> code;
    @FXML
    private TableColumn<Article, String> nom;
    @FXML
    private TableColumn<Article, Integer> prix;
    @FXML
    private TableColumn<Article, String> amballage;

     Model model = new Model();
    private Connections connections;
    
    ObservableList<Article> liste = FXCollections.observableArrayList();
      //=========recherche filter==========//
      public void rechercheFilter()
      {
          FilteredList<Article> eleve = new FilteredList<>(liste, b->true);
          recherche.textProperty().addListener((observable,ancienValuer,valeur)->{
              eleve.setPredicate((Article e) -> {
                  if(valeur == null || valeur.isEmpty()){
                      return true;
                  }
                  String amener = valeur.toLowerCase();
                  int val = valeur.charAt(0);
                  if (e.getCode().toLowerCase().contains(amener)) {
                      return true;
                  }
                  else if(e.getNom().toLowerCase().contains(amener)){
                      return true;
                  }
                  else if(e.getAmballage().toLowerCase().contains(amener)){
                      return true;
                  }
                  return false;
              }); 
          });    
        SortedList<Article> sorte = new SortedList<>(eleve);
        sorte.comparatorProperty().bind(tableaux.comparatorProperty());
        tableaux.setItems(sorte);
      }
      
      
    
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connections = Connections.getInstance();
       selecte();
       rechercheFilter();
    }    

    @FXML
    private void getADD(ActionEvent event) {
          String page = "/vue/formulaire1";
          String titre = "ajouter Article";
          model.stage(page, titre);
    }

    @FXML
    private void getDelete(ActionEvent event) {
          Article article = tableaux.getSelectionModel().getSelectedItem();
          if (article == null) {
               JOptionPane.showMessageDialog(null,"veuillez choisir une conlonne");
                 return;
        }
          else
          {                
                   liste.removeAll(article);
                  connections.deleteArticle(article);
                   selecte();
          }
        
    }

    @FXML
    private void getUpdate(ActionEvent event) {
         Article article = tableaux.getSelectionModel().getSelectedItem();
         String page = "/vue/Formulaire";
        if (article == null) {
               JOptionPane.showMessageDialog(null,"veuillez choisir une conlonne");
                 return;
        }
        else {
          
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource(page+".fxml"));
                Parent parent = loader.load();
                FormulaireController controller = (FormulaireController) loader.getController();
                controller.inflateUI(article);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Edit Article");
                stage.setScene(new Scene(parent));
                stage.show();   
                stage.setOnHidden((e)->{
                    getRefresh(new ActionEvent());
                });
            
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public void select()
    {
        liste.clear();
           String select = "SELECT * FROM articles";
           Connections conne  = Connections.getInstance();
           ResultSet rs = conne.execQuery(select);
          try {
              while(rs.next()) {    
                                 String codes = rs.getString("code");
                                 String noms = rs.getString("nomProduit");
                                 Integer prixs = rs.getInt("prix");
                                 String amballer = rs.getString("amballage");
                                 
                        liste.addAll(new Article(codes,noms,prixs,amballer));
              }
                     
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        tableaux.setItems(liste);
    }
    
    
     public void selecte()
     {
          code.setCellValueFactory(new PropertyValueFactory<>("code"));   
          nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          amballage.setCellValueFactory(new PropertyValueFactory<>("amballage"));   
          select();
     }

    @FXML
    private void getRefresh(ActionEvent event) {
        selecte();
    }
    
   

}
