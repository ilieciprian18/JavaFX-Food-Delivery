package Services;

import Classes.*;

import javax.swing.plaf.nimbus.State;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class SQLConnect {
    private final String MySQLname = "jdbc:mysql://localhost:3306/fooddeliveryapp";
    private final String username = "root";
    private final String password = "B@@kc@s3";
    private final Connection conector = DriverManager.getConnection(MySQLname, username, password);

    public SQLConnect() throws SQLException {
        //constructorul imi spune daca nu ne putem connecta
        try {
            Connection conector = DriverManager.getConnection(MySQLname, username, password);
        } catch (SQLException e) {
            System.out.println("Wrong path");
        }
    }

    public boolean Login(String username, String pass) {
        boolean loginOK = false;
        String query = "select * from users where username =" + "'" + username + "'";
        try {
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while (respondData.next()) {
                String tempUsername = respondData.getString("username");
                String tempPass = respondData.getString("pass");


                if (pass.equals(tempPass))
                    return true;
            }
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }
        return false;

    }

    public int findUserType(String username){
        int tempUserType = -1;
        // 0 - user
        // 1 - livrator
        // 2/3 - restaurant/admin

        String query = " select userType from users where username = " + "'" + username + "'";
        try {
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while ( respondData.next()){
                tempUserType = Integer.parseInt(respondData.getString("userType"));
            }

        } catch (SQLException e){
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return tempUserType;
    }

    public boolean searchUsername(String username){

        boolean userUnique = false;
        String query = "select count(*) from users where username=" + "'" + username + "'";

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            while(respondData.next())
            {
                String numarCount = respondData.getString("count(*)");

                if(Integer.parseInt(numarCount) == 0)
                {
                    userUnique = true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return  userUnique;
    }

    public void insertUserInDatabase(String username, String password, String nume, String prenume, String email, String telefon, int userType, int bday, int bmonth, int byear, String gender){

        String query = "insert into users (username, pass, nume, prenume, email, telefon, userType, birthDay, birthMonth, birthYear, gender ) VALUES " +
                "(" + "'" + username + "'" + "," + "'" + password + "'" + "," + "'" + nume + "'" + "," + "'" + prenume + "'" + "," +
                "'" + email + "'" + "," + "'" + telefon + "'" + "," + userType + "," + bday + "," + bmonth + "," + byear + "," + "'" + gender + "'" + ")";

        try{
            Statement st = conector.createStatement();
            st.execute(query);

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

    }

    public void insterAuditLog(String description){

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateCurrent = LocalDateTime.now();

        String query = "insert into auditlog ( operationDate, operationType ) VALUES "  + "(" + "'" + dateFormat.format(dateCurrent) + "'" + "," +
                "'" + description + "'"
                + ")";

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

    }

    public Vector<Vector<String>> getRestauransCity(String oras){

        Vector<Vector<String>> respond = new Vector<>();
        String query = "select * from restaurant where restaurant_oras = " + "'" + oras + "'";
        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);
            while(respondData.next())
            {
                Vector<String> lineRespond = new Vector<>();
                String restaurantName = respondData.getString("restaurant_nume");
                String restaurantRating = respondData.getString("restaurant_rating");
                String restaurantAdress = respondData.getString("restaurant_adress");
                String restaurantID = respondData.getString("id_restaurant");
                //String restaurantCity = respondData.getString("restaurant_oras");
                lineRespond.add(restaurantName);
                lineRespond.add(restaurantRating);
                lineRespond.add(restaurantAdress);
                lineRespond.add(restaurantID);

                respond.add(lineRespond);

            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return respond;
    }

    public Vector<String> getRestaurantInfo(String id){

        Vector<String> respond = new Vector<>();
        String query = "select * from restaurant where id_restaurant = " + id;

        try {
                Statement st = conector.createStatement();
                ResultSet respondData = st.executeQuery(query);

                while(respondData.next())
                {
                    String restaurantName = respondData.getString("restaurant_nume");
                    String restaurantRating = respondData.getString("restaurant_rating");
                    String restaurantCity = respondData.getString("restaurant_oras");
                    String restaurantAdress = respondData.getString("restaurant_adress");

                    respond.add(restaurantName);
                    respond.add(restaurantRating);
                    respond.add(restaurantCity);
                    respond.add(restaurantAdress);

                }



        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }



        return respond;


    }

    public int getNumberReviewsRestaurant(String id){

        int numberReviews = 0;

        String query = " select count(*) from reviews where id_restaurant = " + id;

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next()){
                numberReviews = Integer.valueOf(respondData.getString("count(*)"));
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return numberReviews;
    }

    public Vector<Review> getReviewsRestaurant(String id){

        Vector<Review> reviewsRestaurant = new Vector<>();
        String query = "select * from reviews where id_restaurant = " + id;

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next()){

                String reviewsRateRestaurant = respondData.getString("reviews_rate");
                String reviewsUsernameRestaurant = respondData.getString("username_user");
                String reviewDescriptionRestaurant = respondData.getString("reviews_description");

                Review tempReviewRestaurant = new Review(Integer.valueOf(id),Integer.valueOf(reviewsRateRestaurant),reviewsUsernameRestaurant,reviewDescriptionRestaurant);
                reviewsRestaurant.add(tempReviewRestaurant);
            }


        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


        return reviewsRestaurant;
    }


    public String getNumeRestaurant(String id){

        String restaurantName = "";

        String query = "select restaurant_nume from restaurant\n" +
                "where id_restaurant " + id;

        try {
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while( respondData.next()){

                restaurantName = respondData.getString("restaurant_nume");

            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return restaurantName;
    }

    public Vector<Produs> loadMenuRestaurant(int id){

        Vector<Produs> meniu = new Vector<>();

        String query = " select * from meniu where id_restaurant = " + id + ";";
        try{
            Statement st = conector.createStatement();
            ResultSet respondDataOrders = st.executeQuery(query);

            while(respondDataOrders.next())
            {
                Produs tempProdus = new Produs();
                String tempNumeProdus = respondDataOrders.getString("nume_produs");
                String tempPriceProdus = respondDataOrders.getString("price_produs");
                String tempIngredienteProdus = respondDataOrders.getString("ingrediente_produs");
                int tempIdProdus = Integer.parseInt(respondDataOrders.getString("id_meniu"));


                tempProdus.setNumeProdus(tempNumeProdus);
                tempProdus.setPretProdus(Float.parseFloat(tempPriceProdus));
                tempProdus.setIngredienteProdus(tempIngredienteProdus);
                tempProdus.setIdProdsX(tempIdProdus);


                meniu.add(tempProdus);

            }
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return meniu;

    }


    public Voucher searchVoucher(String codID){

        Voucher tempVoucher = new Voucher();

        String query = "select * from voucher where cod = " + "'" + codID + "'";

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                String tempCod = respondData.getString("cod");
                int tempValue = Integer.parseInt(respondData.getString("voucher_value"));
                String statusVoucher = respondData.getString("statusVoucher");

                tempVoucher.setCodVoucher(tempCod);
                tempVoucher.setValue(tempValue);
                tempVoucher.setStatus(statusVoucher);
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return  tempVoucher;

    }

    public int getOrderCurrent(){

        int id = 0;
        String query = "select count(*) from comanda";

        try {

            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);



            while(respondData.next())
            {
                id = Integer.parseInt(respondData.getString("count(*)"));



            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return id;

    }

    public int getInsertOrder(){
        int futureOrderId = 0;
        String query = "select * from comanda";

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                futureOrderId = Integer.parseInt(respondData.getString("id_comanda"));
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        System.out.println(futureOrderId);
        return futureOrderId;
    }

    public void insertOrderDatabase( String username, int idRestaurant, int orderDay, int orderMonth, int orderYear, String adress, float suma){


        String query = "insert into comanda ( username_client, username_livrator, id_restaurant, orderday, ordermonth, orderyear, adress, suma, rated, status_order)" +
                            " values " + "(" + "'" + username + "'" + "," + "'unassigned'" + "," + idRestaurant + "," + orderDay + "," + orderMonth + "," + orderYear +
                "," + "'" + adress + "'" + "," + suma + "," + "'n'" + "," + "'Placed'" + ")";

        System.out.println(query);

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

    }

    public void insertOrderProduct(int idComanda, int idMeniu)
    {
        String query = "insert into comanda_are_produs (id_comanda,id_meniu) values " + "(" + idComanda + "," + idMeniu + ")";

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

    }

    public Restaurant loadRestaurantInfo(int id){

        String query = "select * from restaurant where id_restaurant =  " + id;
        System.out.println(query);
        Restaurant tempRestaurant = new Restaurant();

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next()) {
                String restaurantName = respondData.getString("restaurant_nume");
                String restaurantOras = respondData.getString("restaurant_oras");
                String restaurantAdress = respondData.getString("restaurant_adress");
                float restaurantRating = Float.parseFloat(respondData.getString("restaurant_rating"));


                tempRestaurant.setNumeRestaurant(restaurantName);
                tempRestaurant.setOrasRestaurant(restaurantOras);
                tempRestaurant.setAdressRestaurant(restaurantAdress);
                tempRestaurant.setRatingRestaurant(restaurantRating);
            }

        }
        catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


        return tempRestaurant;

    }



    public boolean historyOrder(String username){

        boolean isFirst = true;

        String query = " select count(*) from comanda where username_client = " + "'" + username + "'";

        try{

            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while( respondData.next())
            {
                int check = Integer.parseInt(respondData.getString("count(*)"));
                if( check > 0)
                {
                    isFirst = false;
                }
                else {
                        isFirst = true ;
                }
            }

        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return isFirst;

    }

    public int numberOrderClient(String username)
    {
        int numberOrders = 0;

        String query = " select count(*) from comanda where username_client = " + "'" + username + "'";

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                int tempNumberOrders = Integer.parseInt(respondData.getString("count(*)"));
                numberOrders = tempNumberOrders;
            }
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


        return numberOrders;

    }

    public Comanda getDetailsOrder(int id)
    {
        String query = "select * from comanda where id_comanda = " + id;

        Comanda tempComanda = new Comanda();

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                String tempUsernameClient = respondData.getString("username_client");
                String tempUsernameLivrator = respondData.getString("username_livrator");
                int tempIdRestaurant = Integer.parseInt(respondData.getString("id_restaurant"));
                int tempOrderDay = Integer.parseInt(respondData.getString("orderday"));
                int tempOrderMonth = Integer.parseInt(respondData.getString("ordermonth"));
                int tempOrderYear = Integer.parseInt(respondData.getString("orderyear"));
                String tempAdress = respondData.getString("adress");
                float tempSum = Float.parseFloat(respondData.getString("suma"));
                String tempRated = respondData.getString("rated");
                String tempStatusOrder = respondData.getString("status_order");

                tempComanda.setUsernameUser(tempUsernameClient);
                tempComanda.setUsernameLivrator(tempUsernameLivrator);
                tempComanda.setIdRestaurant(tempIdRestaurant);
                tempComanda.setOrderDay(tempOrderDay);
                tempComanda.setOrderMonth(tempOrderMonth);
                tempComanda.setOrderYear(tempOrderYear);
                tempComanda.setAdress(tempAdress);
                tempComanda.setSuma(tempSum);
                tempComanda.setIsOrderRated(tempRated);
                tempComanda.setOrderStatus(tempStatusOrder);


            }


        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


        return tempComanda;
    }

    public Vector<Comanda> getOrderUser(String username)
    {
        String query = "select * from comanda where username_client = " + "'" + username + "'";
        System.out.println(query);

        Vector<Comanda> comenziUser = new Vector<>();



        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {

                Comanda tempComanda = new Comanda();



                String tempUsernameClient = respondData.getString("username_client");
                String tempUsernameLivrator = respondData.getString("username_livrator");
                int tempIdRestaurant = Integer.parseInt(respondData.getString("id_restaurant"));
                int tempOrderDay = Integer.parseInt(respondData.getString("orderday"));
                int tempOrderMonth = Integer.parseInt(respondData.getString("ordermonth"));
                int tempOrderYear = Integer.parseInt(respondData.getString("orderyear"));


                String tempAdress = respondData.getString("adress");
                float tempSum = Float.parseFloat(respondData.getString("suma"));
                String tempRated = respondData.getString("rated");
                String tempStatusOrder = respondData.getString("status_order");

                int tempOrderId = Integer.parseInt(respondData.getString("id_comanda"));

                tempComanda.setUsernameUser(tempUsernameClient);
                tempComanda.setUsernameLivrator(tempUsernameLivrator);
                tempComanda.setIdRestaurant(tempIdRestaurant);
                tempComanda.setOrderDay(tempOrderDay);
                tempComanda.setOrderMonth(tempOrderMonth);
                tempComanda.setOrderYear(tempOrderYear);
                tempComanda.setAdress(tempAdress);
                tempComanda.setSuma(tempSum);
                tempComanda.setIsOrderRated(tempRated);
                tempComanda.setOrderStatus(tempStatusOrder);
                tempComanda.setIdComanda(tempOrderId);


                comenziUser.add(tempComanda);


            }


        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


        return comenziUser;


    }

    public Vector<Produs> getItemsOrder(int orderID){

            Vector<Produs> itemsOrder = new Vector<>();

            String query = "select meniu.id_restaurant,comanda_are_produs.id_meniu, meniu.nume_produs, meniu.price_produs " +
                    "from meniu,comanda_are_produs " +
                    "where comanda_are_produs.id_comanda = "+ orderID + " and comanda_are_produs.id_meniu = meniu.id_meniu";

            try{
                Statement st = conector.createStatement();
                ResultSet respondData = st.executeQuery(query);

                while(respondData.next())
                {

                    Produs tempProdus = new Produs();

                    String tempNameProdus = respondData.getString("nume_produs");
                    Float tempPriceProdus = Float.valueOf(respondData.getString("price_produs"));
                    int tempIdProdus = Integer.parseInt(respondData.getString("id_meniu"));

                    tempProdus.setNumeProdus(tempNameProdus);
                    tempProdus.setPretProdus(tempPriceProdus);
                    tempProdus.setIdProdsX(tempIdProdus);


                    itemsOrder.add(tempProdus);




                }
            } catch (SQLException e) {
                System.out.println("Eroare in executarea comenzii SQL");
            }




            return itemsOrder;

    }


    public Produs getDetailsProdus(int idProdus)
    {
        Produs tempProdus = new Produs();

        String query = "select * from meniu " +
                "where id_meniu = " + idProdus;

        System.out.println(query);


        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
                {


                    String tempNumeProdus = respondData.getString("nume_produs");
                    Float tempPriceProdus = Float.valueOf(respondData.getString("price_produs"));

                    tempProdus.setNumeProdus(tempNumeProdus);
                    tempProdus.setPretProdus(tempPriceProdus);



                }
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return tempProdus;
    }

    public void insertReviewDatabase(int idRestaurant, int reviewRating, String username, String reviewDescription){

        String query = "insert into reviews ( id_restaurant, reviews_rate, username_user, reviews_description) " +
                "values" + "(" + idRestaurant + "," + reviewRating + "," + "'" + username + "'" + "," + "'" + reviewDescription + "'" + ")";

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }



    }

    public int getNumberReviews (int idRestaurant){


        int nr = 0;

        String query = "select count(*) from reviews " +
                "where id_restaurant = " + idRestaurant;

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                int numberReviewsCount = Integer.parseInt(respondData.getString("count(*)"));
                nr = numberReviewsCount;
            }
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return  nr;

    }

    public void updateReviewRestaurant(int newReviewValue, Float oldReviewValue, int numberReviews, int idRestaurant){


        Float ratingNew = (oldReviewValue * numberReviews + newReviewValue) / (numberReviews + 1 );

        String query = "update restaurant " +
                "set restaurant_rating = " + ratingNew + " " +
                "where id_restaurant = " + idRestaurant;

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

    }

    public void updateOrderRated(int orderId){


        String query = "update comanda " +
                "set rated = 'y' " +
                "where id_comanda = " + orderId;

        try{
            Statement st = conector.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }


    }

    public User getDetailsUser(String username){

        User tempUser = new User();

        String query = "select * from users " +
                "where username = " + "'" + username + "'";

        try{
            Statement st = conector.createStatement();
            ResultSet respondData = st.executeQuery(query);

            while(respondData.next())
            {
                String nameUser = respondData.getString("nume");
                String prenumeUser = respondData.getString("prenume");
                String emailUser = respondData.getString("email");
                String telefonUser = respondData.getString("telefon");
                int birthDay = Integer.parseInt(respondData.getString("birthDay"));
                int birthMonth = Integer.parseInt(respondData.getString("birthMonth"));
                int birthYear = Integer.parseInt(respondData.getString("birthYear"));
                String gender = respondData.getString("gender");

                tempUser.setNume(nameUser);
                tempUser.setPrenume(prenumeUser);
                tempUser.setEmail(emailUser);
                tempUser.setTelefon(telefonUser);
                tempUser.setGender(gender);
                tempUser.setBirthDay(birthDay);
                tempUser.setBirthMonth(birthMonth);
                tempUser.setBirthYear(birthYear);



            }




        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return tempUser;

    }




    public Vector<Comanda> loadOrders(){

        Vector<Comanda> orders = new Vector<>();

        String query1 = "select * from comanda";

        try {

            Statement st = conector.createStatement();
            ResultSet respondDataOrders = st.executeQuery(query1);

            while(respondDataOrders.next())
            {
                String tempUsernameLivrator = respondDataOrders.getString("username_livrator");
                String tempUsernameUser = respondDataOrders.getString("username_client");
                String tempIdRestaurant = respondDataOrders.getString("id_restaurant");
                String numeRestaurant = getNumeRestaurant(tempIdRestaurant);
                String tempIdComanda = respondDataOrders.getString("id_comanda");
                String tempAdress = respondDataOrders.getString("adress");
                String tempSuma = respondDataOrders.getString("suma");






            }


        } catch (SQLException e) {
            System.out.println("Eroare in executarea comenzii SQL");
        }

        return orders;
    }



}
