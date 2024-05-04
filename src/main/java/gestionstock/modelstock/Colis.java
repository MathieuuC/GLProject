package gestionstock.modelstock;


import java.util.ArrayList;
import java.util.List;

/**
 * La classe Colis représente un colis contenant des matériels et des médicaments.
 */
public class Colis {
	private int identifiant;
	private String nature;
	private double volume;
	private String cotes;
	private String designation;
	private int valeurHT;
	private List<Materiel> materiels; // Liste pour stocker des objets Materiel
	private List<Medicament> medicaments; // Liste pour stocker des objets Medicament

	/**
	 * Constructeur de la classe Colis.
	 *
	 * @param identifiant L'identifiant du colis.
	 * @param nature      La nature du colis.
	 * @param volume      Le volume du colis.
	 * @param cotes       Les cotes du colis.
	 * @param designation La désignation du colis.
	 * @param valeurHT    La valeur hors taxe du colis.
	 */
	public Colis(int identifiant, String nature, double volume, String cotes, String designation, int valeurHT) {
		this.identifiant = identifiant;
		this.nature = nature;
		this.volume = volume;
		this.cotes = cotes;
		this.designation = designation;
		this.valeurHT = valeurHT;
		this.materiels = new ArrayList<>();
		this.medicaments = new ArrayList<>();
	}

	/**
	 * Obtient l'identifiant du colis.
	 *
	 * @return L'identifiant du colis.
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * Définit l'identifiant du colis.
	 *
	 * @param identifiant Le nouvel identifiant du colis.
	 */
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Obtient la nature du colis.
	 *
	 * @return La nature du colis.
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * Définit la nature du colis.
	 *
	 * @param nature La nouvelle nature du colis.
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * Obtient le volume du colis.
	 *
	 * @return Le volume du colis.
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Définit le volume du colis.
	 *
	 * @param volume Le nouveau volume du colis.
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * Obtient les cotes du colis.
	 *
	 * @return Les cotes du colis.
	 */
	public String getCotes() {
		return cotes;
	}

	/**
	 * Définit les cotes du colis.
	 *
	 * @param cotes Les nouvelles cotes du colis.
	 */
	public void setCotes(String cotes) {
		this.cotes = cotes;
	}

	/**
	 * Obtient la désignation du colis.
	 *
	 * @return La désignation du colis.
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Définit la désignation du colis.
	 *
	 * @param designation La nouvelle désignation du colis.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Obtient la valeur hors taxe du colis.
	 *
	 * @return La valeur hors taxe du colis.
	 */
	public int getValeurHT() {
		return valeurHT;
	}

	/**
	 * Définit la valeur hors taxe du colis.
	 *
	 * @param valeurHT La nouvelle valeur hors taxe du colis.
	 */
	public void setValeurHT(int valeurHT) {
		this.valeurHT = valeurHT;
	}

	/**
	 * Ajoute un matériel au colis.
	 *
	 * @param materiel Le matériel à ajouter.
	 */
	public void ajouterMateriel(Materiel materiel) {
		this.materiels.add(materiel);
	}

	/**
	 * Retire un matériel du colis en fonction de son identifiant.
	 *
	 * @param id L'identifiant du matériel à retirer.
	 */
	public void retirerMateriel(int id) {
		this.materiels.removeIf(materiel -> materiel.getIdentifiant() == id);
	}

	/**
	 * Retire un médicament du colis en fonction de son identifiant.
	 *
	 * @param id L'identifiant du médicament à retirer.
	 */
	public void retirerMedicament(int id) {
		this.materiels.removeIf(materiel -> materiel.getIdentifiant() == id);
	}

	/**
	 * Ajoute un médicament au colis.
	 *
	 * @param medicament Le médicament à ajouter.
	 */
	public void ajouterMedicament(Medicament medicament) {
		this.medicaments.add(medicament);
	}

	/**
	 * Obtient le nombre de matériels dans le colis.
	 *
	 * @return Le nombre de matériels dans le colis.
	 */
	public int getNombreMateriels() {
		return materiels.size();
	}

	/**
	 * Obtient le nombre de médicaments dans le colis.
	 *
	 * @return Le nombre de médicaments dans le colis.
	 */
	public int getNombreMedicaments() {
		return medicaments.size();
	}
}







