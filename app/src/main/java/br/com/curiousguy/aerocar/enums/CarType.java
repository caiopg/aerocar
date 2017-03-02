package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;

public enum CarType implements Pricer.Priced {

    SMALL {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleSmallCar();
        }
    },
    MEDIUM {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleMediumCar();
        }
    },
    BIG {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSimpleBigCar();
        }
    },
    TAXI {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getTaxi();
        }
    },
    UBER {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getUber();
        }
    };

    private static Price getPriceTable() {
        DbFacade facade = new RealmFacade();
        return facade.fetchNewestPriceTable();
    }
}
