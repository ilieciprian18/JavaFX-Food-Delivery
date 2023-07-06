package MainApplicationUI;

import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {

    @FXML
    private TextField username;
    @FXML
    private Label wrongUsername;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongPassword;
    @FXML
    private Label wrongName;
    @FXML
    private TextField name;
    @FXML
    private Label wrongPrenume;
    @FXML
    private TextField prenume;
    @FXML
    private Label wrongEmail;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Label wrongPhoneNumber;
    @FXML
    private ChoiceBox<String> chooseSex;
    @FXML
    private Label wrongSelected;
    private String[] select = {"male", "female"};
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private Label wrongDate;

    private String errorMessageRegister;

    public void RegisterController(){

    }

    public void initialize(){

        chooseSex.getItems().addAll(select);

    }

    public void toLogin(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("login.fxml");
    }

    public void signUp(ActionEvent event) throws SQLException {

        SQLConnect SQL = new SQLConnect();
        boolean okusername = false;
        boolean okPassword = false;
        boolean okName = false;
        boolean okPrenume = false;
        boolean okEmail = false;
        boolean okPhoneNumber = false;
        boolean okSelect = false;
        boolean okDate = false;

        String allertMessage = "";

        String tempUsername = username.getText();

        if (tempUsername.length() >= 1 && tempUsername.contains(" ") == false && SQL.searchUsername(tempUsername) == true) {
            System.out.println("da");
            wrongUsername.setVisible(false);
            okusername = true;
        } else {
            if (tempUsername.length() < 1) {
                //wrongUsername.setText("Required*");
                //wrongUsername.setVisible(true);
                allertMessage = allertMessage + "Please enter a username (required)\n";
            }

            if (tempUsername.contains(" ") == true) {
                //wrongUsername.setText("No spaced allowed");
               // wrongUsername.setVisible(true);
                allertMessage = allertMessage + "No spaces allowed in username \n";
            }

            if (SQL.searchUsername(tempUsername) == false) {
                //wrongUsername.setText("Username already taken!");
                //wrongUsername.setVisible(true);
                allertMessage = allertMessage + "This username is already taken \n";

            }

        }

        String tempPassword = password.getText();
        okPassword = checkPassword(tempPassword);
        //System.out.println(okPassword);
        if (okPassword == false) {
            //wrongPassword.setVisible(true);
            allertMessage = allertMessage + errorMessageRegister ;

        } else {
            //wrongPassword.setVisible(false);

        }

        String tempName = name.getText();
        if (tempName.length() >= 1) {
            okName = true;
            //wrongName.setVisible(false);
        }

        if (okName == false) {
            //wrongName.setVisible(true);
            allertMessage = allertMessage + "Please enter a name (required)\n";
        }

        String tempPrenume = prenume.getText();
        if (tempPrenume.length() >= 1) {
            okPrenume = true;
            //wrongPrenume.setVisible(false);
        }

        if (okPrenume == false) {
            //wrongPrenume.setVisible(true);
            allertMessage = allertMessage + "Please enter a prenume (required)\n";
        }

        String tempEmail = email.getText();
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(tempEmail);
        System.out.println(matcher.matches());
        if (matcher.matches() == true) {
            //wrongEmail.setVisible(false);
            okEmail = true;

        }

        if (okEmail == false) {
            //wrongEmail.setVisible(true);
           // wrongEmail.setText("email not valid");
            allertMessage = allertMessage + "Please enter a valid email (required)\n";
        }

        String tempPhoneNumber = phoneNumber.getText();
        pattern = Pattern.compile("^\\d{10}$");
        matcher = pattern.matcher(tempPhoneNumber);
        if (matcher.matches() == true) {
           // wrongPhoneNumber.setVisible(false);
            okPhoneNumber = true;

        }

        if (okPhoneNumber == false) {
            //wrongPhoneNumber.setVisible(true);
            //wrongPhoneNumber.setText("not valid");
            allertMessage = allertMessage + "Please enter a valid phone number (required)\n";
        }

        String x = "";
        String tempGender = chooseSex.getValue();
        if (tempGender != null) {

            if (tempGender.equals("female")) {
                x = "female";
            } else {
                x = "male";
            }
            okSelect = true;
           // wrongSelected.setVisible(false);
        }

        if (okSelect == false) {
            //wrongSelected.setVisible(true);
            allertMessage = allertMessage + "Please enter a gender (required)\n";
        }

        int tempDay = 0;
        int tempMonth = 0;
        int tempYear = 0;

        if (dateOfBirth.getValue() != null) {
            okDate = true;
            wrongDate.setVisible(false);

            tempDay = dateOfBirth.getValue().getDayOfMonth();
            tempMonth = dateOfBirth.getValue().getMonthValue();
            tempYear = dateOfBirth.getValue().getYear();

        }

        if(okDate == false)
        {
            //wrongDate.setVisible(true);
            allertMessage = allertMessage + "Please enter a birth date (required)\n";
        }

        if( okusername && okName && okPrenume && okPassword && okEmail && okPhoneNumber && okSelect && okDate)
        {
            //select

            SQL.insertUserInDatabase(tempUsername,tempPassword,tempName,tempPrenume,tempEmail,tempPhoneNumber,0,tempDay,tempMonth,tempYear,x);
            SQL.insterAuditLog("A new user was registered! " + "(" + tempUsername + ")");

            username.clear();
            password.clear();
            name.clear();
            prenume.clear();
            email.clear();
            phoneNumber.clear();
            chooseSex.valueProperty().set(null);
            dateOfBirth.valueProperty().set(null);

        }

        else {

            Alert allertInfo = new Alert(Alert.AlertType.INFORMATION);
            allertInfo.setTitle("Register notification!");
            allertInfo.setHeaderText(allertMessage);
            allertInfo.show();
        }
    }


    private boolean checkPassword(String password){

        boolean isValid = true;
        String error = "Minim password requirements not met: \n";
        if(password.length() < 8)
        {
            isValid = false;
            error = error + "8 caractere (!) \n";
        }

        String uppercase = "(.*[A-Z].*)";
        if(!password.matches(uppercase)){
            isValid = false;
            error = error + " o litera mare (!) \n";
        }

        String numbers = "(.*[1-9].*)";
        if(!password.matches(numbers))
        {
            isValid = false;
            error = error + " o cifra (!) \n";
        }

        this.errorMessageRegister = error;

        System.out.println();
        wrongPassword.setText(error);
        return isValid;

    }
}
