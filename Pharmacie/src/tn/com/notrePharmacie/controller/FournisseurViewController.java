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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.com.notrePharmacie.model.Fournisseur;

public class FournisseurViewController implements DashboardNavigator{
	private Scene scene ;
	private Stage stage;
	 private String userRole;
	 public void setUserRole(String role) {
	       this.userRole = role;
	    }

    @FXML private TableView<Fournisseur> tableFournisseur;
    @FXML private TableColumn<Fournisseur, Integer> colId;
    @FXML private TableColumn<Fournisseur, String> colNom;
    @FXML private TableColumn<Fournisseur, String> colNumero;
    @FXML private TableColumn<Fournisseur, String> colAdresse;

    private ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        //fournisseurs= appel de la requete sql
        tableFournisseur.setItems(fournisseurs);
    }

    @FXML
    void ajouter(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/fournisseurajouter.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/fournisseurModifier.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    void supprimer(ActionEvent event) throws IOException {
        Fournisseur f = tableFournisseur.getSelectionModel().getSelectedItem();
        if (f != null) fournisseurs.remove(f);
        Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/fournisseurSupp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    void back(ActionEvent event) throws IOException {
    	  goBackToDashboard(event, userRole);
    }
}
