package tn.com.notrePharmacie.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface DashboardNavigator {

    default void goBackToDashboard(ActionEvent event, String userRole) throws IOException {
        String fxmlPath;

        if ("admin".equalsIgnoreCase(userRole)) {
            fxmlPath = "/tn/com/notrePharmacie/view/DashboradAdmin.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            fxmlPath = "/tn/com/notrePharmacie/view/DashboardUser.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Passer le rôle pour cacher boutons
            DashboardUserController controller = loader.getController();
            controller.setUserRole(userRole);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
