package gestionstock.modelstock;

public class Materiel {

    private int identifiant;
    private String nom;
    private String fonction;
    

    // Constructeur
    public Materiel(int identifiant, String nom, String fonction) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.fonction = fonction;
        
    }



    // Getters et Setters
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

   

  
}
