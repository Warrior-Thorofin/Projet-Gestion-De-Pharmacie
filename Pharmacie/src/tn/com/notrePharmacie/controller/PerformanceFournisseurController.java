package tn.com.notrePharmacie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tn.com.notrePharmacie.model.PerformanceFournisseur;
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

public class PerformanceFournisseurController {

    @FXML
    private TableView<PerformanceFournisseur> perfTable;

    @FXML
    private TableColumn<PerformanceFournisseur, Integer> colFournisseur;

    @FXML
    private TableColumn<PerformanceFournisseur, String> colScore;

    @FXML
    private TableColumn<PerformanceFournisseur, Integer> colCmd;

    @FXML
    private TableColumn<PerformanceFournisseur, Double> colRetard;

    private ObservableList<PerformanceFournisseur> perfList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colFournisseur.setCellValueFactory(new PropertyValueFactory<>("idFournisseur"));
        colScore.setCellValueFactory(new PropertyValueFactory<>("nomFournisseur"));
        colCmd.setCellValueFactory(new PropertyValueFactory<>("nbrCommandes"));
        colRetard.setCellValueFactory(new PropertyValueFactory<>("retardMoyen"));

        perfList=loadPerformance();
        perfTable.setItems(perfList);
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
