
package controller;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class Connections {
    
       private static Connections handler = null;

    private static final String DB_URL = "jdbc:derby:Magasin;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet resultat = null;
    
    
    
     private static void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = (Connection) DriverManager.getConnection(DB_URL);
            System.out.println("connexion ok");
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
           // System.exit(0);
        }
    }
     
     
     static{
        createConnection();
        Connections con = new Connections();
       con.createtable();
  }
    
    public static Connections getInstance() {
        if (handler == null) {
            handler = new Connections();
        }
        return handler;
    }  
  public void createtable(){
        final String TABLE = "articles";
        try{
            stmt = conn.createStatement();
            //creer une table
            DatabaseMetaData data = conn.getMetaData();
            resultat = (ResultSet) data.getTables(null, null, TABLE.toLowerCase(), null);
            if(resultat.next())
            {
                System.out.println("la base " + TABLE +" existe deja");
            }else
            {
                stmt.execute("CREATE TABLE "+TABLE 
                        +"(code varchar(10) primary key, nomProduit varchar(10),prix Integer, amballage varchar(15))");
               
            }
                    System.out.println(TABLE+" a ete creer avec succes");
                 conn.close();
        }catch(Exception e)
        {
              System.err.println(e + "la table exit");
 
        }
       
    }    
  public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        }
        catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        finally {
        }
        return result;
    }
  public Connection getConnection() {
        return conn;
    }
   public boolean deleteArticle(Article article) {
        try {
            String deleteStatement = "DELETE FROM articles WHERE code = ?";
            PreparedStatement stmts = conn.prepareStatement(deleteStatement);
            stmts.setString(1, article.getCode());
            int res = stmts.executeUpdate();
            if (res == 1) {
                return true;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
