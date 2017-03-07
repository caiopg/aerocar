package br.com.curiousguy.aerocar.util;

import br.com.curiousguy.aerocar.enums.CarType;
import br.com.curiousguy.aerocar.enums.Wash;
import br.com.curiousguy.aerocar.model.WorkSession;

public class Pricer {

    public static long price(WorkSession workSession) {

        long price = 0;

        CarType carType = workSession.getCar().getType();
        if(workSession.hasWash()) {
            price = price + workSession.getWash().getPrice(carType);
        }

        if(workSession.hasService()) {
            price = price + workSession.getService().getPrice(carType);
        }

        return price;
    }

    public interface Priced {
        int getPrice(CarType carType);
    }
}
