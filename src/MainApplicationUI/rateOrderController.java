package MainApplicationUI;

import Classes.Comanda;
import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.Rating;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class rateOrderController {



    @FXML
    private Rating ratingLivrator;

    @FXML
    private Rating ratingRestaurant;


    @FXML
    private TextArea reviewRestaurant;



    public void rateOrderController(){

    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }

    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }

    public void sendReview(ActionEvent event) throws SQLException, IOException {

        Preferences userPreferences = Preferences.userRoot();
        String selectedOrderIdTemp = userPreferences.get("idRateOrder","none");
        String selectedUsername = userPreferences.get("username","none");

        SQLConnect SQL = new SQLConnect();

        int selectedOrderId = Integer.parseInt(selectedOrderIdTemp);
        Comanda selectedOrder = SQL.getDetailsOrder(selectedOrderId);
        int idRestaurant = selectedOrder.getIdRestaurant();


        String reviewText = reviewRestaurant.getText();
        String checkOkReview = reviewText.replace(" ", "");

        double ratingRastaurantValue = ratingRestaurant.getRating();
        int intValueRating = (int) ratingRastaurantValue;

        Float ratingRestaurantDatabase = SQL.loadRestaurantInfo(idRestaurant).getRatingRestaurant();
        int numberReviewRestaurant = SQL.getNumberReviews(idRestaurant);

        SQL.insertReviewDatabase(idRestaurant,intValueRating,selectedUsername,reviewText);

        SQL.updateReviewRestaurant(intValueRating,ratingRestaurantDatabase,numberReviewRestaurant,idRestaurant);




        SQL.updateOrderRated(selectedOrderId);






        //update rating

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");



    }


    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }



}
