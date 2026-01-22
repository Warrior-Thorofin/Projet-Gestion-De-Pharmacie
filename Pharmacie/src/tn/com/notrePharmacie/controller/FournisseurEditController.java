package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FournisseurEditController {
    private Scene scene ;
    private Stage stage ;
    @FXML
    private TextField Nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField id;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField numero;

    @FXML
    void cancel(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/FournisseurView.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    void save(ActionEvent event) {
     // requete sql modifier 

    }

}