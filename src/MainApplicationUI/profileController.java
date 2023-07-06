package MainApplicationUI;

import Classes.User;
import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class profileController {


    public void profileController()
    {

    }

    @FXML
    private AnchorPane profilePane;



    public void initialize() throws SQLException {

        Preferences userPreferences = Preferences.userRoot();
        String selectedUsername = userPreferences.get("username","none");

        SQLConnect SQL = new SQLConnect();

        User selectedUserInfo = SQL.getDetailsUser(selectedUsername);



        Label usernameUser = new Label();
        usernameUser.setText(selectedUsername);
        usernameUser.setFont(Font.font("Constantia", 20));
        usernameUser.setPrefWidth(200);
        usernameUser.setAlignment(Pos.CENTER_RIGHT);
        usernameUser.setLayoutX(485);
        usernameUser.setLayoutY(57);
        profilePane.getChildren().add(usernameUser);

        Label prenumeUser = new Label();
        prenumeUser.setText(selectedUserInfo.getPrenume());
        prenumeUser.setFont(Font.font("Constantia", 20));
        prenumeUser.setPrefWidth(200);
        prenumeUser.setAlignment(Pos.CENTER_RIGHT);
        prenumeUser.setLayoutX(485);
        prenumeUser.setLayoutY(86);
        profilePane.getChildren().add(prenumeUser);

        Label numeUser = new Label();
        numeUser.setText(selectedUserInfo.getNume());
        numeUser.setFont(Font.font("Constantia", 20));
        numeUser.setPrefWidth(200);
        numeUser.setAlignment(Pos.CENTER_RIGHT);
        numeUser.setLayoutX(485);
        numeUser.setLayoutY(115);
        profilePane.getChildren().add(numeUser);

        Label emailUser = new Label();
        emailUser.setText(selectedUserInfo.getEmail());
        emailUser.setFont(Font.font("Constantia", 20));
        emailUser.setPrefWidth(200);
        emailUser.setAlignment(Pos.CENTER_RIGHT);
        emailUser.setLayoutX(485);
        emailUser.setLayoutY(144);
        profilePane.getChildren().add(emailUser);

        Label phoneNumberUser = new Label();
        phoneNumberUser.setText(selectedUserInfo.getTelefon());
        phoneNumberUser.setFont(Font.font("Constantia", 20));
        phoneNumberUser.setPrefWidth(200);
        phoneNumberUser.setAlignment(Pos.CENTER_RIGHT);
        phoneNumberUser.setLayoutX(485);
        phoneNumberUser.setLayoutY(173);
        profilePane.getChildren().add(phoneNumberUser);

        Label genderUser = new Label();
        genderUser.setText(selectedUserInfo.getGender());
        genderUser.setFont(Font.font("Constantia", 20));
        genderUser.setPrefWidth(200);
        genderUser.setAlignment(Pos.CENTER_RIGHT);
        genderUser.setLayoutX(485);
        genderUser.setLayoutY(202);
        profilePane.getChildren().add(genderUser);

        String dateText = "";
        if(selectedUserInfo.getBirthDay() < 10)
        {
            dateText = dateText + "0" + selectedUserInfo.getBirthDay() + "/";
        }
        else {
            dateText = dateText + selectedUserInfo.getBirthDay() + "/";
        }

        if(selectedUserInfo.getBirthMonth() < 10)
        {
            dateText = dateText + "0"+ selectedUserInfo.getBirthMonth() + "/";
        } else {
            dateText = dateText + selectedUserInfo.getBirthMonth() + "/";
        }

        dateText = dateText + selectedUserInfo.getBirthYear();

        Label birthShowUser = new Label();
        birthShowUser.setText(dateText);
        birthShowUser.setFont(Font.font("Constantia", 20));
        birthShowUser.setPrefWidth(200);
        birthShowUser.setAlignment(Pos.CENTER_RIGHT);
        birthShowUser.setLayoutX(485);
        birthShowUser.setLayoutY(231);
        profilePane.getChildren().add(birthShowUser);





    }

    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }

    public void selectCity(ActionEvent event) throws IOException {


        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("oras","" );

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userSelectCity.fxml");

    }

    public void logOut(ActionEvent event) throws IOException {
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("username","" );

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("login.fxml");
    }
}
