package tn.com.notrePharmacie.util;
import tn.com.notrePharmacie.service.*;
import tn.com.notrePharmacie.dao.*;
import tn.com.notrePharmacie.exception.*;




public class Login {
	

	 public static UserService login(String username, int password) throws InvalidUserException{
		 UserDao dao=new UserDao();//une fois login terminer l'objet dao est supprimer (composition)
		 boolean ok = dao.isUser(username, password);
		 if(ok==false) {
			 throw new InvalidUserException("Login Incorrecte");
		 }
	    	/*on commence la session tout est bien
	    	 * mais Admin ou User
	    	 */
		 else {
			 if(dao.isAdmin(username,password)==false) {//c'est pas un admin
				 return new UserService(username,password);
			 }
	    		else {
	    			//show admin ui
	    			return new AdminService(username,password);
	    		}
		 } 
	  }	
}
