package MainApplicationUI;


import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.prefs.Preferences;

public class UserSelectCityController {

    public UserSelectCityController(){

    }

    public void clickPloiesti(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("oras","Ploiesti");
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }

    public void clickBucharest(ActionEvent event) throws IOException {

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("oras","Bucuresti");

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }
}
