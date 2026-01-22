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

public class FournisseurDeleteController {

    @FXML
    private TextField IdFournisseur;

    @FXML
    private Button annulerBtn;

    @FXML
    private Label confirmationLabel;

    @FXML
    private Button deleteBtn;

    @FXML
    void cancel(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/FournisseurView.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    @FXML
    void confirm(ActionEvent event) {
       // requete sql : delete from where
    }

}