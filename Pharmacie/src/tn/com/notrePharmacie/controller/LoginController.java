package tn.com.notrePharmacie.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.com.notrePharmacie.model.User;
import tn.com.notrePharmacie.dao.UserDao;
class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = UserDAO.login(username, password);

        if (user == null) {
            errorLabel.setVisible(true);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/com/notrePharmacie/view/Dashboard.fxml"));
            Parent root = loader.load();
            DashboardAdminController controller = loader.getController();
            controller.setUserRole(user.getRole());
            controller.updateUI();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
