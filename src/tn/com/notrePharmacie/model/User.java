package tn.com.notrePharmacie.model;
import java.time.LocalDate;


//we don't need user only keep it in database


public class User {
	private int employeId;//c'est le password
	private String userName;
	private String nom;
	private String prenom;
	private int CIN;
	private String telephone;
	private String email;
	private boolean isAdmin;
	public User(int employeId,String userName, String nom, String prenom, int CIN, String telephone, String email,boolean isAdmin) {
	    this.employeId = employeId;
	    this.userName=userName;
	    this.nom = nom;
	    this.prenom = prenom;
	    this.CIN = CIN;
	    this.telephone = telephone;
	    this.email = email;
	    this.isAdmin=isAdmin;
	    
	}
}
