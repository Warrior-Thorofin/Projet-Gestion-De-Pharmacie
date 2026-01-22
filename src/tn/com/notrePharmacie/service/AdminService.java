package tn.com.notrePharmacie.service;
import tn.com.notrePharmacie.dao.*;

import tn.com.notrePharmacie.model.*;
import tn.com.notrePharmacie.exception.*;
import java.time.LocalDate;//yyyy-MM-dd
import java.util.ArrayList;



public class AdminService extends UserService {
	private AdminDao  daoadmin=new AdminDao();
	public AdminService(String username,int password){
		super(username,password);
	}

	  ////methode pour l'interface Produit coté admin
/***********************************************************/		
	  public void ajouterProduit (int produitId,String nomProduit,int quantiteMinimal) {
		  daoadmin.creerProduit(produitId,quantiteMinimal,nomProduit);
	  }
	  public void supprimerProduit (int produitId) {
		  daoadmin.supprimerProduit(produitId);
	  }
/****************************************************************/	  
	  ///methode assurant acces historique vente pour l'admin
	  
	  
	  //s'affiche dans l'ui historique
	  public ArrayList<Vente> accesHistorique() {
		  ArrayList<Vente> vente=daoadmin.getHistory();
		  return vente;
	  }
	  
/*****************************************************************/
	  
	  
	  //partie statistique
/******************************************************************/
	  //voir chiffre d'affaire
	  public int getChiffreAffaire(int mois,int année) {
		  int chiffreAffaire=daoadmin.calcChiffreAffaire(mois ,année);
		  return chiffreAffaire;
	  }
	  
	  //controler la performance des fournisseurs cette methode est utiliser pour l'UI
	  public ArrayList<PerformanceFournisseur> performanceFournisseur(){
		  ArrayList<PerformanceFournisseur> listFournisseur=daoadmin.calcPerformanceFournisseur();
		  return listFournisseur;
	  }

	/********************************************************/  
	  
	    //methode pour afficher les produits depassant le seuil minimal
	    
/******************************************************************/
	    public ArrayList<Produit> showStockManquant(){
	    	ArrayList<Produit> stock=daoadmin.showStockManquant();
	    	return stock;
	    }
	    
	/***************************************************************/    
	    
	    //methode pour gerer les utilisateurs (empoyé a l'UI Utilisateurs) 
/******************************************************************/
	    
	    public ArrayList<User> showUsers(){
	    	ArrayList<User> listUsers=daoadmin.showUsers();
	    	return listUsers;
	    }
	    
/*********************************************************************************/
	   
	    
	    //utilisateur ajouter a la table User
/*******************************************************************************/

	    public void addUser(int userId,String userName,String nom,String prenom,int CIN,String telephone,String email,boolean isAdmin) {
	    	daoadmin.creerUtilisateur( userId,userName, nom, prenom,CIN,telephone,email,isAdmin);
	    }
	    

/*********************************************************************************/
	   
	    
	    //utilisateur supprimer de la table User
/*******************************************************************************/

	    public void deleteUser(int userId) {
	    	daoadmin.supprimerUtilisateur(userId);
	    }   
	    
	  
	  
	  
	  
	  
	  
	  
	  



}

