package br.com.curiousguy.aerocar.util;

import br.com.curiousguy.aerocar.model.WorkSession;

public class Pricer {

    public static long price(WorkSession workSession) {

        long price = 0;

        if(workSession.hasCar() && workSession.getCar().hasType()) {
            price = price + workSession.getCar().getType().getPrice();
        }

        if(workSession.hasService()) {
            price = price + workSession.getService().getPrice();
        }

        if(workSession.hasWash()) {
            price = price + workSession.getWash().getPrice();
        }

        return price;
    }

    public interface Priced {
        int getPrice();
    }
}