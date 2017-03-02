package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;

public enum Wash implements Pricer.Priced {
    SIMPLE {
        @Override
        public int getPrice() {
            return 0;
        }
    },
    WAX {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getAdditionalWax();
        }
    },
    RESIN {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getAdditionalResin();
        }
    };

    private static Price getPriceTable() {
        DbFacade facade = new RealmFacade();
        return facade.fetchNewestPriceTable();
    }
}
