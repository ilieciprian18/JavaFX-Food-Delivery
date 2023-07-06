package Services;

import Classes.Restaurant;

import java.util.Vector;

public class RestaurantService {

    public Vector<Restaurant> restaurante;

    public RestaurantService() {
        this.restaurante = new Vector<>();
    }

    public void setRestaurante(Vector<Restaurant> restaurante) {
        this.restaurante = restaurante;
    }
}
