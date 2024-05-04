package gestionstock.modelstock;

public class Medicament {
    private int identifiant;
    private String DCI; // Dénomination Commune Internationale
    private String formeDosage;
    private String classeTherapeutique;
    private int numCaisse;
    private String lot;
    private String DLU; // Date Limite d'Utilisation
    private String dotationU7;

    // Constructeur
    public Medicament(int identifiant, String DCI, String formeDosage, String classeTherapeutique,
                      int numCaisse, String lot, String DLU, String dotationU7) {
        this.identifiant = identifiant;
        this.DCI = DCI;
        this.formeDosage = formeDosage;
        this.classeTherapeutique = classeTherapeutique;
        this.numCaisse = numCaisse;
        this.lot = lot;
        this.DLU = DLU;
        this.dotationU7 = dotationU7;

    }

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getDCI() {
		return DCI;
	}

	public void setDCI(String dCI) {
		DCI = dCI;
	}

	public String getFormeDosage() {
		return formeDosage;
	}

	public void setFormeDosage(String formeDosage) {
		this.formeDosage = formeDosage;
	}

	public String getClasseTherapeutique() {
		return classeTherapeutique;
	}

	public void setClasseTherapeutique(String classeTherapeutique) {
		this.classeTherapeutique = classeTherapeutique;
	}

	public int getNumCaisse() {
		return numCaisse;
	}

	public void setNumCaisse(int numCaisse) {
		this.numCaisse = numCaisse;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getDLU() {
		return DLU;
	}

	public void setDLU(String dLU) {
		DLU = dLU;
	}

	public String getDotationU7() {
		return dotationU7;
	}

	public void setDotationU7(String dotationU7) {
		this.dotationU7 = dotationU7;
	}





}
