package tn.com.notrePharmacie.controller;

import java.io.IOException;
import java.time.LocalDate;

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
import tn.com.notrePharmacie.model.Commande;

public class CommandeListController implements DashboardNavigator {
    private Stage stage ;
    private Scene scene;
    private String userRole;

    public void setUserRole(String role) {
        this.userRole = role;
    }
    @FXML
    private TableColumn<Commande, Integer> coProduit;
    @FXML private TableColumn<Commande, Integer> colId;
    @FXML private TableColumn<Commande, String> colFournisseur;
    @FXML private TableColumn<Commande, Integer> colquantite;
    @FXML private TableColumn<Commande, Double> colPrix;
    @FXML private TableColumn<Commande, LocalDate> colDate;
    @FXML private TableColumn<Commande, LocalDate> colDateReel;
    @FXML
    private TableView<Commande> tableCommandes;
    @FXML
    public void initialize() {
    	 ObservableList<Commande> commandesList = FXCollections.observableArrayList();
    	 //commandesList=requte sql tjib hedhom;
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        colquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDateReel.setCellValueFactory(new PropertyValueFactory<>("dateReel"));

        // Mettre les données dans le TableView
        tableCommandes.setItems(commandesList);
    }
    @FXML
    void addCommande(ActionEvent event) throws IOException {
    	   Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/remplir.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	 goBackToDashboard(event, userRole);
    }

    @FXML
    void deleteCommande(ActionEvent event) throws IOException {
    	   Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/commandDelete.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    void editCommande(ActionEvent event) throws IOException {
    	   Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/commandModifier.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

}