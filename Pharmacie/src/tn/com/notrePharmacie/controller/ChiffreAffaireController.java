package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChiffreAffaireController {

    @FXML
    private ComboBox<Integer> anneeCombo;  
    @FXML
    private ComboBox<Integer> moisCombo;  

    @FXML
    private TextField chiffreAfaire;

    @FXML
    public void initialize() {
        ObservableList<Integer> annees = FXCollections.observableArrayList(2024, 2025, 2026);
        anneeCombo.setItems(annees);
        ObservableList<Integer> mois = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            mois.add(i);
        }
        moisCombo.setItems(mois);
    }

    @FXML
    public void handleAfficher(ActionEvent event) {
        Integer annee = anneeCombo.getValue();
        Integer mois = moisCombo.getValue();

        if (annee == null || mois == null) {
            chiffreAfaire.setText("Veuillez sélectionner l'année et le mois !");
            return;
        }

       //double chiffre= requete sql 

        chiffreAfaire.setText(String.format("%.2f D", chiffre));
    }
    @FXML
    public void back(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/dashborad.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    	
    }
}
