package MainApplicationUI;

import Classes.Comanda;
import Classes.Restaurant;
import Services.SQLConnect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class UserOrderHistoryController {

    public void UserOrderHistory(){

    }


    @FXML
    private Label isFirstOrder;

    @FXML
    private AnchorPane showHistoryPanel;

    Vector<String> ordersStatusNotifications;


    public void initialize() throws SQLException {


        Preferences userPreferences = Preferences.userRoot();
        String selectedUsername = userPreferences.get("username","none");

        this.ordersStatusNotifications =new Vector<>();


        SQLConnect SQL = new SQLConnect();

        boolean maybeNoOrder = SQL.historyOrder(selectedUsername);

        if( maybeNoOrder == true)
        {
            isFirstOrder.setVisible(true);
        }
        else {

           // System.out.println(SQL.numberOrderClient(selectedUsername));
            int numberOrdersTotal = SQL.numberOrderClient(selectedUsername);
            Vector<Comanda> userOrder = SQL.getOrderUser(selectedUsername);




            int paneColor = 0;
            int layoutY = 0;
            for(int i = 0; i < userOrder.size(); i++)
            {
                Restaurant currentRestaurant = SQL.loadRestaurantInfo(userOrder.elementAt(i).getIdRestaurant());

                ordersStatusNotifications.add(userOrder.elementAt(i).getOrderStatus());

                Pane historyTab = new Pane();
                if( paneColor %2 == 0)
                {
                    historyTab.setStyle("-fx-background-color:  #D3D3D3");
                }
                else {
                    historyTab.setStyle("-fx-background-color:   #E8E8E8");
                }
                paneColor++;

                historyTab.setPrefWidth(850);
                historyTab.setPrefHeight(107);

                historyTab.setLayoutY(layoutY);
                showHistoryPanel.getChildren().add(historyTab);


                ImageView tempImage = new ImageView();
                String numeImagine = "MainApplicationUI/Images/"+ currentRestaurant.getOrasRestaurant() + currentRestaurant.getNumeRestaurant() + ".png";
                //System.out.println(numeImagine);
                tempImage.setImage(new Image(numeImagine));
                tempImage.setFitWidth(135);
                tempImage.setFitHeight(108);
                tempImage.setLayoutX(0);
                tempImage.setLayoutY(0);
                tempImage.setPreserveRatio(true);
                historyTab.getChildren().add(tempImage);

                Label tempRestaurantName = new Label();
                tempRestaurantName.setText(currentRestaurant.getNumeRestaurant());
                tempRestaurantName.setFont(new Font("Sylfaen", 24));
                tempRestaurantName.setPrefWidth(125);
                tempRestaurantName.setAlignment(Pos.CENTER);

                tempRestaurantName.setLayoutX(125);
                tempRestaurantName.setLayoutY(24);
                historyTab.getChildren().add(tempRestaurantName);


                Label tempRestaurantAdress = new Label();
                tempRestaurantAdress.setText(currentRestaurant.getAdressRestaurant());
                tempRestaurantAdress.setFont(Font.font("System", FontPosture.ITALIC, 12));
                tempRestaurantAdress.setPrefWidth(150);
                tempRestaurantAdress.setAlignment(Pos.CENTER);
                tempRestaurantAdress.setLayoutX(113);
                tempRestaurantAdress.setLayoutY(54);
                historyTab.getChildren().add(tempRestaurantAdress);


                Label tempRestaurantOras = new Label();
                tempRestaurantOras.setText(currentRestaurant.getOrasRestaurant());
                tempRestaurantOras.setFont(Font.font("System", FontPosture.ITALIC, 12));
                tempRestaurantOras.setLayoutX(164);
                tempRestaurantOras.setLayoutY(72);
                historyTab.getChildren().add(tempRestaurantOras);

                Label tempDeliveredText = new Label();
                tempDeliveredText.setText("Delivered By");
                tempDeliveredText.setFont(Font.font("Sylfaen", 20));
                tempDeliveredText.setLayoutX(286);
                tempDeliveredText.setLayoutY(26);
                historyTab.getChildren().add(tempDeliveredText);

                Label tempLivratorUsername = new Label();
                tempLivratorUsername.setText(userOrder.elementAt(i).getUsernameLivrator());
                tempLivratorUsername.setFont(Font.font("Corbel", FontWeight.BOLD,FontPosture.ITALIC, 15));
                tempLivratorUsername.setPrefWidth(150);
                tempLivratorUsername.setAlignment(Pos.CENTER);
                tempLivratorUsername.setLayoutX(265);
                tempLivratorUsername.setLayoutY(53);
                historyTab.getChildren().add(tempLivratorUsername);

                Label tempDeliveryDate = new Label();
                String dateText = "";
                if(userOrder.elementAt(i).getOrderDay() < 10)
                {
                    dateText = dateText + "0" + userOrder.elementAt(i).getOrderDay() + "/";
                }
                else {
                    dateText = dateText + userOrder.elementAt(i).getOrderDay() + "/";
                }

                if(userOrder.elementAt(i).getOrderMonth() < 10)
                {
                    dateText = dateText + "0"+ userOrder.elementAt(i).getOrderMonth() + "/";
                } else {
                    dateText = dateText + userOrder.elementAt(i).getOrderMonth() + "/";
                }

                dateText = dateText + userOrder.elementAt(i).getOrderYear();

                tempDeliveryDate.setText(dateText);
                tempDeliveryDate.setFont(Font.font("Corbel", FontWeight.BOLD,FontPosture.ITALIC, 15));
                tempDeliveryDate.setLayoutX(306);
                tempDeliveryDate.setLayoutY(71);
                historyTab.getChildren().add(tempDeliveryDate);

                Label tempTotalText = new Label();
                tempTotalText.setText("Total");
                tempTotalText.setFont(Font.font("Sylfaen", 20));
                tempTotalText.setLayoutX(442);
                tempTotalText.setLayoutY(26);
                historyTab.getChildren().add(tempTotalText);

                Label tempSum = new Label();
                tempSum.setText(userOrder.elementAt(i).getSuma() + " lei");
                tempSum.setFont(Font.font("Corbel", FontWeight.BOLD,FontPosture.ITALIC, 20));
                tempSum.setPrefWidth(100);
                tempSum.setPrefHeight(23);
                tempSum.setLayoutX(414);
                tempSum.setLayoutY(57);
                tempSum.setTextAlignment(TextAlignment.CENTER);
                tempSum.setAlignment(Pos.CENTER);
                historyTab.getChildren().add(tempSum);

                Label tempOrderStatus = new Label();
                tempOrderStatus.setText("Order Status");
                tempOrderStatus.setFont(Font.font("Sylfaen", 20));
                tempOrderStatus.setLayoutX(550);
                tempOrderStatus.setLayoutY(23);
                historyTab.getChildren().add(tempOrderStatus);

                Label tempOrderCheck = new Label();
                tempOrderCheck.setText(userOrder.elementAt(i).getOrderStatus());
                tempOrderCheck.setFont(Font.font("Corbel",FontWeight.BOLD,FontPosture.ITALIC, 20));
                tempOrderCheck.setPrefWidth(120);
                tempOrderCheck.setAlignment(Pos.CENTER);
                tempOrderCheck.setLayoutX(542);
                tempOrderCheck.setLayoutY(59);
                historyTab.getChildren().add(tempOrderCheck);

                String idOrderButton = String.valueOf(userOrder.elementAt(i).getIdComanda());
                //System.out.println(idOrderButton);

                Button tempOrderInfo = new Button(idOrderButton);
                tempOrderInfo.setId(idOrderButton);
                tempOrderInfo.setText("Order Information");
                tempOrderInfo.setPrefWidth(113);
                tempOrderInfo.setPrefHeight(25);
                tempOrderInfo.setLayoutX(709);
                tempOrderInfo.setLayoutY(21);
                tempOrderInfo.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
                tempOrderInfo.setTextFill(Paint.valueOf("white"));
                tempOrderInfo.setFont(new Font("System",10));
                tempOrderInfo.setStyle("-fx-font-weight: bold");
                tempOrderInfo.setOnAction(this::toOrderInformation);
                // add functie to restaurant
                historyTab.getChildren().add(tempOrderInfo);


                if(userOrder.elementAt(i).getIsOrderRated().equals("n"))
                {
                        // avem rate now


                    Button tempRateOrder = new Button(idOrderButton);
                    tempRateOrder.setId(idOrderButton);
                    tempRateOrder.setText("Rate Order");
                    tempRateOrder.setPrefWidth(113);
                    tempRateOrder.setPrefHeight(25);
                    tempRateOrder.setLayoutX(709);
                    tempRateOrder.setLayoutY(58);
                    tempRateOrder.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
                    tempRateOrder.setTextFill(Paint.valueOf("white"));
                    tempRateOrder.setFont(new Font("System",10));
                    tempRateOrder.setStyle("-fx-font-weight: bold");
                    tempRateOrder.setOnAction(this::toRateOrder);
                    // add functie to restaurant
                    historyTab.getChildren().add(tempRateOrder);









                }
                else {
                        // avem order again

                    Button tempOrderAgain = new Button(idOrderButton);
                    tempOrderAgain.setId(idOrderButton);
                    tempOrderAgain.setText("Order Again");
                    tempOrderAgain.setPrefWidth(113);
                    tempOrderAgain.setPrefHeight(25);
                    tempOrderAgain.setLayoutX(709);
                    tempOrderAgain.setLayoutY(58);
                    tempOrderAgain.getStylesheets().add(getClass().getResource("Images/order.css").toExternalForm());
                    tempOrderAgain.setTextFill(Paint.valueOf("white"));
                    tempOrderAgain.setFont(new Font("System",10));
                    tempOrderAgain.setStyle("-fx-font-weight: bold");
                    //tempRateOrder.setOnAction(this::toOrderAgain);
                    // add functie to restaurant
                    historyTab.getChildren().add(tempOrderAgain);
                }















                layoutY = layoutY + 107;
            }


           // Vector<Comanda> userOrder = SQL.getOrderUser(selectedUsername);

            boolean okStop = false;
            Timeline fiveSecondsWonder = new Timeline(
                    new KeyFrame(Duration.seconds(60),
                            new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {

                                    Vector<Comanda> userOrder = SQL.getOrderUser(selectedUsername);

                                    for( int i = 0; i < userOrder.size();i++)
                                    {

                                        System.out.println(ordersStatusNotifications.elementAt(i) + " = " + userOrder.elementAt(i).getOrderStatus());
                                        if(userOrder.elementAt(i).getOrderStatus().equals(ordersStatusNotifications.elementAt(i)))
                                        {

                                          //  System.out.println("all good");

                                        } else {
                                            System.out.println("nu intra");
                                            //ordersStatusNotifications.elementAt(i) = userOrder.elementAt(i).getOrderStatus();



                                            System.out.println(ordersStatusNotifications);
                                           // System.out.println("Notification sent");
                                            Notifications notificationUpdateStatus = Notifications.create()
                                                    .title("Update Order Status")
                                                    .text("Order Status Update: " + userOrder.elementAt(i).getOrderStatus())
                                                    .hideAfter(Duration.seconds(5))
                                                    .position(Pos.BOTTOM_RIGHT);
                                            notificationUpdateStatus.show();

                                            MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
                                            try {
                                                m.changeScene("userOrderHistory.fxml");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }



                                        }


                                    }


                                }
                            }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();


        }





        // if login succesful initializez ala








    }

    public void toOrderInformation(ActionEvent event){

        int itemButtonId = Integer.parseInt(((Button)event.getSource()).getId());
        String itemButtonIdString = Integer.toString(itemButtonId);
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("idOrderInformation",itemButtonIdString );

        System.out.println(itemButtonIdString);

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        try{
            m.changeScene("orderInformation.fxml");
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }


    public void toRateOrder(ActionEvent event){


        int itemButtonId = Integer.parseInt(((Button)event.getSource()).getId());
        String itemButtonIdString = Integer.toString(itemButtonId);
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("idRateOrder",itemButtonIdString );

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();

        try{
            m.changeScene("rateOrder.fxml");
        } catch (IOException e)
        {
            e.printStackTrace();
        }







    }


    public void goToTempRate(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("rateOrder.fxml");

    }

    public void updateNotificationsStatus() throws SQLException, IOException {

        SQLConnect SQL = new SQLConnect();

        Preferences userPreferences = Preferences.userRoot();
        String selectedUsername = userPreferences.get("username","none");



        Vector<Comanda> userOrder = SQL.getOrderUser(selectedUsername);

        //boolean okReset = true;
        for( int i = 0; i < userOrder.size();i++)
        {
            if(userOrder.elementAt(i).getOrderStatus().equals(ordersStatusNotifications.elementAt(i)))
            {

                System.out.println("all good");

            } else {


                Notifications notificationUpdateStatus = Notifications.create()
                        .title("Update Order Status")
                        .text("Order Status Update: " + userOrder.elementAt(i).getOrderStatus())
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.BOTTOM_RIGHT);
                notificationUpdateStatus.show();

                MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
                m.changeScene("userOrderHistory.fxml");

        }


        }



    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }

    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }
    public void testNotification(){

    }



}
