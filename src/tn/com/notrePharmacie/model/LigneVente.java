package tn.com.notrePharmacie.model;

public class LigneVente {
	private int venteId;
	private int produitId;
	private int quantiteVendue;
	private float prixVenteUnitaire;
	
	public LigneVente(int venteId,int produitId,int quantiteVendue,float prixVenteUnitaire){
		this.venteId=venteId;
		this.produitId=produitId;
		this.quantiteVendue=quantiteVendue;
		this.prixVenteUnitaire=prixVenteUnitaire;
	}
	public int getProduitId() {
		return produitId;
	}
	public int getQuantiteVendue() {
		return quantiteVendue;
	}	
}
