package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProduitFormController {
	private Stage stage;
	private Scene scene;

    @FXML
    private Button annulerBtn;

    @FXML
    private TextField idField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField quantiteField;

    @FXML
    private Button sauvegarderBtn;

    @FXML
    private Label titleLabel;
    @FXML
    public void Sauvgarder(ActionEvent event ) {
    	//requete sql ajouter produit
    }
    @FXML
    public void annuler(ActionEvent event ) throws IOException {
    	   Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/produits.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }
    
}