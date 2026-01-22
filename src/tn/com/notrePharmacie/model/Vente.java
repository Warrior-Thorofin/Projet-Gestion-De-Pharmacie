package tn.com.notrePharmacie.model;
import java.time.LocalDate;

public class Vente {
	private int venteId;
	private int userId;
	private LocalDate dateVente;
	public Vente(int venteId,int userId,LocalDate dateVente) {
		this.dateVente=dateVente;
		this.userId=userId;
		this.venteId=venteId;
	}
	
	
	
	
	
	

}
