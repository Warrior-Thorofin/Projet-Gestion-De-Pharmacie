package tn.com.notrePharmacie.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardAdminController{
	private Stage stage;
	private Scene scene;
	private String userRole;
	 @FXML private Button chiffreAffaireButton;
	 @FXML private Button historiqueButton;
     @FXML private Button performanceButton;
     @FXML private Button UtilisateursButton;


    public void setUserRole(String role) {
        this.userRole = role;
    }
    public void updateUI() {
        if ("user".equalsIgnoreCase(userRole)) {
            chiffreAffaireButton.setVisible(false);
            historiqueButton.setVisible(false);
            performanceButton.setVisible(false);
            UtilisateursButton.setVisible(false);
        }
    }
	@FXML
    void logout(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/tst.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void alerte(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/com/notrePharmacie/view/produitEnFindeStock.fxml"));
    	Parent root = loader.load();
    	ProduitEnFinStockController controller = loader.getController();
    	controller.setUserRole(userRole);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void chiffreAffaire(ActionEvent event) throws IOException {	
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/ChiffreAfaire.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openCommandes(ActionEvent event) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("/tn/com/notrePharmacie/view/cmnd.fxml"));
    	Parent root = loader.load();
    	CommandeListController controller = loader.getController();
    	controller.setUserRole(userRole);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openHistorique(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/historiqueVente.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openProduits(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/com/notrePharmacie/view/Produits.fxml"));
    	Parent root = loader.load();
    	ProduitsListController controller = loader.getController();
    	controller.setUserRole(userRole);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openStock(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/cmnd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openUsers(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/userview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void perfermance(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/PerformanceFournisseurs.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void openFournisseurs(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/com/notrePharmacie/view/FournisseurView.fxml"));
    	Parent root = loader.load();
    	FournisseurViewController controller = loader.getController();
    	controller.setUserRole(userRole);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
