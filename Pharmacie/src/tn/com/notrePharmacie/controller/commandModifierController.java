package tn.com.notrePharmacie.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class commandModifierController {
    private Scene scene  ;
    private Stage stage;
    @FXML
    private Button annulerBtn;

    @FXML
    private TextField clientField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField idField;

    @FXML
    private Button sauvegarderBtn;
    public void sauvgarder(ActionEvent event ) {
    	// requete  sql where idcommand 
    }
    @FXML
    public void annuler(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/tn/com/notrePharmacie/view/cmnd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}