package tn.com.notrePharmacie.model;

public class Produit {
	
	private int produitId;
	private String nomProduit;
	private float prixAchat;
	private float TVA;
	private float prixVente;
	private int quantiteMinimale;
	private int quantiteMaximale;
	private int dureeDeConservation;
	public Produit(int ProduitId,String nomProduit,float prixAchat,float TVA) {
		this.produitId=ProduitId;
		this.nomProduit=nomProduit;
		this.prixAchat=prixAchat;
		this.TVA=TVA;
		this.prixVente=(prixAchat*TVA)/100;
	}
	public int getProduitId() {
		return this.produitId;
	}
	public int getQuantiteMaximale() {
		return this.quantiteMaximale;
	}
	public int getDureeDeConservation() {
		return this.dureeDeConservation;
	}
	
	
	

}
