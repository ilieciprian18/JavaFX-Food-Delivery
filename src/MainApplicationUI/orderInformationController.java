package MainApplicationUI;

import Classes.Comanda;
import Classes.Produs;
import Classes.Restaurant;
import Services.SQLConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.prefs.Preferences;

public class orderInformationController {




    public void orderInformationController()
    {

    }

    @FXML
    private AnchorPane informationPane;

    @FXML
    private Label voucherTextShow;

    @FXML
    private Label voucherSumShow;

    @FXML
    private Label totalTextShow;

    @FXML
    private Label itemsShow;

    @FXML
    private Label priceShow;

    @FXML
    private ImageView restaurantLogo;

    public void initialize() throws SQLException {


        SQLConnect SQL = new SQLConnect();
        Preferences userPreferences = Preferences.userRoot();
        String selectedOrderIdTemp = userPreferences.get("idOrderInformation","none");
        int selectedOrderId = Integer.parseInt(selectedOrderIdTemp);


        Comanda selectedOrder = SQL.getDetailsOrder(selectedOrderId);

        Restaurant currentRestaurant = SQL.loadRestaurantInfo(selectedOrder.getIdRestaurant());

        Label usernameLivratorOrder = new Label();
        usernameLivratorOrder.setText(selectedOrder.getUsernameLivrator());
        usernameLivratorOrder.setFont(Font.font("System", FontPosture.ITALIC, 20));
        usernameLivratorOrder.setPrefWidth(180);
        usernameLivratorOrder.setAlignment(Pos.CENTER);
        usernameLivratorOrder.setLayoutX(61);
        usernameLivratorOrder.setLayoutY(448);
        informationPane.getChildren().add(usernameLivratorOrder);

        String logoName = "MainApplicationUI/Images/"+ currentRestaurant.getOrasRestaurant() + currentRestaurant.getNumeRestaurant() + ".png";
        restaurantLogo.setImage(new Image(logoName));
        restaurantLogo.setFitHeight(297);
        restaurantLogo.setFitWidth(300);

        Label deliveryAdress = new Label();
        deliveryAdress.setText(selectedOrder.getAdress());
        deliveryAdress.setFont(Font.font("System", FontPosture.ITALIC, 15));
        deliveryAdress.setPrefWidth(200);
        deliveryAdress.setAlignment(Pos.CENTER);
        deliveryAdress.setLayoutX(51);
        deliveryAdress.setLayoutY(525);
        informationPane.getChildren().add(deliveryAdress);

        Label restaurantNameShow = new Label();
        restaurantNameShow.setText(currentRestaurant.getNumeRestaurant());
        restaurantNameShow.setFont(Font.font("Sylfaen", 25));
        restaurantNameShow.setPrefWidth(200);
        restaurantNameShow.setAlignment(Pos.CENTER);
        restaurantNameShow.setLayoutX(51);
        restaurantNameShow.setLayoutY(335);
        informationPane.getChildren().add(restaurantNameShow);

        Label restaurantAdressShow = new Label();
        restaurantAdressShow.setText(currentRestaurant.getAdressRestaurant());
        restaurantAdressShow.setFont(Font.font("System",FontPosture.ITALIC, 15));
        restaurantAdressShow.setPrefWidth(200);
        restaurantAdressShow.setAlignment(Pos.CENTER);
        restaurantAdressShow.setLayoutX(49);
        restaurantAdressShow.setLayoutY(366);
        informationPane.getChildren().add(restaurantAdressShow);

        Label restaurantOrasShow = new Label();
        restaurantOrasShow.setText(currentRestaurant.getOrasRestaurant());
        restaurantOrasShow.setFont(Font.font("System",FontPosture.ITALIC, 15));
        restaurantOrasShow.setPrefWidth(120);
        restaurantOrasShow.setAlignment(Pos.CENTER);
        restaurantOrasShow.setLayoutX(91);
        restaurantOrasShow.setLayoutY(385);
        informationPane.getChildren().add(restaurantOrasShow);

        Label statusOrderOption = new Label();
        statusOrderOption.setText("Order Status: " + selectedOrder.getOrderStatus());
        statusOrderOption.setFont(Font.font("Javanese Text", 15));
        statusOrderOption.setPrefWidth(250);
        statusOrderOption.setAlignment(Pos.CENTER);
        statusOrderOption.setLayoutX(449);
        statusOrderOption.setLayoutY(312);
        informationPane.getChildren().add(statusOrderOption);

        String numeImagine = "";

        if(selectedOrder.getOrderStatus().equals("Placed"))
        {
            numeImagine = "MainApplicationUI/Images/placed.gif";
        }
        else {
            numeImagine = "MainApplicationUI/Images/cooking.gif";
        }

        if(selectedOrder.getOrderStatus().equals("In Preparation"))
        {

            numeImagine = "MainApplicationUI/Images/cooking.gif";
        }


        if(selectedOrder.getOrderStatus().equals("Pick Up"))
        {
            numeImagine = "MainApplicationUI/Images/pickup.gif";
        }

        if(selectedOrder.getOrderStatus().equals("On the way"))
        {
            numeImagine = "MainApplicationUI/Images/delivery.gif";
        }

        if(selectedOrder.getOrderStatus().equals("Delivered"))
        {
            numeImagine = "MainApplicationUI/Images/delivered.gif";
        }


        ImageView statusOrderGif = new ImageView();
        //System.out.println(numeImagine);
        statusOrderGif.setImage(new Image(numeImagine));
        statusOrderGif.setFitWidth(365);
        statusOrderGif.setFitHeight(206);
        statusOrderGif.setLayoutX(471);
        statusOrderGif.setLayoutY(350);
        statusOrderGif.setPreserveRatio(true);
        informationPane.getChildren().add(statusOrderGif);

        //generam lista de produse
        Vector<Produs> selectedOrderItems = new Vector<>();
        selectedOrderItems = SQL.getItemsOrder(selectedOrderId);

        //int tempId = selectedOrderItems.elementAt(0).getIdProdsX();
        //System.out.println(tempId);
        int nrProduse = 0;
        int[] countProductsOrder = new int[20];
        int[] idProduseCount = new int[20];
        int tempContorX = 0;
        float sum = 0;

        for(int i = 0; i < selectedOrderItems.size();i++)
        {
            int tempProdusId = selectedOrderItems.elementAt(i).getIdProdsX();
            if(countProductsOrder[tempProdusId] == 0)
            {
                    idProduseCount[tempContorX] = tempProdusId;
                    tempContorX++;
            }
            countProductsOrder[tempProdusId] ++;
            sum = sum + selectedOrderItems.elementAt(i).getPretProdus();
        }

        String productsList = "";
        String productsPrice = "";
        for(int i = 0; i < tempContorX;i++)
        {
            //afis comenzi
            System.out.println(idProduseCount[i]);
            productsList = productsList + SQL.getDetailsProdus(idProduseCount[i]).getNumeProdus() + " x " + countProductsOrder[idProduseCount[i]] + '\n';
            productsPrice = productsPrice + SQL.getDetailsProdus(idProduseCount[i]).getPretProdus() * countProductsOrder[idProduseCount[i]] + " ron" +  '\n';

            System.out.println(countProductsOrder[idProduseCount[i]]);
        }



        itemsShow.setText(productsList);
        priceShow.setText(productsPrice);



        // put voucher
        System.out.println(selectedOrder.getSuma());
        if( sum + 5 != selectedOrder.getSuma())
        {
            voucherTextShow.setVisible(true);
            voucherSumShow.setVisible(true);
            voucherSumShow.setText(selectedOrder.getSuma() - sum - 5 + " ron");
        }

        totalTextShow.setText(selectedOrder.getSuma()+ 5 + " ron");






















        




    }

    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }


    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }






}
