package tn.com.notrePharmacie.dao;
import tn.com.notrePharmacie.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import tn.com.notrePharmacie.util.DataBaseConnection;


public class UserDao {
	private Connection connection;
	
	public UserDao() {//applying singletonPattern
	this.connection = DataBaseConnection.getConnection();
	}
	
	public boolean isUser(String nom, int userId) {
	    String sql = "SELECT COUNT(*) FROM User WHERE nom = ? AND userId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, nom);
	        stmt.setInt(2, userId);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; // true si utilisateur trouvé
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	public boolean isAdmin(String nom, int userId) {
	    String sql = "SELECT admin FROM User WHERE nom = ? AND userId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, nom);
	        stmt.setInt(2, userId);
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int adminValue = rs.getInt("admin");
	            return adminValue == 1; // true si admin, false sinon
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false; // Par défaut, pas admin
	}
	
	public void supprimerCommande(int commandeId) {
	    String sql = "DELETE FROM Commande WHERE commandeId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, commandeId);
	        
	        int rowsDeleted = stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Erreur lors de la suppression de la commande");
	    }
	}
	
	public void miseajourLigneCommande(int commandeId, int produitId, int quantiteModifie) {
	    String sql = "UPDATE LigneCommande SET quantite = ? WHERE commandeId = ? AND produitId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, quantiteModifie);
	        stmt.setInt(2, commandeId);
	        stmt.setInt(3, produitId);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void miseajourCommande(int commandeId, LocalDate dateReelle) {
	    String sql = "UPDATE Commande SET dateReelle = ? WHERE commandeId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setDate(1, java.sql.Date.valueOf(dateReelle));
	        stmt.setInt(2, commandeId);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public int lireFournisseurIdDepuisCommande(int commandeId) {
	    String sql = "SELECT fournisseurId FROM Commande WHERE commandeId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, commandeId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("fournisseurId");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return -1; // Retourne -1 si aucun fournisseur trouvé
	}
	
	
	
	
	public boolean verifStock(int produitId, int fournisseurId) {
	    String sql = "SELECT quantite FROM stock WHERE produitId = ? AND fournisseurId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        stmt.setInt(2, fournisseurId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        return rs.next();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	public void creerStock(int produitId, int fournisseurId, int quantite) {
	    String sql = "INSERT INTO stock (produitId, fournisseurId, quantite) VALUES (?, ?, ?)";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        stmt.setInt(2, fournisseurId);
	        stmt.setInt(3, quantite);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public int lireQuantiteDepuisStock(int produitId, int fournisseurId) {
	    String sql = "SELECT quantite FROM stock WHERE produitId = ? AND fournisseurId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        stmt.setInt(2, fournisseurId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("quantite");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return 0; // retournant 0 si aucune ligne trouvée
	}
	
	
	public void misajourStock(int produitId, int fournisseurId, int nouvelleQuantite) {
	    String sql = "UPDATE stock SET quantite = ? WHERE produitId = ? AND fournisseurId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, nouvelleQuantite);
	        stmt.setInt(2, produitId);
	        stmt.setInt(3, fournisseurId);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public ArrayList<LigneCommande> getLignesCommandeByCommandeId(int commandeId) {
	    ArrayList<LigneCommande> lignesCommande = new ArrayList<>();
	    String sql = "SELECT * FROM LigneCommande WHERE commandeId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, commandeId);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            LigneCommande ligne = new LigneCommande(rs.getInt("commandeId"),rs.getInt("produitId"),rs.getInt("quantite"));
	            lignesCommande.add(ligne);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return lignesCommande;
	}
	
	
	
	public void creerCommande(int fournisseurId,int password,int commandeId,LocalDate datePrevue) {
	    String sql = "INSERT INTO commande (fournisseurId,commandeId,userId,datePrevue) VALUES (?, ?,?,?)";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, fournisseurId);
	        stmt.setInt(2, commandeId);
	        stmt.setInt(3, password);
	        stmt.setDate(4, java.sql.Date.valueOf(datePrevue));//java.sql.Date.valueOf(LocalDate) convertit LocalDate en java.sql.Date compatible avec JDBC
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}



