package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum CarType implements Pricer.Priced {

    SMALL ("Carro Pequeno") {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleSmallCar();
        }
    },
    MEDIUM ("Carro Médio") {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleMediumCar();
        }
    },
    BIG ("Carro Grande") {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleBigCar();
        }
    },
    TAXI ("Taxi") {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getTaxi();
        }
    },
    UBER ("Uber") {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getUber();
        }
    };

    @Getter
    private String name;

    CarType(String name) {
        this.name = name;
    }

    private static Price getPriceTable() {
        DbFacade facade = new RealmFacade();
        return facade.fetchNewestPriceTable();
    }
}
