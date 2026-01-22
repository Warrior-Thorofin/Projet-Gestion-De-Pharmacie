package tn.com.notrePharmacie.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tn.com.notrePharmacie.model.HistoriqueVente;
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

public class HistoriqueVenteController {

    @FXML
    private TableView<HistoriqueVente> tableVentes;

    @FXML
    private TableColumn<HistoriqueVente, Integer> colId;

    @FXML
    private TableColumn<HistoriqueVente, Integer> colUsert;

    @FXML
    private TableColumn<HistoriqueVente, String> colproduit;

    @FXML
    private TableColumn<HistoriqueVente, Integer> colQuantite;

    @FXML
    private TableColumn<HistoriqueVente, Double> colTotal;

    @FXML
    private TableColumn<HistoriqueVente, LocalDate> colDate;

    // Méthode pour retourner à la scène précédente
    @FXML
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/dashborad.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Initialisation du TableView
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idVente"));
        colUsert.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colproduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Remplir le tableau avec des données fictives pour tester
        ObservableList<HistoriqueVente> ventes = FXCollections.observableArrayList();
        // ventes=requettesql tjibhom
        tableVentes.setItems(ventes);
    }
}
