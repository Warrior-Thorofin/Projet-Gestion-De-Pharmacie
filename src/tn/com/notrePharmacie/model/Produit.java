package tn.com.notrePharmacie.model;

public class Produit {
	
	private int produitId;
	private int quantite;
	private String nomProduit;
	public Produit(int ProduitId,String nomProduit,int quantite) {
		this.produitId=ProduitId;
		this.quantite=quantite;
		this.nomProduit=nomProduit;
	}
	public int getProduitId() {
		return this.produitId;
	}
	public int getQuantite() {
		return this.quantite;
	}
	public String getNomProduit() {
		return this.nomProduit;
	}
	
	
}
