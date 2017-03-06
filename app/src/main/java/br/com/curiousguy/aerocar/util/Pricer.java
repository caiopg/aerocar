package br.com.curiousguy.aerocar.util;

import br.com.curiousguy.aerocar.enums.Wash;
import br.com.curiousguy.aerocar.model.WorkSession;

public class Pricer {

    //todo redo -> no cartype
    public static long price(WorkSession workSession) {

        long price = 0;

        price = price + workSession.getCar().getType().getPrice();

        if(workSession.hasService()) {
            price = price + workSession.getService().getPrice();
        }

        if(workSession.hasWash() && !fitsSpecialCriteria(workSession)) {
            price = price + workSession.getWash().getPrice();
        }

        return price;
    }

    private static boolean fitsSpecialCriteria(WorkSession workSession) {
        return workSession.getCar().isSpecialCar() && workSession.getWash() == Wash.WAX;
    }

    public interface Priced {
        int getSimplePrice();
        int getWaxPrice();
        int getResinPrice();
        int getSanitationPrice();
        int getPolishingPrice();
        int getLittleRepairsPrice();
    }
}
