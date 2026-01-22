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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import tn.com.notrePharmacie.model.LigneCommande;

public class commandAjouterController {
    private Scene scene ;
    private Stage stage ;
    @FXML
    private TableView<LigneCommande> tableLignes;

    @FXML
    private TableColumn<LigneCommande, Integer> colIdProduit;

    @FXML
    private TableColumn<LigneCommande, Integer> colQuantite;

    @FXML
    private TableColumn<LigneCommande, Double> colPrixUnitaire;
    @FXML
    private DatePicker datePrevuPicker;

    @FXML
    private TextField idFournisseurField;

    private ObservableList<LigneCommande> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tableLignes.setEditable(true);

        colIdProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colPrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        colIdProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQuantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPrixUnitaire.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        colIdProduit.setOnEditCommit(e ->
                e.getRowValue().setIdProduit(e.getNewValue())
        );

        colQuantite.setOnEditCommit(e ->
                e.getRowValue().setQuantite(e.getNewValue())
        );

        colPrixUnitaire.setOnEditCommit(e ->
                e.getRowValue().setPrixUnitaire(e.getNewValue())
        );

        tableLignes.setItems(list);
    }

    @FXML
    void addLigne(ActionEvent event) {
        list.add(new LigneCommande(0, 0, 0.0)); // ligne vide a remplir
    }

    @FXML
    void deleteLigne(ActionEvent event) {
        LigneCommande selected = tableLignes.getSelectionModel().getSelectedItem();
        if (selected != null) {
            list.remove(selected);
        }
    }
    @FXML
    void enregistrer(ActionEvent event ) {
    	// requete sql t3mrli vente commande 
    	LocalDate date=datePrevuPicker.getValue();
    	int fourn=Integer.parseInt(idFournisseurField.getText());
        ObservableList<LigneCommande> lignes = tableLignes.getItems();
       /* int idcommande=//SELECT MAX(id) AS lastId FROM fournisseur;

               String sql = "INSERT INTO ligne_commande(idCommande, idProduit, quantite, prixUnitaire) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {*/

            int idCommande = 12; // ou récupéré avant

            for (LigneCommande ligne : lignes) {
              /*  ps.setInt(1, idCommande);
                ps.setInt(2, ligne.getIdProduit());
                ps.setInt(3, ligne.getQuantite());
                ps.setDouble(4, ligne.getPrixUnitaire());

                ps.executeUpdate();*/ //d5lhom fi parametre l fonction sql 
            }
    }
    @FXML 
    void annuler(ActionEvent event ) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/cmnd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
