package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FournisseurAddController {

    @FXML private TextField nomField;
    @FXML private TextField numeroField;
    @FXML private TextField adresseField;

    @FXML
    void save(ActionEvent event) {
        String nom = nomField.getText();
        int numero = Integer.parseInt(numeroField.getText());
        String adresse = adresseField.getText();
        // requete sql
    }

    @FXML
    void cancel(ActionEvent event ) throws IOException{
    	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/Fournisseurs.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }
}
