package tn.com.notrePharmacie.model;

public class LigneCommande {
	private int commandeId;
	private int produitId;
	private int quantite;
	public LigneCommande(int commandeId,int produitId,int quantite){
		this.commandeId=commandeId;
		this.produitId=produitId;
		this.quantite=quantite;
	}
	public int getProduitId() {
		return produitId;
	}
	public int getQuantite() {
		return quantite;
	}	
}
