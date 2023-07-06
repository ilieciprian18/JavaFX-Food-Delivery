package MainApplicationUI;

import Classes.Produs;
import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class UserRestaurantOrder {

    @FXML
    private AnchorPane spaceShowItemsPane;

    private int[] updateInterfaceProducts;
    private Vector<Produs> orderUser;

    private Vector<Produs> restaurantMenuGlobal;

    DataSingleton data = DataSingleton.getInstance();

    public void UserRestaurantOrder(){

    }


    public void initialize() throws SQLException {

        Preferences userPreferences = Preferences.userRoot();
        String IdRestaurant = userPreferences.get("idRestaurant","none");
        String selectedUsername = userPreferences.get("username","none");
        SQLConnect SQL = new SQLConnect();

        this.updateInterfaceProducts = new int[20];
        this.orderUser = new Vector<>();

        Vector<String> selectedRestaurantInfo = new Vector<>();
        selectedRestaurantInfo = SQL.getRestaurantInfo(IdRestaurant);


        //1[nume_restaurant]Cover
        ImageView tempImage = new ImageView();
        String numeImagine = "MainApplicationUI/Images/"+ IdRestaurant + selectedRestaurantInfo.elementAt(0) + "Cover" + ".png";
        System.out.println(numeImagine);
        tempImage.setImage(new Image(numeImagine));
        tempImage.setFitWidth(850);
        tempImage.setFitHeight(187);
        tempImage.setLayoutX(0);
        tempImage.setLayoutY(0);
        spaceShowItemsPane.getChildren().add(tempImage);

        Label restaurantNameShow = new Label();
        if( selectedRestaurantInfo.elementAt(0).equals("KFC"))
        {
            restaurantNameShow.setText("Kentucky Fried Chicken");
        }
        else {
            restaurantNameShow.setText(selectedRestaurantInfo.elementAt(0));
        }

        restaurantNameShow.setAlignment(Pos.TOP_CENTER);
        restaurantNameShow.setFont(new Font("Segoe UI Black", 40));
        restaurantNameShow.setPrefWidth(484);
        restaurantNameShow.setPrefHeight(58);
        restaurantNameShow.setLayoutX(195);
        restaurantNameShow.setLayoutY(43);
        restaurantNameShow.setTextFill(Paint.valueOf("#ffffff"));
        restaurantNameShow.getStylesheets().add(getClass().getResource("Images/textStyle.css").toExternalForm());
        spaceShowItemsPane.getChildren().add(restaurantNameShow);

        Label restaurantAdressShow = new Label();
        restaurantAdressShow.setText(selectedRestaurantInfo.elementAt(3));
        restaurantAdressShow.setAlignment(Pos.TOP_CENTER);
        restaurantAdressShow.setFont(new Font("System", 20));
        restaurantAdressShow.setStyle("-fx-font-weight: bold");
        restaurantAdressShow.setPrefWidth(257);
        restaurantAdressShow.setPrefHeight(30);
        restaurantAdressShow.setLayoutX(287);
        restaurantAdressShow.setLayoutY(94);
        restaurantAdressShow.setTextFill(Paint.valueOf("#ffffff"));
        restaurantAdressShow.getStylesheets().add(getClass().getResource("Images/textStyle.css").toExternalForm());
        spaceShowItemsPane.getChildren().add(restaurantAdressShow);

        Button reviewsButton = new Button();
        reviewsButton.setText("Reviews");
        reviewsButton.setPrefWidth(61);
        reviewsButton.setPrefHeight(27);
        reviewsButton.setLayoutX(385);
        reviewsButton.setLayoutY(124);
        reviewsButton.getStylesheets().add(getClass().getResource("Images/buttonStyle.css").toExternalForm());
        reviewsButton.setTextFill(Paint.valueOf("white"));
        reviewsButton.setFont(new Font("System",12));
        reviewsButton.setOnAction(this::goToReviews);
        spaceShowItemsPane.getChildren().add(reviewsButton);


        System.out.println(selectedRestaurantInfo);
        System.out.println(IdRestaurant);

        Vector<Produs> meniuRestaurantSelected = SQL.loadMenuRestaurant(Integer.parseInt(IdRestaurant));
        this.restaurantMenuGlobal = meniuRestaurantSelected;
        //merge pe un i++ cand afisam in database
        System.out.println(meniuRestaurantSelected);

        int color = 0;
        int windowPlace = 187;

        for(int i = 0; i < meniuRestaurantSelected.size();i++)
        {
            //afisam meniul
            Pane showPane = new Pane();

            if ( color % 2 == 0)
            {
                showPane.setStyle("-fx-background-color:#D3D3D3");
            }
            else {

                showPane.setStyle("-fx-background-color:#E8E8E8");

            }
            color ++;

            showPane.setPrefHeight(96);
            showPane.setPrefWidth(850);
            showPane.setLayoutY(windowPlace);



            ImageView temporaryImage = new ImageView();
            String numeImagineNew = "MainApplicationUI/Images/"+ IdRestaurant + meniuRestaurantSelected.elementAt(i).getNumeProdus() + ".png";
            System.out.println(numeImagineNew);
            temporaryImage.setImage(new Image(numeImagineNew));
            temporaryImage.setFitWidth(187);
            temporaryImage.setFitHeight(96);
            temporaryImage.setLayoutX(0);
            temporaryImage.setLayoutY(0);
            showPane.getChildren().add(temporaryImage);

            Label tempProductName = new Label();
            System.out.println(meniuRestaurantSelected.elementAt(i).getNumeProdus());
            tempProductName.setText(meniuRestaurantSelected.elementAt(i).getNumeProdus());
            tempProductName.setFont(new Font("Sylfaen", 25));
            tempProductName.setLayoutX(222);
            tempProductName.setLayoutY(16);
            showPane.getChildren().add(tempProductName);

            Label tempProductPrice = new Label();
            tempProductPrice.setText(String.valueOf(meniuRestaurantSelected.elementAt(i).getPretProdus()));
            tempProductPrice.setFont(new Font("Sylfaen", 28));
            tempProductPrice.setLayoutX(525);
            tempProductPrice.setLayoutY(31);
            showPane.getChildren().add(tempProductPrice);

            // e cu 1 mai mic i
            String idProdusButon = String.valueOf(i);

            Button tempAddOrder = new Button(idProdusButon);
            tempAddOrder.setId(idProdusButon);
            tempAddOrder.setText("Add");
            tempAddOrder.setPrefWidth(94);
            tempAddOrder.setPrefHeight(39);
            tempAddOrder.setLayoutY(29);
            tempAddOrder.setLayoutX(677);
            tempAddOrder.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
            tempAddOrder.setTextFill(Paint.valueOf("white"));
            tempAddOrder.setFont(new Font("System",16));
            tempAddOrder.setStyle("-fx-font-weight: bold");
            tempAddOrder.setOnAction(this::addProdus);
            showPane.getChildren().add(tempAddOrder);

            Label tempNumarProduse = new Label();
            tempNumarProduse.setId("i" + idProdusButon);
            tempNumarProduse.setText("(0)");
            tempNumarProduse.setFont(new Font("System", 12));
            tempNumarProduse.setLayoutX(781);
            tempNumarProduse.setLayoutY(40);
            showPane.getChildren().add(tempNumarProduse);


            String check = meniuRestaurantSelected.elementAt(i).getIngredienteProdus();
            //System.out.println(check.length() > 40);



            if(check.length() > 40)
            {

                String check1 = check.substring(0,40);
                String check2 = check.substring(40);
                if( check2.charAt(0) == ' ')
                {
                    check2 = check2.substring(1);
                }

                Label tempIngrediente = new Label();
                tempIngrediente.setText(check1);
                tempIngrediente.setFont(new Font("System", 12));
                tempIngrediente.setLayoutX(222);
                tempIngrediente.setLayoutY(49);
                tempIngrediente.setPrefWidth(230);
                tempIngrediente.setPrefHeight(17);
                tempIngrediente.setTextAlignment(TextAlignment.RIGHT);
                showPane.getChildren().add(tempIngrediente);

                Label tempIngrediente2 = new Label();
                tempIngrediente2.setText(check2);
                tempIngrediente2.setFont(new Font("System", 12));
                tempIngrediente2.setLayoutX(222);
                tempIngrediente2.setLayoutY(60);
                tempIngrediente2.setPrefWidth(230);
                tempIngrediente2.setPrefHeight(17);
                tempIngrediente2.setTextAlignment(TextAlignment.RIGHT);
                showPane.getChildren().add(tempIngrediente2);
            }
            else {

                Label tempIngrediente = new Label();
                tempIngrediente.setText(check);
                tempIngrediente.setFont(new Font("System", 12));
                tempIngrediente.setLayoutX(222);
                tempIngrediente.setLayoutY(49);
                tempIngrediente.setPrefWidth(200);
                tempIngrediente.setPrefHeight(17);
                tempIngrediente.setTextAlignment(TextAlignment.RIGHT);
                showPane.getChildren().add(tempIngrediente);


            }








            spaceShowItemsPane.getChildren().add(showPane);
            windowPlace = windowPlace + 96;

        }

        for(int i = 0; i <= meniuRestaurantSelected.size();i++)
        {
                updateInterfaceProducts[i] = 0;
        }





    }

    public void goToReviews(ActionEvent event){

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        try {
            m.changeScene("reviewPage.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }

    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }

    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }


    public void addProdus(ActionEvent event){

        int itemButtonId = Integer.parseInt(((Button)event.getSource()).getId());
        orderUser.add(restaurantMenuGlobal.elementAt(itemButtonId));

        updateInterfaceProducts[itemButtonId]++;

        Label lookForLabel = (Label) spaceShowItemsPane.lookup("#i" + itemButtonId);

        String pastText = lookForLabel.getText();
        pastText = pastText.replace("(","");
        pastText = pastText.replace(")","");
        pastText = pastText.replace("i","");
        System.out.println(pastText);
        String newText = Integer.toString(Integer.parseInt(pastText) + 1);
        System.out.println(newText);

        lookForLabel.setText("(" + newText + ")");

    }

    public void goToCheckout(ActionEvent event) throws IOException {


        data.sendComanda(orderUser);
        data.setNumarProduseTemp(updateInterfaceProducts);




        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userCheckout.fxml");

    }

}
