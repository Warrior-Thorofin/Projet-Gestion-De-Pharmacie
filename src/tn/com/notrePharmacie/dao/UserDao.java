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
	
	//Interaction lors de login
	/*************************************************/
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
	/*************************************************************/
	
	
	
	//Interaction suite au click sur le bouton Produit coté user
	
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
	
	/***********************************************************/
	
	
	//Interaction suite au click sur le bouton Commande  cote user 
	
	/***********************************************************/
	public int creerCommande(int fournisseurId, int password, LocalDate datePrevue, LocalDate dateCommande) {
	    String sql = "INSERT INTO commande (fournisseurId, userId, datePrevue, dateCommande) VALUES (?, ?, ?, ?)";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, fournisseurId);
	        stmt.setInt(2, password);
	        stmt.setDate(3, java.sql.Date.valueOf(datePrevue));
	        stmt.setDate(4, java.sql.Date.valueOf(dateCommande));
	        stmt.executeUpdate();
	        
	        int ans = getCommandeId(fournisseurId, password, datePrevue, dateCommande);
	        return ans;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
		    return -1;
	    }
	}

	public int getCommandeId(int fournisseurId, int userId, LocalDate datePrevue, LocalDate dateCommande) {
	    String sql = "SELECT commandeId FROM commande WHERE fournisseurId = ? AND userId = ? AND datePrevue = ? AND dateCommande = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, fournisseurId);
	        stmt.setInt(2, userId);
	        stmt.setDate(3, java.sql.Date.valueOf(datePrevue));
	        stmt.setDate(4, java.sql.Date.valueOf(dateCommande));
	        
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("commandeId");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	

	
	public void creerLigneCommande(int commandeId,int produitId,int quantite,float prixVenteUnitaire) {
		String sql = "INSERT INTO lignecommande (commandeId,produitid, quantite,prixAchatUnitaire) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, commandeId);
	        stmt.setInt(2, produitId);
	        stmt.setInt(3, quantite);
	        stmt.setDouble(4, prixVenteUnitaire);
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
	            LigneCommande ligne = new LigneCommande(rs.getInt("commandeId"),rs.getInt("produitId"),rs.getInt("quantite"),rs.getInt("prixVenteUnitaire"));
	            lignesCommande.add(ligne);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return lignesCommande;
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
	
	
	public void miseajourQuantiteCommande(int commandeId, int produitId, int newQuantite) {
	    String sql = "UPDATE lignecommande SET quantite = ? WHERE commandeId = ? AND produitId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, newQuantite);
	        stmt.setInt(2, commandeId);
	        stmt.setInt(3, produitId);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void miseajourDatePrevueCommande(int commandeId, LocalDate newDatePrevue) {
	    String sql = "UPDATE commande SET datePrevue = ? WHERE commandeId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setDate(1, java.sql.Date.valueOf(newDatePrevue));
	        stmt.setInt(2, commandeId);
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
	
	/**********************************************************/
	
	
	
	
	//Mise a jour automatique de stock
	
	/***********************************************************/
	
	public void creerStock(int produitId,String nomProduit, int quantite) {
	    String sql = "INSERT INTO produit (produitId, quantite) VALUES (?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        stmt.setInt(2, quantite);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	public boolean verifStock(int produitId) {
	    String sql = "SELECT quantite FROM produit WHERE produitId = ? ";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        ResultSet rs = stmt.executeQuery();
	        
	        return rs.next();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	
	
	public int lireQuantiteDepuisStock(int produitId) {
	    String sql = "SELECT quantite FROM produit WHERE produitId = ? ";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("quantite");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return 0; // retournant 0 si aucune ligne trouvée
	}
	public String lireNomProduitDepuisStock(int produitId) {
	    String sql = "SELECT nomProduit FROM produit WHERE produitId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getString("nomProduit");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return null; // retournant null si aucune ligne trouvée
	}
	
	
	public void misajourStock(int produitId, int nouvelleQuantite) {
	    String sql = "UPDATE produit SET quantite = ? WHERE produitId = ? ";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, nouvelleQuantite);
	        stmt.setInt(2, produitId);
	        
	        stmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	/**************************************************************************************/
	
	
	
	
	//interaction suite au click bouton vente cote user
	
	/****************************************************************************************/
	
	
	public int creerVente(int password, LocalDate dateVente) {
	    String sql = "INSERT INTO vente (userId, dateVente) VALUES (?, ?)";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, password);
	        stmt.setDate(2, java.sql.Date.valueOf(dateVente));
	        stmt.executeUpdate();
	        int ans = getVenteId(password, dateVente);
	        return ans;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
		    return -1;
	    }
	}

	public int getVenteId(int userId, LocalDate dateVente) {
	    String sql = "SELECT venteId FROM vente WHERE  userId = ? AND dateVente = ? ";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        stmt.setDate(2, java.sql.Date.valueOf(dateVente));
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt("venteId");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	

	public void creerLigneVente(int venteId,int produitId,int quantiteVendue,float prixVenteUnitaire) {
		String sql = "INSERT INTO lignevente (venteeId,produitid, quantiteVendue,prixVenteUnitaire) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, venteId);
	        stmt.setInt(2, produitId);
	        stmt.setInt(3, quantiteVendue);
	        stmt.setDouble(4, prixVenteUnitaire);
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
		  
	    }
	}

	public ArrayList<LigneVente> getLignesVenteByVenteId(int venteId) {
	    ArrayList<LigneVente> lignesVente = new ArrayList<>();
	    String sql = "SELECT * FROM LigneVente WHERE venteId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, venteId);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            LigneVente ligne = new LigneVente(rs.getInt("venteId"),rs.getInt("produitId"),rs.getInt("quantiteVendue"),rs.getInt("prixVenteUnitaire"));
	            lignesVente.add(ligne);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return lignesVente;
	}
	

	

	public int misajourStockApresVente(int produitId, int quantiteVendue) {
	    int quantite = lireQuantiteDepuisStock(produitId); // On accede la quantité dans stock
	    String sql = "UPDATE produit SET quantite = ? WHERE produitId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, quantite - quantiteVendue);
	        stmt.setInt(2, produitId);
	        stmt.executeUpdate();
	        return quantite - quantiteVendue;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // En erreur
	    }
	}

	public int getQuantiteMinimale(int produitId) {
	    String sql = "SELECT quantiteminimale FROM produit WHERE produitId = ?";
	    
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setInt(1, produitId);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("quantiteminimale");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return 0; // retournant 0 si aucune ligne trouvée
	}
	
	

/**************************************************************************************/
	//affichage de stock suite au click sur le bouton Stock
	public ArrayList<Produit> showStock() {
	    ArrayList<Produit> listeProduits = new ArrayList<>();
	    String sql = "SELECT * FROM produit";
	    
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



