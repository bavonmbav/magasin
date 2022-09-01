
package controller;


public class Article {
    private String code;
    private String nom;
    private Integer prix;
    private String amballage;

    public Article(String code, String nom, int prix, String amballage) {
        this.code = code;
        this.nom = nom;
        this.prix = prix;
        this.amballage = amballage;
    }

    public Article() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getAmballage() {
        return amballage;
    }

    public void setAmballage(String amballage) {
        this.amballage = amballage;
    }

    
}
