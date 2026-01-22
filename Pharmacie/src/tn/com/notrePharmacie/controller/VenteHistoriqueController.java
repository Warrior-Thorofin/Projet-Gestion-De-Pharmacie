package tn.com.notrePharmacie.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.com.notrePharmacie.model.Vente;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class VenteHistoriqueController {

    @FXML
    private TableView<Vente> tableVentes;

    @FXML
    private TableColumn<Vente, Integer> colId;

    @FXML
    private TableColumn<Vente, Integer> colUsert;

    @FXML
    private TableColumn<Vente, String> colproduit;

    @FXML
    private TableColumn<Vente, Integer> colQuantite;

    @FXML
    private TableColumn<Vente, Double> colTotal;

    @FXML
    private TableColumn<Vente, LocalDate> colDate;

    private ObservableList<Vente> ventesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés du modèle
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsert.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colproduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Charger les données depuis la base
        ventesList=loadVentes();

        // Affecter la liste observable au tableau
        tableVentes.setItems(ventesList);
    }

   
    @FXML
    public void back (ActionEvent event ) throws IOException {
   	 Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/dashborad.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
}
