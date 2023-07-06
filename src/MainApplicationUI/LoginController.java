package MainApplicationUI;

import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class LoginController {

    public String loggedUsername;
    public String loggedPassword;

    public LoginController(){
    }

    @FXML
    private Button loginButton;
    @FXML
    private Label wrongPasswordIntroduced;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void login(ActionEvent event) throws SQLException, IOException {

        SQLConnect SQL = new SQLConnect();
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();

        String tempUser = username.getText();
        if(SQL.Login(username.getText(), password.getText()) && tempUser.equals("temporary") == false)
        {
            System.out.println(SQL.findUserType(tempUser));

            Preferences userPreferences = Preferences.userRoot();
            userPreferences.put("username", tempUser);

            //initialize aici date applicatie

            if( SQL.findUserType(tempUser) == 0)
            {
                System.out.println("Logged as User");
                m.changeScene("userSelectCity.fxml");
            }
            else {
                if( SQL.findUserType(tempUser) == 1)
                {
                    System.out.println("Logged as Livrator");
                }
                else {
                    if(SQL.findUserType(tempUser) == 2)
                    {
                        System.out.println("Logged as Restaurant Manager");
                    }
                    else {
                        System.out.println("Logged as Admin");
                    }
                }
            }

           // m.changeScene("selectActualCity.fxml");
        }
        else {
            System.out.println("Parola incorecta");
            wrongPasswordIntroduced.setVisible(true);
        }
    }

    public void goToRegister(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("register.fxml");
    }




}
