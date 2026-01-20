package tn.com.notrePharmacie.model;

public class Stock {
	private int fournisseurId;
	private int produitId;
	private int quantite;
	public Stock(int fournisseurId,int produitId,int quantite){
		this.fournisseurId=fournisseurId;
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
