package tn.com.notrePharmacie.dao;
import tn.com.notrePharmacie.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import tn.com.notrePharmacie.util.DataBaseConnection;

public class AdminDao extends UserDao{
private Connection connection;
	
	public AdminDao() {//applying singletonPattern
	this.connection = DataBaseConnection.getConnection();
	}
	
	
	//Interaction suite au click sur le bouton Produit coté admin
	
		/***********************************************************/
		public void creerProduit(int produitId, int quantiteMinimale, String nomProduit) {
		    String sql = "INSERT INTO Produit (produitId, quantiteMinimale, nomProduit) VALUES (?, ?, ?)";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
		        stmt.setInt(1, produitId);
		        stmt.setInt(2, quantiteMinimale);
		        stmt.setString(3, nomProduit);
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		public void supprimerProduit(int produitId) {
		    String sql = "DELETE FROM Produit WHERE produitId = ?";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
		        stmt.setInt(1, produitId);
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		/**************************************************************/
		//acces a l'historique des ventes coté admin
		public ArrayList<Vente> getHistory() {
		    ArrayList<Vente> listeVentes = new ArrayList<>();
		    String sql = "SELECT * FROM vente";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		        
		        while (rs.next()) {
		            Vente vente = new Vente(rs.getInt("venteId"),rs.getInt("userId"),rs.getDate("dateVente").toLocalDate());
		            listeVentes.add(vente);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return listeVentes;
		}
		
		/**************************************************************/
		//calcul chiffre d'affaire a partir de la base de donnée
		
		public int calcChiffreAffaire(int mois, int annee) {
		    String sql = "SELECT SUM(quantiteVendue * prixVenteUnitaire) AS chiffreAffaire " +
		                 "FROM lignevente " +
		                 "WHERE venteId IN ( " +
		                 "    SELECT venteId " +
		                 "    FROM vente " +
		                 "    WHERE dateVente BETWEEN ? AND ? " +
		                 ")";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
		        // Créeant les dates de début et fin du mois
		        LocalDate dateDebut = LocalDate.of(annee, mois, 1);
		        LocalDate dateFin = dateDebut.withDayOfMonth(dateDebut.lengthOfMonth());
		        
		        stmt.setDate(1, java.sql.Date.valueOf(dateDebut));
		        stmt.setDate(2, java.sql.Date.valueOf(dateFin));
		        
		        ResultSet rs = stmt.executeQuery();
		        
		        if (rs.next()) {
		            return rs.getInt("chiffreAffaire");
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return 0; // retournant 0 si aucune vente trouvée
		}
	
		/*******************************************************************/
		//calcul de la performance de chaque fournisseur en tenant compte de retard de livraisant comme critere d'evaluation
		public ArrayList<PerformanceFournisseur> calcPerformanceFournisseur() {
		    ArrayList<PerformanceFournisseur> listeFournisseurs = new ArrayList<>();
		    
		    String sql = "SELECT f.fournisseurId, f.nom, f.telephone, f.email, " +
		                 "       AVG(DATEDIFF(c.dateReelle, c.datePrevue)) AS retardMoyen " +
		                 "FROM Fournisseur f,Commande c " +
		                 "Where f.fournisseurId=c.fournisseurId " +
		                 "GROUP BY f.fournisseurId, f.nom, f.telephone, f.email";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		        
		        while (rs.next()) {
		            PerformanceFournisseur pf = new PerformanceFournisseur(rs.getInt("fournisseurId"),rs.getString("nom"),rs.getString("telephone"),rs.getString("email"),rs.getDouble("retardMoyen"));
		            listeFournisseurs.add(pf);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return listeFournisseurs;
		}

		
		/***********************************************************/
		
		/*c'est l'admin qui est responsabe de controle continu donc c'est celui 
		qui voit le stock manquant*/
		
		/*************************************************************/
		/**************************************************************************************/
		//affichage de stock suite au click sur le bouton Stock
		public ArrayList<Produit> showStockManquant() {
		    ArrayList<Produit> listeProduits = new ArrayList<>();
		    String sql = "SELECT * FROM produit"
		    		+ 	 "WHERE quantite <  quantiteMinimal	 ";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		        
		        while (rs.next()) {
		            Produit produit = new Produit(rs.getInt("produitId"),rs.getString("nomProduit"),rs.getInt("quantite"));
		            listeProduits.add(produit);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return listeProduits;
		}
		
		/***********************************************************************************/
		
		
		//afficher lesutilisateurs de systeme a l'admin
		/***************************************************************/
		public ArrayList<User> showUsers() {
		    ArrayList<User> listeUsers = new ArrayList<>();
		    String sql = "SELECT * FROM User";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {
		        
		        while (rs.next()) {
		            User user = new User(
		                rs.getInt("UserId"),
		                rs.getString("userName"),
		                rs.getString("nom"),
		                rs.getString("prenom"),
		                rs.getInt("CIN"),
		                rs.getString("telephone"),
		                rs.getString("email"),
		                rs.getBoolean("isAdmin")
		            );
		            
		            listeUsers.add(user);
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return listeUsers;
		}
		
		
/***********************************************************************************/
		
		
		//creation de la table user
		/***************************************************************/
		public void creerUtilisateur(int userId, String userName, String nom, String prenom, int CIN, String telephone, String email, boolean isAdmin) {
			String sql = "INSERT INTO User (userId, userName, nom, prenom, CIN, telephone, email, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setString(2, userName);
			stmt.setString(3, nom);
			stmt.setString(4, prenom);
			stmt.setInt(5, CIN);
			stmt.setString(6, telephone);
			stmt.setString(7, email);
			stmt.setBoolean(8, isAdmin);
			
			stmt.executeUpdate();

			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
		
	/*************************************************************************/
		
		
		//suppression  a partir de  la table user
/***************************************************************/
		public void supprimerUtilisateur(int userId) {
		    String sql = "DELETE FROM User WHERE userId = ?";
		    
		    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
		        stmt.setInt(1, userId);
		        stmt.executeUpdate();
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		
		
		
		
	
	
	
	
	
}
