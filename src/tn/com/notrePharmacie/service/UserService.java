package tn.com.notrePharmacie.service;
import tn.com.notrePharmacie.dao.*;

import tn.com.notrePharmacie.model.*;
import tn.com.notrePharmacie.exception.*;
import java.time.LocalDate;//yyyy-MM-dd
import java.util.ArrayList;


public class UserService {
	 private UserDao dao=new UserDao();//objet d'accees a la BD
	 protected int password;//la session depend de lui(c'est de caching)
	 protected String username;//choisissons protected pour les acceder a partir de AdminService
	  public UserService(String username,int password){
	    	this.password=password;
	    	this.username=username;
	  }
	  
	  
	  

	  
/*********************************************************************/
	  
	  
//methode pour l'interface Commande cote user

/*********************************************************************/
	  
	  
	    public int passerCommande(int fournisseurId,LocalDate datePrevue) {
	    	LocalDate dateCommande=LocalDate.now();
	    	int commandeId=dao.creerCommande(fournisseurId,password,datePrevue,dateCommande);
	    	return commandeId;
	    }
	    
	    
	    //ca serait afficher dans l'UI
	    public ArrayList<LigneCommande> specifierCommande(int commandeId,int produitId,int quantite,float prixVenteUnitaire) {
	    	dao.creerLigneCommande(commandeId, produitId, quantite, prixVenteUnitaire);
	    	ArrayList<LigneCommande> ligneCommande=dao.getLignesCommandeByCommandeId(commandeId);
	    	return ligneCommande;
	    }
	    
	  
	  public void annulerCommande(int commandeId) {
	    	dao.supprimerCommande(commandeId);
	    }
	  
	  public void modifierQuantiteCommande(int fournisseurId,LocalDate datePrevue,LocalDate dateCommande,int produitId,int newQuantite) {//avec table lignecommande
		  int commandeId=dao.getCommandeId(fournisseurId,password,datePrevue,dateCommande);
		  dao.miseajourQuantiteCommande(commandeId,produitId,newQuantite);
	
	  }
	  public void modifierDatePrevueCommande(int fournisseurId,LocalDate datePrevue,LocalDate dateCommande,LocalDate newDatePrevue) {//avec table commande
		  int commandeId=dao.getCommandeId(fournisseurId,password,datePrevue,dateCommande);
		  dao.miseajourDatePrevueCommande(commandeId,newDatePrevue);
	  }
	  
	  public void receptionCommande(LocalDate dateRelle,int fournisseurId,LocalDate datePrevue,LocalDate dateCommande) {
		  int commandeId=dao.getCommandeId(fournisseurId,password,datePrevue,dateCommande);
		  dao.miseajourCommande( commandeId, dateRelle);//on accede a commande 
		  //apres reception de stock on met a jour le stock (incrementation des quantités)
		  
		  ArrayList<Produit> listeStock=getStockDepuisCommande(commandeId);	
	    	for (Produit s:listeStock) {
	    		if(dao.verifStock(s.getProduitId())==false){//si on n'a pas ce stock on le creer
	    			dao.creerStock(s.getProduitId(),s.getNomProduit(),s.getQuantite());
	    		}
	    		else {//si ce stock est deja existant on met a jour la quantite
	    			int quantiteActuelle=dao.lireQuantiteDepuisStock(s.getProduitId());
	    			int nouvelleQuantite=quantiteActuelle+s.getQuantite();
	    			dao.misajourStock(s.getProduitId(),nouvelleQuantite);
	    		}
	    	}
	  }
	    
	  public ArrayList<Produit> getStockDepuisCommande(int commandeId){
	    	 ArrayList<Produit> listeStock = new ArrayList<>();
	    	 ArrayList<LigneCommande> lignesCommande = dao.getLignesCommandeByCommandeId(commandeId);
	    	 for (LigneCommande l :lignesCommande) {//chaque ligne de commande donne un objet Stock (Produit)
	    		 String nomProduit=dao.lireNomProduitDepuisStock(l.getProduitId());
	    		 Produit s=new Produit(l.getProduitId(),nomProduit,l.getQuantite());
	    		 listeStock.add(s);
	    	 }
	    	 return listeStock;
	    }
	  
	  
	  
	  
/****************************************************************************/	  
	  
	
	  
	  //methode pour l'interface Vente cote user
	  
/*****************************************************************************/
	  
	  //ces methodes s'execute successivement lors de la vente
	    public int vendreProduits() {
	    	LocalDate dateVente=LocalDate.now();
	    	int venteId=dao.creerVente(password,dateVente);
	    	return venteId;
	    }
	  
	    //ca serait afficher dans l'UI 
	    public ArrayList<LigneVente> specifierVente(int venteId, int produitId,int quantiteVendue,float prixVenteUnitaire) {
	    	try {
	    		dao.creerLigneVente(venteId,produitId, quantiteVendue, prixVenteUnitaire);
	    		//apres chaque produit vendue en decremente les quantiré de stock
	    		int quantiteRestante=dao.misajourStockApresVente(produitId, quantiteVendue);
	    		if (quantiteRestante<(dao.getQuantiteMinimale(produitId))) {
	    			throw new QuantiteMinimalAtteinteException("on a atteint le seuil minimal pour le produit "+produitId);//gestion local de l'erreur
	    		}
	    	}
	    	catch (QuantiteMinimalAtteinteException e) {
	    		e.getMessage();//ca s'affiche comme alerte
	    	}
	    	ArrayList<LigneVente> ligneVente=dao.getLignesVenteByVenteId(venteId);
	    	return ligneVente ;
	    }
	  
		  
	  
	  
	  
/***************************************************************/    

	    //methode pour afficher le stock pour l'utilisateur 
	    
/******************************************************************/
	    public ArrayList<Produit> showStock(){
	    	
	    	
	    	ArrayList<Produit> stock=dao.showStock();
	    	return stock;
	    }
	    
	    /***************************************************************/    


	    
	    
}
