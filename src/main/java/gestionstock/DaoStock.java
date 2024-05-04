package gestionstock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import gestionstock.modelstock.Colis;
import gestionstock.modelstock.Materiel;
import gestionstock.modelstock.Medicament;

public class DaoStock {
	
	private Connection connection ;
	private static final Logger log = Logger.getLogger("DaoStock");
	
	public DaoStock(Connection connection) {
		this.connection = connection ;
	}
	
	 public void addColis(Colis colis) {
	        try {
	            PreparedStatement ps = connection.prepareStatement("INSERT INTO COLIS (NATURE, VOLUME, COTES, DESIGNATION, VALEUR_HT) VALUES (?, ?, ?, ?, ?)");
	            ps.setString(1, colis.getNature());
	            ps.setDouble(2, colis.getVolume());
	            ps.setString(3, colis.getCotes());
	            ps.setString(4, colis.getDesignation());
	            ps.setInt(5, colis.getValeurHT());
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	            log.severe("Error adding colis: " + e.getMessage());
	        }
	    }

	    public List<Colis> getAllColis() {
	        List<Colis> colisList = new ArrayList<>();
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM COLIS");
	            while (rs.next()) {
	                Colis colis = new Colis(
	                    rs.getInt("ID"),
	                    rs.getString("NATURE"),
	                    rs.getDouble("VOLUME"),
	                    rs.getString("COTES"),
	                    rs.getString("DESIGNATION"),
	                    rs.getInt("VALEUR_HT")
	                );
	                colisList.add(colis);
	            }
	            rs.close();
	            stmt.close();
	        } catch (Exception e) {
	            log.severe("Error getting all colis: " + e.getMessage());
	        }
	        return colisList;
	    }
	    
	    public void updateColis(Colis colis) {
	        PreparedStatement ps = null;
	        try {
	            String sql = "UPDATE COLIS SET NATURE = ?, VOLUME = ?, COTES = ?, DESIGNATION = ?, VALEUR_HT = ? WHERE ID = ?";
	            ps = connection.prepareStatement(sql);
	            ps.setString(1, colis.getNature());
	            ps.setDouble(2, colis.getVolume());
	            ps.setString(3, colis.getCotes());
	            ps.setString(4, colis.getDesignation());
	            ps.setInt(5, colis.getValeurHT());
	            ps.setInt(6, colis.getIdentifiant());

	            int affectedRows = ps.executeUpdate();
	            if (affectedRows == 0) {
	                log.warning("Aucune ligne mise à jour pour le colis avec l'ID: " + colis.getIdentifiant());
	            } else {
	                log.info("Mise à jour réussie pour le colis avec l'ID: " + colis.getIdentifiant());
	            }
	        } catch (Exception e) {
	            log.severe("Erreur lors de la mise à jour du colis: " + e.getMessage());
	        } finally {
	            if (ps != null) {
	                try {
	                    ps.close();
	                } catch (Exception e) {
	                    log.severe("Erreur lors de la fermeture du PreparedStatement: " + e.getMessage());
	                }
	            }
	        }
	    }


	    
	    public Colis getColisById(int id) {
	        Colis colis = null;
	        try {
	            String sql = "SELECT * FROM COLIS WHERE ID = ?";
	            PreparedStatement ps = connection.prepareStatement(sql);
	            ps.setInt(1, id);
	            
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                colis = new Colis(
	                    rs.getInt("ID"),
	                    rs.getString("NATURE"),
	                    rs.getDouble("VOLUME"),
	                    rs.getString("COTES"),
	                    rs.getString("DESIGNATION"),
	                    rs.getInt("VALEUR_HT")
	                );
	            }
	            
	            rs.close();
	            ps.close();
	        } catch (Exception e) {
	            log.severe("Error getting colis by id: " + e.getMessage());
	        }
	        return colis;
	    }
	    


	   

	    public void deleteColis(int id) {
	        try {
	            PreparedStatement ps = connection.prepareStatement("DELETE FROM COLIS WHERE ID = ?");
	            ps.setInt(1, id);
	            ps.executeUpdate();
	            ps.close();
	        } catch (Exception e) {
	            log.severe("Error deleting colis: " + e.getMessage());
	        }
	    }
	    
	    public void ajouterMaterielAuColis(int colisId, Materiel materiel) {
	        try {
	            // Insérer le matériel
	            PreparedStatement ps = connection.prepareStatement("INSERT INTO Materiels (nom, fonction, estFonctionnel) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, materiel.getNom());
	            ps.setString(2, materiel.getFonction());
	            
	            int affectedRows = ps.executeUpdate();

	         

	            // Récupérer l'ID généré pour le matériel
	            int materielId = -1;
	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                materielId = generatedKeys.getInt(1);
	            }
	            generatedKeys.close();
	            ps.close();

	            // Associer le matériel au colis
	            if (materielId != -1) {
	                ps = connection.prepareStatement("INSERT INTO ColisMateriels (colisId, materielId) VALUES (?, ?)");
	                ps.setInt(1, colisId);
	                ps.setInt(2, materielId);
	                ps.executeUpdate();
	                ps.close();
	            }
	        } catch (Exception e) {
	            log.severe("Error adding materiel to colis: " + e.getMessage());
	        }
	    }

	    public void ajouterMedicamentAuColis(int colisId, Medicament medicament) {
	        try {
	            // Insérer le médicament
	            PreparedStatement ps = connection.prepareStatement("INSERT INTO Medicaments (DCI, formeDosage, classeTherapeutique, numCaisse, lot, DLU, dotationU7) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, medicament.getDCI());
	            ps.setString(2, medicament.getFormeDosage());
	            ps.setString(3, medicament.getClasseTherapeutique());
	            ps.setInt(4, medicament.getNumCaisse());
	            ps.setString(5, medicament.getLot());
	            ps.setString(6, medicament.getDLU());
	            ps.setString(7, medicament.getDotationU7());
	            int affectedRows = ps.executeUpdate();

	            if (affectedRows == 0) {
	                throw new Exception("Creating medicament failed, no rows affected.");
	            }

	            // Récupérer l'ID généré pour le médicament
	            int medicamentId = -1;
	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                medicamentId = generatedKeys.getInt(1);
	            }
	            generatedKeys.close();
	            ps.close();

	            // Associer le médicament au colis
	            if (medicamentId != -1) {
	                ps = connection.prepareStatement("INSERT INTO ColisMedicaments (colisId, medicamentId) VALUES (?, ?)");
	                ps.setInt(1, colisId);
	                ps.setInt(2, medicamentId);
	                ps.executeUpdate();
	                ps.close();
	            }
	        } catch (Exception e) {
	            log.severe("Error adding medicament to colis: " + e.getMessage());
	        }
	    }

}
