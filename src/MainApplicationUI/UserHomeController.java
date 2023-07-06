package MainApplicationUI;


import Services.SQLConnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.text.Style;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class UserHomeController {


    @FXML
    private AnchorPane spaceShowPane;

    public UserHomeController(){

    }

    public void initialize() throws SQLException {


        Preferences userPreferences = Preferences.userRoot();
        String selectedCity =userPreferences.get("oras","none");
        SQLConnect SQL = new SQLConnect();

        //nume_restaurant, rating, adress
        Vector<Vector<String>> tempRestaurants = new Vector<>();
        tempRestaurants = SQL.getRestauransCity(selectedCity);

        int paneColor = 0;
        int windowDown = 0;


        for(int i = 0; i < tempRestaurants.size();i++)
        {
            //generam dinamic interfata grafica
            Pane restaurantTab = new Pane();
            if( paneColor %2 == 0)
            {
                restaurantTab.setStyle("-fx-background-color:  #D3D3D3");
            }
            else {
                restaurantTab.setStyle("-fx-background-color:   #E8E8E8");
            }
            paneColor++;

            restaurantTab.setPrefWidth(850);
            restaurantTab.setPrefHeight(117);
            restaurantTab.setLayoutY(windowDown);
            spaceShowPane.getChildren().add(restaurantTab);

            ImageView tempImage = new ImageView();
            String numeImagine = "MainApplicationUI/Images/"+ selectedCity + tempRestaurants.elementAt(i).elementAt(0) + ".png";
            System.out.println(numeImagine);
            tempImage.setImage(new Image(numeImagine));
            tempImage.setFitWidth(135);
            tempImage.setFitHeight(117);
            tempImage.setLayoutX(0);
            tempImage.setLayoutY(windowDown);
            spaceShowPane.getChildren().add(tempImage);

            Label tempRestaurantName = new Label();
            tempRestaurantName.setText(tempRestaurants.elementAt(i).elementAt(0));
            tempRestaurantName.setFont(new Font("Segoe UI Black", 50));
            tempRestaurantName.setLayoutX(176);
            tempRestaurantName.setLayoutY(22 + windowDown);
            spaceShowPane.getChildren().add(tempRestaurantName);

            //pun in user preferences id rest selectat

            String IDRestaurantButton = tempRestaurants.elementAt(i).elementAt(3);

            Button tempOrder = new Button(IDRestaurantButton);
            tempOrder.setId(IDRestaurantButton);
            tempOrder.setText("ORDER");
            tempOrder.setPrefWidth(94);
            tempOrder.setPrefHeight(39);
            tempOrder.setLayoutX(620);
            tempOrder.setLayoutY(43 + windowDown);
            tempOrder.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
            tempOrder.setTextFill(Paint.valueOf("white"));
            tempOrder.setFont(new Font("System",16));
            tempOrder.setStyle("-fx-font-weight: bold");
            tempOrder.setOnAction(this::toRestaurant);
            // add functie to restaurant
            spaceShowPane.getChildren().add(tempOrder);

            Label tempRating = new Label();
            float ratingValue = Float.valueOf(tempRestaurants.elementAt(i).elementAt(1));
                tempRating.setText(tempRestaurants.elementAt(i).elementAt(1));
            //tempRating.setText(tempRestaurants.elementAt(i).elementAt(1));
            tempRating.setFont(new Font("System", 23));
            tempRating.setLayoutX(763);
            tempRating.setLayoutY(45 + windowDown);
            spaceShowPane.getChildren().add(tempRating);

            FontAwesomeIcon iconStar = new FontAwesomeIcon();
            iconStar.setGlyphName("STAR");
            iconStar.setSize("2em");
            iconStar.setFill(Paint.valueOf("#000000"));
            iconStar.setLayoutX(802);
            iconStar.setLayoutY(71 + windowDown);
            spaceShowPane.getChildren().add(iconStar);

            windowDown = windowDown + 117;

        }





    }

    public void toRestaurant(ActionEvent event) {

        int itemButtonId = Integer.parseInt(((Button)event.getSource()).getId());
        String itemButtonIdString = Integer.toString(itemButtonId);
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("idRestaurant",itemButtonIdString );

        System.out.println(itemButtonIdString);
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        try {
            m.changeScene("userRestaurantOrder.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }

    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }







}
