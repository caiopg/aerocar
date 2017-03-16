package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.DataFacade;
import br.com.curiousguy.aerocar.model.PriceTable;
import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum Service implements Pricer.Priced {
    SANITATION ("Higienização") {
        @Override
        public int getPrice(CarType carType) {
            PriceTable priceTable = getPriceTable();

            switch (carType) {
                case SMALL:
                    return priceTable.getSmallSanitation();
                case MEDIUM:
                    return priceTable.getMediumSanitation();
                case BIG:
                    return priceTable.getBigSanitation();
                default:
                    return 0;
            }
        }
    },
    POLISHING ("Polimento") {
        @Override
        public int getPrice(CarType carType) {
            PriceTable priceTable = getPriceTable();

            switch (carType) {
                case SMALL:
                    return priceTable.getSmallPolishing();
                case MEDIUM:
                    return priceTable.getMediumPolishing();
                case BIG:
                    return priceTable.getBigPolishing();
                default:
                    return 0;
            }
        }
    },
    LITTLE_REPAIRS ("Pequenos Reparos") {
        @Override
        public int getPrice(CarType carType) {
            PriceTable priceTable = getPriceTable();

            switch (carType) {
                case SMALL:
                    return priceTable.getSmallLittleRepairs();
                case MEDIUM:
                    return priceTable.getMediumLittleRepairs();
                case BIG:
                    return priceTable.getBigLittleRepairs();
                default:
                    return 0;
            }
        }
    };

    @Getter
    private String name;

    Service(String name) {
        this.name = name;
    }

    private static PriceTable getPriceTable() {
        DbFacade facade = new DataFacade();
        return facade.fetchPriceTable();
    }
}
