package tn.com.notrePharmacie.model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Vente {

    private IntegerProperty idVente;
    private IntegerProperty idUser;
    private StringProperty nomProduit;
    private IntegerProperty quantite;
    private DoubleProperty total;
    private ObjectProperty<LocalDate> dateVente;

    public Vente(int idVente, int idUser, String nomProduit, int quantite, double total, LocalDate dateVente) {
        this.idVente = new SimpleIntegerProperty(idVente);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.nomProduit = new SimpleStringProperty(nomProduit);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.total = new SimpleDoubleProperty(total);
        this.dateVente = new SimpleObjectProperty<>(dateVente);
    }

    // GETTERS
    public int getIdVente() { return idVente.get(); }
    public int getIdUser() { return idUser.get(); }
    public String getNomProduit() { return nomProduit.get(); }
    public int getQuantite() { return quantite.get(); }
    public double getTotal() { return total.get(); }
    public LocalDate getDateVente() { return dateVente.get(); }

    // SETTERS
    public void setIdVente(int idVente) { this.idVente.set(idVente); }
    public void setIdUser(int idUser) { this.idUser.set(idUser); }
    public void setNomProduit(String nomProduit) { this.nomProduit.set(nomProduit); }
    public void setQuantite(int quantite) { this.quantite.set(quantite); }
    public void setTotal(double total) { this.total.set(total); }
    public void setDateVente(LocalDate dateVente) { this.dateVente.set(dateVente); }

    // PROPERTIES -> nécessaires pour TableView
    public IntegerProperty idVenteProperty() { return idVente; }
    public IntegerProperty idUserProperty() { return idUser; }
    public StringProperty nomProduitProperty() { return nomProduit; }
    public IntegerProperty quantiteProperty() { return quantite; }
    public DoubleProperty totalProperty() { return total; }
    public ObjectProperty<LocalDate> dateVenteProperty() { return dateVente; }
}
