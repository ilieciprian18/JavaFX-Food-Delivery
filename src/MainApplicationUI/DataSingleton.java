package MainApplicationUI;

import Classes.Produs;

import java.util.Vector;

public class DataSingleton {

    private static final DataSingleton instance = new DataSingleton();


    private Vector<Produs> comandaTemp;
    private int[]numarProduseTemp;

    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public void sendComanda(Vector<Produs> order){
        this.comandaTemp = order;
    }

    public Vector<Produs> getOrder(){
        return comandaTemp;
    }

    public int[] getNumarProduseTemp(){
        return numarProduseTemp;
    }

    public void setNumarProduseTemp(int[] numarProduseTemp) {
        this.numarProduseTemp = numarProduseTemp;
    }
}
