package tn.com.notrePharmacie.model;

import java.time.LocalDate;

public class Commande {
    private int id;
    private String produit;
    private int quantite;
    private double prix;
    private String fournisseur;
    private LocalDate date;
    private LocalDate dateReel;

    public Commande(int id, String produit, int quantite, double prix,
                    String fournisseur, LocalDate date, LocalDate dateReel) {
        this.id = id;
        this.produit = produit;
        this.quantite = quantite;
        this.prix = prix;
        this.fournisseur = fournisseur;
        this.date = date;
        this.dateReel = dateReel;
    }

    // getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProduit() { return produit; }
    public void setProduit(String produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getFournisseur() { return fournisseur; }
    public void setFournisseur(String fournisseur) { this.fournisseur = fournisseur; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDate getDateReel() { return dateReel; }
    public void setDateReel(LocalDate dateReel) { this.dateReel = dateReel; }
}
