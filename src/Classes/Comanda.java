package Classes;

import java.util.Vector;

public class Comanda {

    protected String usernameUser;
    protected String usernameLivrator;
    protected String numeRestaerant;
    protected int idRestaurant;
    protected int idComanda;
    protected String adress;
    protected float suma;
    protected int orderDay;
    protected int orderMonth;
    protected int orderYear;
    protected String isOrderRated;
    protected String orderStatus;

    protected Vector<Produs> itemsOrder;

    public Comanda() {
    }

    public void setIsOrderRated(String isOrderRated) {
        this.isOrderRated = isOrderRated;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public int getOrderDay() {
        return orderDay;
    }

    public int getOrderMonth() {
        return orderMonth;
    }

    public int getOrderYear() {
        return orderYear;
    }

    public String getIsOrderRated() {
        return isOrderRated;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderDay(int orderDay) {
        this.orderDay = orderDay;
    }

    public void setOrderMonth(int orderMonth) {
        this.orderMonth = orderMonth;
    }

    public void setOrderYear(int orderYear) {
        this.orderYear = orderYear;
    }

    public String getUsernameUser() {
        return usernameUser;
    }


    public String getUsernameLivrator() {
        return usernameLivrator;
    }

    public String getNumeRestaerant() {
        return numeRestaerant;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public String getAdress() {
        return adress;
    }

    public float getSuma() {
        return suma;
    }

    public Vector<Produs> getItemsOrder() {
        return itemsOrder;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public void setUsernameLivrator(String usernameLivrator) {
        this.usernameLivrator = usernameLivrator;
    }

    public void setNumeRestaerant(String numeRestaerant) {
        this.numeRestaerant = numeRestaerant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }






    public void setItemsOrder(Vector<Produs> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }
}
