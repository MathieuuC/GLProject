package gestionstock.modelstock;

public class ConfigurationClimatique {
    private int identifiant;
    private String type;
    private String description;

    // Constructeur
    public ConfigurationClimatique(int identifiant, String type, String description) {
        this.identifiant = identifiant;
        this.type = type;
        this.description = description;
    }

    // Getters et Setters
    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Autres méthodes utiles pour la gestion de la configuration climatique...
}
