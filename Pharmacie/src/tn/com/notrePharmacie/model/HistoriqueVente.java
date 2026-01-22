package tn.com.notrePharmacie.model;

import java.time.LocalDate;

public class HistoriqueVente {
    private int idVente;
    private int idUser;
    private String nomProduit;
    private int quantite;
    private double total;
    private LocalDate date; 

    public HistoriqueVente(int idVente, int idUser, String nomProduit, int quantite, double total, LocalDate date) {
        this.idVente = idVente;
        this.idUser = idUser;
        this.nomProduit = nomProduit;
        this.quantite = quantite;
        this.total = total;
        this.date = date;
    }

    public int getIdVente() { return idVente; }
    public void setIdVente(int idVente) { this.idVente = idVente; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getNomProduit() { return nomProduit; }
    public void setNomProduit(String nomProduit) { this.nomProduit = nomProduit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
