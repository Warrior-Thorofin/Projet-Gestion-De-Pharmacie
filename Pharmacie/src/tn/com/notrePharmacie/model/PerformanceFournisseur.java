package tn.com.notrePharmacie.model;

public class PerformanceFournisseur {
    private int idFournisseur;
    private String nomFournisseur;
    private int nbrCommandes;
    private double retardMoyen;

    public PerformanceFournisseur(int idFournisseur, String nomFournisseur, int nbrCommandes, double retardMoyen) {
        this.idFournisseur = idFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.nbrCommandes = nbrCommandes;
        this.retardMoyen = retardMoyen;
    }

    public int getIdFournisseur() { return idFournisseur; }
    public void setIdFournisseur(int idFournisseur) { this.idFournisseur = idFournisseur; }

    public String getNomFournisseur() { return nomFournisseur; }
    public void setNomFournisseur(String nomFournisseur) { this.nomFournisseur = nomFournisseur; }

    public int getNbrCommandes() { return nbrCommandes; }
    public void setNbrCommandes(int nbrCommandes) { this.nbrCommandes = nbrCommandes; }

    public double getRetardMoyen() { return retardMoyen; }
    public void setRetardMoyen(double retardMoyen) { this.retardMoyen = retardMoyen; }
}
