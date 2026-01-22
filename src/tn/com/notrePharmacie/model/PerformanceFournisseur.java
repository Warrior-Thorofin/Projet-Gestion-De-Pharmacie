package tn.com.notrePharmacie.model;

public class PerformanceFournisseur {
    private int fournisseurId;
    private String nom;
    private String telephone;
    private String email;
    private double retardMoyen; // en jours
  
    public PerformanceFournisseur(int fournisseurId, String nom, String telephone,String email, double retardMoyen) {
        this.fournisseurId = fournisseurId;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.retardMoyen = retardMoyen;
    }
    
    public int getFournisseurId() {
        return fournisseurId;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getTelephone() {
        return telephone;
    }

    
    public String getEmail() {
        return email;
    }
    
    public double getRetardMoyen() {
        return retardMoyen;
    }
}