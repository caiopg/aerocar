package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.PriceTable;
import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum Wash implements Pricer.Priced {
    SIMPLE ("Simples") {
        @Override
        public int getPrice(CarType carType) {
            PriceTable priceTable = getPriceTable();

            switch (carType) {
                case SMALL:
                    return priceTable.getSimpleSmallCar();
                case MEDIUM:
                    return priceTable.getSimpleMediumCar();
                case BIG:
                    return priceTable.getSimpleBigCar();
                case TAXI:
                    return priceTable.getTaxi();
                case UBER:
                    return priceTable.getUber();
                default:
                    return 0;
            }
        }
    },
    WAX ("Cera") {
        @Override
        public int getPrice(CarType carType) {
            return SIMPLE.getPrice(carType) + getWaxPrice(carType);
        }
    },
    RESIN ("Resina") {
        @Override
        public int getPrice(CarType carType) {
            return SIMPLE.getPrice(carType) + getResinPrice(carType);
        }
    };

    @Getter
    private String name;

    Wash(String name) {
        this.name = name;
    }

    private static PriceTable getPriceTable() {
        DbFacade facade = new RealmFacade();
        return facade.fetchPriceTable();
    }

    public int getWaxPrice(CarType carType) {
        PriceTable priceTable = getPriceTable();

        int waxPrice;
        switch (carType) {
            case TAXI:
            case UBER:
            case BIG:
                waxPrice = 0;
                break;
            default:
                waxPrice = priceTable.getAdditionalWax();
        }

        return waxPrice;
    }

    public int getResinPrice(CarType carType) {
        PriceTable priceTable = getPriceTable();

        int resinPrice;
        switch (carType) {
            case BIG:
                resinPrice = priceTable.getAdditionalBigCarResin();
                break;
            default:
                resinPrice = priceTable.getAdditionalWax();
        }

        return resinPrice;
    }
}
