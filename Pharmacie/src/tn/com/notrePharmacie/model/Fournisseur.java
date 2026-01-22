package tn.com.notrePharmacie.model;

public class Fournisseur {
    private int id;
    private String nom;
    private String numero;
    private String adresse;

    public Fournisseur(int id, String nom, String numero, String adresse) {
        this.id = id;
        this.nom = nom;
        this.numero = numero;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNumero() {
        return numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
