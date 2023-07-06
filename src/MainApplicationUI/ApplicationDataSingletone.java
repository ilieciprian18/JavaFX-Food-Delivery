package MainApplicationUI;

import Classes.Produs;
import Services.ComandaService;
import Services.RestaurantService;
import Services.UserService;

import java.util.Vector;

public class ApplicationDataSingletone {

    private static final ApplicationDataSingletone instance = new ApplicationDataSingletone();

    private RestaurantService restaurante;
    private UserService useriAplicatie;
    private ComandaService comenziAplicatie;

    private Vector<Produs> comandaCheckout;

    //pentru a tine minte comanda pt checkout

    private ApplicationDataSingletone(){

    }

    public static ApplicationDataSingletone getInstance(){
            return instance;
    }

}
