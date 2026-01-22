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

public class ProduitSuppController {
	private Scene scene;
	private Stage stage;

    @FXML
    private Button annulerBtn;

    @FXML
    private Label confirmationLabel;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField idField;
    public void Supprimer(ActionEvent event ) {
    	// requete sql delete from produit where 
    }
    public void annuler(ActionEvent event ) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/produits.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
