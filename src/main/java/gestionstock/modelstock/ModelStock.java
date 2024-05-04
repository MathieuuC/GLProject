package gestionstock.modelstock;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import dao.DaoManager;


/**
 * La classe ModelStock représente le modèle de stock pour la gestion des matériaux, des médicaments et des colis.
 * Elle implémente le design pattern Singleton pour garantir l'unicité de l'instance.
 */
public class ModelStock {
	
	 private static int nextId = 1;

	private static ModelStock instance = null;

    private List<Materiel> listeMateriel;
    private List<Medicament> listeMedicament;
    private List<Colis> listeColis;


    private ModelStock() {
        listeMateriel = new ArrayList<>();
        listeMedicament = new ArrayList<>();
        listeColis = new ArrayList<>();
    }

    // Méthode publique statique pour obtenir l'instance
    public static ModelStock getInstance() {
        if (instance == null) {
            instance = new ModelStock();
        }
        return instance;
    }


    // Méthodes pour gérer la liste du matériel
    /**
        * Ajoute un matériel à la liste des matériels.
        * 
        * @param materiel Le matériel à ajouter.
        */
    public void addMateriel(Materiel materiel) {
        listeMateriel.add(materiel);
    }

    /**
     * Supprime un matériel de la liste des matériels.
     * 
     * @param identifiant L'identifiant du matériel à supprimer.
     */
    public void removeMateriel(int identifiant) {
        listeMateriel = listeMateriel.stream()
                .filter(m -> m.getIdentifiant() != identifiant)
                .collect(Collectors.toList());
    }

    // Méthodes pour gérer la liste des médicaments
    /**
        * Ajoute un médicament à la liste des médicaments.
        * 
        * @param medicament Le médicament à ajouter.
        */
    public void addMedicament(Medicament medicament) {
        listeMedicament.add(medicament);
    }

    /**
     * Supprime un médicament de la liste des médicaments.
     * 
     * @param id L'identifiant du médicament à supprimer.
     */
    public void removeMedicament(int id) {
        listeMedicament = listeMedicament.stream()
                .filter(m -> m.getIdentifiant() != id)
                .collect(Collectors.toList());
    }

    /**
        * Ajoute un colis au modèle de stock.
        * 
        * @param colis Le colis à ajouter.
        */
    public void addColis(Colis colis) {
    	colis.setIdentifiant(nextId++); 
        listeColis.add(colis);
        DaoManager.getInstance().getDaoStock().addColis(colis);
    }

  



    // Méthode pour supprimer un colis par son identifiant
    /**
     * Supprime un colis en fonction de son identifiant.
     * 
     * @param identifiant L'identifiant du colis à supprimer.
     */
    public void deleteColis(int identifiant) {
        listeColis.removeIf(colis -> colis.getIdentifiant() == identifiant);
        DaoManager.getInstance().getDaoStock().deleteColis(identifiant);
    }

   
    // Méthode pour obtenir le stock actuel sous forme de String
    /**
     * Renvoie les informations sur le stock.
     *
     * @return les informations sur le stock sous forme de chaîne de caractères.
     */
    public String getStock() {
        String stockInfo = "Matériel:\n";
        stockInfo += listeMateriel.stream()
                .map(Materiel::toString)
                .collect(Collectors.joining("\n"));

        stockInfo += "\n\nMédicaments:\n";
        stockInfo += listeMedicament.stream()
                .map(Medicament::toString)
                .collect(Collectors.joining("\n"));

        stockInfo += "\n\nColis:\n";
        stockInfo += listeColis.stream()
                .map(Colis::toString)
                .collect(Collectors.joining("\n"));

        return stockInfo;
    }
    
    /**
        * Met à jour un colis existant avec les nouvelles valeurs spécifiées.
        * 
        * @param updatedColis Le colis mis à jour.
        */
    public void updateColis(Colis updatedColis) {
        Colis colis = null;
        
        // Parcourir la liste des colis pour trouver celui avec l'identifiant correspondant
        for (Colis c : listeColis) {
            if (c.getIdentifiant() == updatedColis.getIdentifiant()) {
                colis = c;
                break;
            }
        }

        if (colis != null) {
            // Mettre à jour l'objet colis avec les nouvelles valeurs de updatedColis
            colis.setNature(updatedColis.getNature());
            colis.setVolume(updatedColis.getVolume());
            colis.setCotes(updatedColis.getCotes());
            colis.setDesignation(updatedColis.getDesignation());
            colis.setValeurHT(updatedColis.getValeurHT());

            // Appeler DaoManager pour mettre à jour la base de données
            DaoManager.getInstance().getDaoStock().updateColis(colis);
        } else {
            // Afficher un message si le colis n'est pas trouvé
            System.out.println("Colis avec l'identifiant " + updatedColis.getIdentifiant() + " non trouvé.");
        }
    }



    // Getters pour accéder aux listes si nécessaire

    public List<Materiel> getListeMateriel() {
        return listeMateriel;
    }

    public List<Medicament> getListeMedicament() {
        return listeMedicament;
    }

    public List<Colis> getListeColis() {
        return listeColis;
    }
    

    public List<Colis> getAllColis() {
        return DaoManager.getInstance().getDaoStock().getAllColis();
    }
    
    /**
     * Ajoute un matériel à un colis spécifié.
     * 
     * @param colisId l'identifiant du colis
     * @param materiel le matériel à ajouter
     * @return true si l'ajout est réussi, false si le colis n'est pas trouvé
     */
    public boolean ajouterMaterielAuColis(int colisId, Materiel materiel) {
        Colis colis = listeColis.stream()
            .filter(c -> c.getIdentifiant() == colisId)
            .findFirst()
            .orElse(null);

        if (colis != null) {
            colis.ajouterMateriel(materiel);
            DaoManager.getInstance().getDaoStock().ajouterMaterielAuColis(colisId, materiel);
            return true;  // Retourner vrai si l'ajout est réussi
        }
        return false;  // Retourner faux si le colis n'est pas trouvé
    }

    /**
     * Rafraîchit la liste des colis en récupérant tous les colis à partir du gestionnaire DAO.
     */
    public void refreshColisList() {
        this.listeColis = DaoManager.getInstance().getDaoStock().getAllColis();
    }


}
