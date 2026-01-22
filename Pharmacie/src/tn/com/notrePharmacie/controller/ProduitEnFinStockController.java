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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.com.notrePharmacie.model.Produit;

public class ProduitEnFinStockController implements DashboardNavigator{
	 private String userRole;

	    public void setUserRole(String role) {
	        this.userRole = role;
	    }
	 @FXML
	    private TableColumn<Produit, Integer> colId;

	    @FXML
	    private TableColumn<Produit, String> colNom;

	    @FXML
	    private TableColumn<Produit, Integer> colQuantite;

	    @FXML
	    private TableView<Produit> tableProduits;
	    public void back(ActionEvent event) throws IOException {
	    	 goBackToDashboard(event, userRole);}
        @FXML
        public void initialize() {
        	 ObservableList<Produit> produitList = FXCollections.observableArrayList();
        	 //produitListList=requte sql tjib hedhom elli a9al mel 5;
        	 colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        	 colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        	 colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        	 tableProduits.setItems(produitList);
        }
}

   
