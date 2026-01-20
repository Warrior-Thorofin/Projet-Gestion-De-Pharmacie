package tn.com.notrePharmacie.model;
import java.time.LocalDate;


//we don't need user only keep it in database


public class User {
	private int employeId;//c'est le password
	private String nom;
	private String prenom;
	private int CIN;
	private String telephone;
	private String email;
	/*private LocalDate dateEmbauche;
	private LocalDate dateFinContrat;//null si CDI*/
	private String typeContrat;
	//private Boolean Actif;
}
