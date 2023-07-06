package MainApplicationUI;

import Classes.Produs;
import Classes.Restaurant;
import Classes.Voucher;
import Services.SQLConnect;
import javafx.fxml.FXML;


import java.awt.*;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;
import java.util.prefs.Preferences;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class UserCheckoutController {

    public void UserCheckoutController(){

    }

    @FXML
    private TextField voucherCode;

    @FXML
    private Label voucherAddedInfo;

    @FXML
    private Label voucherWarning;

    @FXML
    private AnchorPane topShowPane;

    @FXML
    private AnchorPane botShowPane;

    @FXML
    private Button verifyVoucher;

    @FXML
    private Button removeVoucher;

    @FXML
    private ImageView restaurantLogo;

    @FXML
    private Pane checkoutPane;

    @FXML
    private Label foodLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label totalShow;

    @FXML
    private Label checkoutVoucherText;

    @FXML
    private Label checkoutVoucherValue;

    @FXML
    private CheckBox cardChecked;

    @FXML
    private CheckBox cashChecked;

    @FXML
    private TextField adressTextBox;


    private boolean voucherActivated;
    private int voucherValue;

    DataSingleton data = DataSingleton.getInstance();
    private Vector<Produs> orderCheckout;
    private Restaurant selectedRestaurant;
    private int[] itemsNumber;
    private float sumOrder;
    private Vector<Produs> globalMenuSelected;

    public void initialize() throws SQLException {

        this.voucherActivated = false;

        SQLConnect SQL = new SQLConnect();
        float tempSum = 0;

        this.orderCheckout = data.getOrder();
        this.itemsNumber = data.getNumarProduseTemp();
        Preferences userPreferences = Preferences.userRoot();
        String idRestaurant = userPreferences.get("idRestaurant","none");
        Vector<String> tempRestaurantSelectedInfo = SQL.getRestaurantInfo(idRestaurant);
        System.out.println(tempRestaurantSelectedInfo);


        selectedRestaurant = new Restaurant();
        selectedRestaurant.setIdRestaurant(Integer.parseInt(idRestaurant));
        selectedRestaurant.setAdressRestaurant(tempRestaurantSelectedInfo.elementAt(3));
        selectedRestaurant.setOrasRestaurant(tempRestaurantSelectedInfo.elementAt(2));
        selectedRestaurant.setRatingRestaurant(Float.parseFloat(tempRestaurantSelectedInfo.elementAt(1)));
        selectedRestaurant.setNumeRestaurant(tempRestaurantSelectedInfo.elementAt(0));
        Vector<Produs> menuSelectedRestaurant = SQL.loadMenuRestaurant(Integer.parseInt(idRestaurant));
        this.globalMenuSelected = menuSelectedRestaurant;

        //id restaurant
        String logoName = "MainApplicationUI/Images/"+ selectedRestaurant.getOrasRestaurant() + selectedRestaurant.getNumeRestaurant() + ".png";
        restaurantLogo.setImage(new Image(logoName));
        restaurantLogo.setFitHeight(297);
        restaurantLogo.setFitWidth(300);

        System.out.println(menuSelectedRestaurant.size());


        String listaProduseComanda = "";
        String pretListaProdus ="";


        int layoutItemsY = 39;
        int layoutButtonY = 39;
        for( int i = 0; i < menuSelectedRestaurant.size();i++)
        {

            if( itemsNumber[i] > 0)
            {

                listaProduseComanda = listaProduseComanda + menuSelectedRestaurant.elementAt(i).getNumeProdus() + " x " + itemsNumber[i] + '\n';
                tempSum = tempSum + menuSelectedRestaurant.elementAt(i).getPretProdus() * itemsNumber[i];
                pretListaProdus = pretListaProdus + String.valueOf(menuSelectedRestaurant.elementAt(i).getPretProdus() * itemsNumber[i]) + " ron" + '\n';

                String idButton = "b" + i;
                Button tempRemoveButton = new Button();
                tempRemoveButton.setId(idButton);
                tempRemoveButton.setText("Remove");
                tempRemoveButton.setPrefWidth(55);
                tempRemoveButton.setMaxWidth(55);
                tempRemoveButton.setMinHeight(15);
                tempRemoveButton.setPrefHeight(15);
                tempRemoveButton.setMaxHeight(15);
                tempRemoveButton.setLayoutX(371);
                tempRemoveButton.setLayoutY(layoutButtonY);
                tempRemoveButton.getStylesheets().add(getClass().getResource("Images/checkoutButton.css").toExternalForm());
                //tempRemoveButton.setTextFill(Paint.valueOf("white"));
                tempRemoveButton.setFont(new Font("System", 6));
                tempRemoveButton.setStyle("-fx-font-weight: bold");

                idButton = "d" + i;
                Button tempDeleteButton = new Button();
                tempDeleteButton.setId(idButton);
                tempDeleteButton.setText("Add");
                tempDeleteButton.setPrefWidth(55);
                tempDeleteButton.setMaxWidth(55);
                tempDeleteButton.setMinWidth(55);
                tempDeleteButton.setMaxHeight(15);
                tempDeleteButton.setMinHeight(15);
                tempDeleteButton.setPrefHeight(15);
                tempDeleteButton.setLayoutX(434);
                tempDeleteButton.setLayoutY(layoutButtonY);
                tempDeleteButton.getStylesheets().add(getClass().getResource("Images/checkoutButton.css").toExternalForm());
                tempDeleteButton.setTextFill(Paint.valueOf("white"));
                tempDeleteButton.setFont(new Font("System",6));
                tempDeleteButton.setStyle("-fx-font-weight: bold");
                tempDeleteButton.setOnAction(this::addItemOrder);
                checkoutPane.getChildren().add(tempDeleteButton);

                tempRemoveButton.setOnAction(this::removeItemOrder);
                checkoutPane.getChildren().add(tempRemoveButton);





                layoutItemsY = layoutItemsY + 19;
                layoutButtonY = layoutButtonY + 24;


            }
            foodLabel.setText(listaProduseComanda);
            priceLabel.setText(pretListaProdus);
            this.sumOrder = tempSum + 5;
            totalShow.setText(String.valueOf(sumOrder) + " ron");




        }







        //verify voucher


    }

    public void addItemOrder(ActionEvent event){

        String addButtonId = ((Button)event.getSource()).getId();
        addButtonId = addButtonId.replace("d","");
        int buttonId = Integer.parseInt(addButtonId);

        itemsNumber[buttonId]++;



        String listaProduseComanda = "";
        String pretListaProdus ="";
        float tempSum = 0;


        for( int i = 0; i < globalMenuSelected.size();i++) {

            if (itemsNumber[i] > 0) {

                listaProduseComanda = listaProduseComanda + globalMenuSelected.elementAt(i).getNumeProdus() + " x " + itemsNumber[i] + '\n';
                tempSum = tempSum + globalMenuSelected.elementAt(i).getPretProdus() * itemsNumber[i];
                pretListaProdus = pretListaProdus + String.valueOf(globalMenuSelected.elementAt(i).getPretProdus() * itemsNumber[i]) + " ron" + '\n';
            }
        }

        foodLabel.setText(listaProduseComanda);
        priceLabel.setText(pretListaProdus);
        this.sumOrder = tempSum + 5;
        totalShow.setText(String.valueOf(sumOrder) + " ron");






    }

    public void removeItemOrder(ActionEvent event){

        String removeButtonId = ((Button)event.getSource()).getId();
        removeButtonId = removeButtonId.replace("b","");
        int itemButtonID = Integer.parseInt(removeButtonId);

        itemsNumber[itemButtonID]--;

        if(itemsNumber[itemButtonID] == 0)
        {
            //delete butoane
            System.out.println("Intra aici");
            //Button lookForRemoveButton = (Button) listPane.lookup("#b" + removeButtonId);
            checkoutPane.getChildren().remove(checkoutPane.lookup("#b" + removeButtonId));
            checkoutPane.getChildren().remove(checkoutPane.lookup("#d" + removeButtonId));

            //updatam pozitie butoane

            for( int i = itemButtonID+1; i< globalMenuSelected.size();i++)
            {
                System.out.println("Butonul se muta" + i);
                try{
                    Button lookForButton = (Button) checkoutPane.lookup("#b" +i);
                    lookForButton.setLayoutY(lookForButton.getLayoutY()-17);

                    Button lookForAddButton = (Button) checkoutPane.lookup("#d" + i);
                    lookForAddButton.setLayoutY(lookForAddButton.getLayoutY()-17);
                }
                catch(NullPointerException e){

                }
            }
            //Button lookForButton = (Button) listPane.lookup();
        }

        String listaProduseComanda = "";
        String pretListaProdus ="";
        float tempSum = 0;


        for( int i = 0; i < globalMenuSelected.size();i++) {

            if (itemsNumber[i] > 0) {

                listaProduseComanda = listaProduseComanda + globalMenuSelected.elementAt(i).getNumeProdus() + " x " + itemsNumber[i] + '\n';
                tempSum = tempSum + globalMenuSelected.elementAt(i).getPretProdus() * itemsNumber[i];
                pretListaProdus = pretListaProdus + String.valueOf(globalMenuSelected.elementAt(i).getPretProdus() * itemsNumber[i]) + " ron" + '\n';
            }
        }

        foodLabel.setText(listaProduseComanda);
        priceLabel.setText(pretListaProdus);
        this.sumOrder = tempSum + 5;
        totalShow.setText(String.valueOf(sumOrder) + " ron");




    }

    public void addVoucher(ActionEvent event) throws SQLException {


            SQLConnect SQL = new SQLConnect();

            String textFieldVoucher = voucherCode.getText();
            Voucher newVoucher = SQL.searchVoucher(textFieldVoucher);

            if (newVoucher.getCodVoucher() == null || newVoucher.getStatus().equals("inactive")) {

                if (newVoucher.getCodVoucher() == null) {
                    //inexistend
                    voucherWarning.setVisible(true);
                    voucherWarning.setText("The voucher does not exist");

                } else {
                    //expired
                    voucherWarning.setVisible(true);
                    voucherWarning.setText("The voucher has expired");
                }

                System.out.println("Voucher inexistent");
            } else {
                this.voucherActivated = true;
                voucherCode.setVisible(false);
                voucherAddedInfo.setVisible(true);
                voucherWarning.setVisible(false);
                System.out.println("Voucher added!");
                this.voucherValue = newVoucher.getValue();
                voucherAddedInfo.setText(newVoucher.getValue() + " ron voucher added");

                verifyVoucher.setVisible(false);
                removeVoucher.setVisible(true);

                checkoutVoucherText.setVisible(true);
                checkoutVoucherValue.setVisible(true);
                checkoutVoucherValue.setText(String.valueOf(voucherValue) + " ron");
                if( this.sumOrder - voucherValue < 0)
                {
                    this.sumOrder = 0;
                }
                else {
                        this.sumOrder = sumOrder - voucherValue;
                }
                totalShow.setText(String.valueOf(sumOrder) + " ron");



            }


    }

    public void removeVoucher(ActionEvent event) {

        this.voucherActivated = false;

        voucherAddedInfo.setVisible(false);
        voucherCode.clear();
        voucherCode.setVisible(true);

        verifyVoucher.setVisible(true);
        removeVoucher.setVisible(false);

        this.sumOrder = this.sumOrder + voucherValue;
        totalShow.setText(String.valueOf(sumOrder) + " ron");
        checkoutVoucherText.setVisible(false);
        checkoutVoucherValue.setVisible(false);
        this.voucherValue = 0;







    }


    public void sendOrder (ActionEvent event) throws SQLException, IOException {

        String allertMessage = "";
        boolean checkPayment = false;
        boolean checkAdress = false;
        boolean checkMinimumOrder = true;
        boolean negativeNotification = false;
        SQLConnect SQL = new SQLConnect();
        if (cardChecked.isSelected() && cashChecked.isSelected())
        {

            allertMessage =  allertMessage + "Please select only one payment option \n";
            negativeNotification = true;
        }
        else {
            if(cashChecked.isSelected() != true && cardChecked.isSelected() != true)
            {
                allertMessage =  allertMessage + "Please select a payment option \n";
                negativeNotification = true;
            }
            else{

                checkPayment = true ;
            }
        }

        if( adressTextBox.getLength() >= 10)
        {
            checkAdress = true;
        }
        else
        {
            allertMessage =  allertMessage + "Please select a valid adress \n";
            negativeNotification = true;
        }

        if(voucherActivated == true)
        {

            if(this.sumOrder + voucherValue - 5 < 30)
            {
                negativeNotification = true;
                allertMessage =  allertMessage + "Minimum order is of 30 ron ( before vouchers ) \n";
                checkMinimumOrder = false;

            }


        }
        else {
            if(this.sumOrder - 5 < 30)
            {
                negativeNotification = true;
                allertMessage =  allertMessage + "Minimum order is of 30 ron ( before vouchers ) \n";
                checkMinimumOrder = false;


            }
        }



        if( checkAdress && checkPayment && checkMinimumOrder)
        {
            int idRestaurant = selectedRestaurant.getIdRestaurant();
            String nameRestaurant = selectedRestaurant.getNumeRestaurant();
            int idComada = SQL.getOrderCurrent();

            String tempAdress = adressTextBox.getText();

            Calendar calendar = Calendar.getInstance();
            int tempDay = calendar.get(Calendar.DAY_OF_MONTH);
            int tempMonth = calendar.get(Calendar.MONTH);
            int tempYear = calendar.get(Calendar.YEAR);



            Preferences userPreferences = Preferences.userRoot();
            String tempUsername = userPreferences.get("username","none");

            //insert into comanda
            System.out.println("insert database");
            SQL.insertOrderDatabase(tempUsername, selectedRestaurant.getIdRestaurant(),tempDay,tempMonth,tempYear,tempAdress,this.sumOrder);

            int insertOrderId = SQL.getInsertOrder() ;

            //insert in ...
            for(int i = 0; i< globalMenuSelected.size(); i++)
            {
                for( int tempX = 0; tempX < itemsNumber[i]; tempX++)
                {
                    System.out.println(globalMenuSelected.elementAt(i).getNumeProdus()  + globalMenuSelected.elementAt(i).getIdProdsX());
                    SQL.insertOrderProduct(insertOrderId, globalMenuSelected.elementAt(i).getIdProdsX());
                }
            }








        }





        if ( negativeNotification == false) {
                allertMessage = "Order had been sent";
            MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
            m.changeScene("userHome.fxml");




        }

        Alert allertInfo = new Alert(Alert.AlertType.INFORMATION);
        allertInfo.setTitle("Order notification!");
        allertInfo.setHeaderText(allertMessage);
        //allertInfo.setContentText(allertMessage);

        allertInfo.show();





    }

    public void goToHistory(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userOrderHistory.fxml");
    }

    public  void goToProfile(ActionEvent event) throws IOException {
        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("profile.fxml");
    }


    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }



}
