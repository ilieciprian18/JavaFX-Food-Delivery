package Classes;

import java.util.Vector;

public class Restaurant {

    protected int idRestaurant;
    protected String numeRestaurant;
    protected float ratingRestaurant;
    protected String orasRestaurant;
    protected String adressRestaurant;
    protected Vector<Produs> meniu = new Vector<Produs>();


    public Restaurant() {
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public String getNumeRestaurant() {
        return numeRestaurant;
    }

    public float getRatingRestaurant() {
        return ratingRestaurant;
    }

    public String getOrasRestaurant() {
        return orasRestaurant;
    }

    public String getAdressRestaurant() {
        return adressRestaurant;
    }

    public Vector<Produs> getMeniu() {
        return meniu;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        this.numeRestaurant = numeRestaurant;
    }

    public void setRatingRestaurant(float ratingRestaurant) {
        this.ratingRestaurant = ratingRestaurant;
    }

    public void setOrasRestaurant(String orasRestaurant) {
        this.orasRestaurant = orasRestaurant;
    }

    public void setAdressRestaurant(String adressRestaurant) {
        this.adressRestaurant = adressRestaurant;
    }

    public void setMeniu(Vector<Produs> meniu) {
        this.meniu = meniu;
    }
}
