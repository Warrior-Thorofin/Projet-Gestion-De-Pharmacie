package tn.com.notrePharmacie.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LigneCommande {

    private IntegerProperty idProduit;
    private IntegerProperty quantite;
    private DoubleProperty prixUnitaire;

    public LigneCommande(int idProduit, int quantite, double prixUnitaire) {
        this.idProduit = new SimpleIntegerProperty(idProduit);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.prixUnitaire = new SimpleDoubleProperty(prixUnitaire);
    }

    // Getters
    public int getIdProduit() { return idProduit.get(); }
    public int getQuantite() { return quantite.get(); }
    public double getPrixUnitaire() { return prixUnitaire.get(); }

    // Setters
    public void setIdProduit(int id) { this.idProduit.set(id); }
    public void setQuantite(int q) { this.quantite.set(q); }
    public void setPrixUnitaire(double p) { this.prixUnitaire.set(p); }

    // Properties
    public IntegerProperty idProduitProperty() { return idProduit; }
    public IntegerProperty quantiteProperty() { return quantite; }
    public DoubleProperty prixUnitaireProperty() { return prixUnitaire; }
}
