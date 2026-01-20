package tn.com.notrePharmacie.service;
import tn.com.notrePharmacie.dao.*;
import tn.com.notrePharmacie.model.*;
import tn.com.notrePharmacie.exception.*;
import java.time.LocalDate;//yyyy-MM-dd
import java.util.ArrayList;


public class UserService {
	 private UserDao dao;//objet d'accees a la BD
	 private int password;//la session depend de lui
	 
	 public boolean login(String username, int password) {
        boolean ok = dao.isUser(username, password);
        return ok;
	  }
	    
	  public UserService(String username,int password) throws InvalidUserException {
	    	this.dao = new UserDao();
	    	this.password=password;
	    	if (login(username,password)==false) {
	        	throw new InvalidUserException("utilisateur invalide verifier votre mot de passe !");
	        }
	    	/*on commence la session tout est bien
	    	 * mais Admin ou User
	    	 */
	    	else {
	    		if(dao.isAdmin(username,password)==false) {
	    			//show user ui
	    		}
	    		else {
	    			//show admin ui
	    		}
	    		
	    		
	    	}
	    }
	  

	    
	    public Cache passerCommande(int fournisseurId,int commandeId,LocalDate datePrevue,int nbProdAchete) {
	    	dao.creerCommande(fournisseurId,commandeId,password,datePrevue);
	    	Cache cacheObj=new Cache(commandeId,nbProdAchete);
	    	return cacheObj;
	    }
	    
		  //pb!!!
	    
	    /*public void specifierCommande(int produitId,int quantite) {
	    	dao.creerLigneCommande(fournisseurId,commandeId,password,datePrevue,produitId,quantite);
	    }*/
	    
	   /* public void commandeEnCours() { on affiche la table avec JavaFX
	    	dao.lireCommandeEnCours();
	    }*/
	    
	  public void annulerCommande(int commandeId) {
	    	dao.supprimerCommande(commandeId);	
	    }
	    //on peut seulement modifier la quantite
	    public void modifierCommande(int commandeId,int produitId,int quantiteModifie) {
	    	dao.miseajourLigneCommande(commandeId,produitId,quantiteModifie);
	    }
	    public void recevoirCommande(int commandeId,LocalDate dateRelle) {
	    	dao.miseajourCommande(commandeId,dateRelle);
	    	int fournisseurId=dao.lireFournisseurIdDepuisCommande(commandeId);//acces a commande table
	    	
	    	ArrayList<Stock> listeStock=getStockDepuisCommande(fournisseurId,commandeId);	
	    	for (Stock s:listeStock) {
	    		if(dao.verifStock(s.getProduitId(),fournisseurId)==false){//si on n'a pas ce stock on le creer
	    			dao.creerStock(s.getProduitId(),fournisseurId,s.getQuantite());
	    		}
	    		else {//si ce stock est deja existant on met a jour la quantite
	    			int quantiteActuelle=dao.lireQuantiteDepuisStock(s.getProduitId(),fournisseurId);
	    			int nouvelleQuantite=quantiteActuelle+s.getQuantite();
	    			dao.misajourStock(s.getProduitId(),fournisseurId,nouvelleQuantite);
	    		}
	    	}
	    }
	    
	    public ArrayList<Stock> getStockDepuisCommande(int fournisseurId,int commandeId){
	    	 ArrayList<Stock> listeStock = new ArrayList<>();
	    	 ArrayList<LigneCommande> lignesCommande = dao.getLignesCommandeByCommandeId(commandeId);
	    	 for (LigneCommande l :lignesCommande) {//chaque ligne de commande donne un objet Stock
	    		 Stock s=new Stock(fournisseurId,l.getProduitId(),l.getQuantite());
	    		 listeStock.add(s);
	    	 }
	    	 return listeStock;
	    }
	    
	    
	    public void gestionVente() {
	    	
	    	
	    	
	    	
	    	
	    	
	    }
	    
	
	
	
	
	

}
