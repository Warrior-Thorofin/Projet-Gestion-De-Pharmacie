package tn.com.notrePharmacie.model;

public class LigneCommande {
	private int commandeId;
	private int produitId;
	private int quantite;
	private float prixVenteUnitaire;
	
	public LigneCommande(int commandeId,int produitId,int quantite,float prixVenteUnitaire){
		this.commandeId=commandeId;
		this.produitId=produitId;
		this.quantite=quantite;
		this.prixVenteUnitaire=prixVenteUnitaire;
	}
	public int getProduitId() {
		return produitId;
	}
	public int getQuantite() {
		return quantite;
	}	
}
