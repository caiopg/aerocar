package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;

public enum Service implements Pricer.Priced {
    SANITATION {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getSanitation();
        }
    },
    POLISHING {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getPolishing();
        }
    },
    LITTLE_REPAIRS {
        @Override
        public int getPrice() {
            Price price = getPriceTable();
            return price.getLittleRepairs();
        }
    };

    private static Price getPriceTable() {
        DbFacade facade = new RealmFacade();
        return facade.fetchNewestPriceTable();
    }
}
