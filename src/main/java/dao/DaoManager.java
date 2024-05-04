package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import gestionstock.modelstock.Colis;
import gestionstock.modelstock.Materiel;
import gestionstock.modelstock.Medicament;
import gestionstock.DaoStock;

/**
 * La classe DaoManager est responsable de la gestion de la connexion à la base de données
 * et fournit des méthodes pour accéder aux différentes fonctionnalités de la base de données.
 */
public class DaoManager {

    private static DaoManager instance = null;
    private Connection connection = null;
    private static final Logger log = Logger.getLogger("DaoManager");
    private static DaoStock daoStock ;

    /**
     * Constructeur privé pour empêcher l'instanciation directe de la classe.
     * Initialise la connexion à la base de données et crée les tables si elles n'existent pas.
     */
    private DaoManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/mathi/Documents/Web Development/GLProject/base3.db");
            log.info("Base de données ouverte avec succès");
            createDatabase();
        } catch (Exception e) {
            log.severe("Échec de la connexion à la base de données : " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Retourne l'instance unique de DaoManager en utilisant le modèle Singleton.
     * Si l'instance n'existe pas, elle est créée.
     * @return L'instance unique de DaoManager.
     */
    public static DaoManager getInstance() {
        if (instance == null) {
            instance = new DaoManager();
        }
        return instance;
    }

    /**
     * Crée les tables de la base de données si elles n'existent pas déjà.
     */
    private void createDatabase() {
        try {
            Statement stmt = connection.createStatement();
            String sqlColis = "CREATE TABLE IF NOT EXISTS COLIS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NATURE TEXT, VOLUME REAL, COTES TEXT, DESIGNATION TEXT, VALEUR_HT INTEGER)";
            stmt.executeUpdate(sqlColis);
            
            String sqlMateriels = "CREATE TABLE IF NOT EXISTS Materiels (ID INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, fonction TEXT, estFonctionnel BOOLEAN)";
            stmt.executeUpdate(sqlMateriels);
            
            String sqlMedicaments = "CREATE TABLE IF NOT EXISTS Medicaments (ID INTEGER PRIMARY KEY AUTOINCREMENT, DCI TEXT, formeDosage TEXT, classeTherapeutique TEXT, numCaisse INTEGER, lot TEXT, DLU TEXT, dotationU7 TEXT)";
            stmt.executeUpdate(sqlMedicaments);
            
            String sqlColisMateriels = "CREATE TABLE IF NOT EXISTS ColisMateriels (colisId INTEGER, materielId INTEGER, FOREIGN KEY(colisId) REFERENCES COLIS(ID), FOREIGN KEY(materielId) REFERENCES Materiels(ID))";
            stmt.executeUpdate(sqlColisMateriels);
            
            String sqlColisMedicaments = "CREATE TABLE IF NOT EXISTS ColisMedicaments (colisId INTEGER, medicamentId INTEGER, FOREIGN KEY(colisId) REFERENCES COLIS(ID), FOREIGN KEY(medicamentId) REFERENCES Medicaments(ID))";
            stmt.executeUpdate(sqlColisMedicaments);

            stmt.close();
        } catch (Exception e) {
            log.severe("Erreur lors de la création de la base de données : " + e.getMessage());
        }
        
    }
    
    /**
     * Retourne l'instance de DaoStock pour accéder aux fonctionnalités de gestion du stock.
     * Si l'instance n'existe pas, elle est créée.
     * @return L'instance de DaoStock.
     */
    public DaoStock getDaoStock() {
    	if ( daoStock == null ) {
    		daoStock = new DaoStock(this.connection);
    	}
    	return daoStock;
    }
}
