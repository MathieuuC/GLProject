package gestionstock.modelstock;

public class InstructionChargement {
    private int identifiant;
    private int vitesse;
    private int poidsMax;
    private String instructionsChargement;

    // Constructeur
    public InstructionChargement(int identifiant, int vitesse, int poidsMax, String instructionsChargement) {
        this.identifiant = identifiant;
        this.vitesse = vitesse;
        this.poidsMax = poidsMax;
        this.instructionsChargement = instructionsChargement;
    }

    // Méthode pour décrire l'opération de chargement
    public void operation() {
        // Ici, nous mettrions la logique pour effectuer l'opération de chargement basée sur les instructions
    }

    // Getters et Setters
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(int poidsMax) {
        this.poidsMax = poidsMax;
    }

    public String getInstructionsChargement() {
        return instructionsChargement;
    }

    public void setInstructionsChargement(String instructionsChargement) {
        this.instructionsChargement = instructionsChargement;
    }

    // Autres méthodes utiles pour l'opération de chargement...
}
