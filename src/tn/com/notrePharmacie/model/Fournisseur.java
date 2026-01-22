package tn.com.notrePharmacie.model;

public class Fournisseur {
	private String typeFournisseur;
	private String numTelephone;
	private String adresse;
	private String email;
	private int fournisseurId;
	private String raisonSocial ;//nom complet et officiel
	public Fournisseur(String typeFournisseur, String numTelephone, String adresse, String email, int fournisseurId, String raisonSocial) {
	    this.typeFournisseur = typeFournisseur;
	    this.numTelephone = numTelephone;
	    this.adresse = adresse;
	    this.email = email;
	    this.fournisseurId = fournisseurId;
	    this.raisonSocial = raisonSocial;
	}
	
	
	
}
