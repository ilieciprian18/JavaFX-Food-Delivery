package MainApplicationUI;

import Classes.Comanda;
import Services.SQLConnect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class MainFoodDeliveryApplication extends Application {

private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stg = primaryStage;
        primaryStage.setResizable(false);



        Parent root = FXMLLoader.load(getClass().getResource("../MainApplicationUI/login.fxml"));
        primaryStage.setScene(new Scene(root, 850, 725));
        primaryStage.setTitle("Food Delivery Application");
        primaryStage.getIcons().add(new Image("MainApplicationUI/Images/icon.png"));
        primaryStage.show();



    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
