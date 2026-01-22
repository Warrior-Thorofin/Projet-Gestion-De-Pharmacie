package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProduitsListController implements DashboardNavigator {
    private Stage stage;
    private Scene scene;
    private String userRole;

    public void setUserRole(String role) {
        this.userRole = role;
    }
    @FXML
    private TableColumn<?, ?> colEtat;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNom;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableView<?> tableProduits;
    

    @FXML
    void addProduit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/ProduitsForm.fxml"));
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
    void deleteProduit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/ProduiySupp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editProduit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/ProduitModifier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
