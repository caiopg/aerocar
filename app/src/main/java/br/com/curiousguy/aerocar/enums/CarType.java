package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum CarType implements Pricer.Priced{

    SMALL ("Carro Pequeno") {
        @Override
        public int getSimplePrice() {
            Price price = CarType.getPriceTable();
            return price.getSimpleSmallCar();
        }

        @Override
        public int getSanitationPrice() {
            Price price = CarType.getPriceTable();
            return price.getSmallSanitation();
        }

        @Override
        public int getPolishingPrice() {
            Price price = CarType.getPriceTable();
            return price.getSmallPolishing();
        }

        @Override
        public int getLittleRepairsPrice() {
            Price price = CarType.getPriceTable();
            return price.getSmallLittleRepairs();
        }
    },
    MEDIUM ("Carro Médio") {
        @Override
        public int getSimplePrice() {
            Price price = CarType.getPriceTable();
            return price.getSimpleMediumCar();
        }

        @Override
        public int getSanitationPrice() {
            Price price = CarType.getPriceTable();
            return price.getMediumSanitation();
        }

        @Override
        public int getPolishingPrice() {
            Price price = CarType.getPriceTable();
            return price.getMediumPolishing();
        }

        @Override
        public int getLittleRepairsPrice() {
            Price price = CarType.getPriceTable();
            return price.getMediumSanitation();
        }
    },
    BIG ("Carro Grande") {
        @Override
        public int getSimplePrice() {
            Price price = CarType.getPriceTable();
            return price.getSimpleBigCar();
        }

        @Override
        public int getSanitationPrice() {
            Price price = CarType.getPriceTable();
            return price.getBigSanitation();
        }

        @Override
        public int getPolishingPrice() {
            Price price = CarType.getPriceTable();
            return price.getBigPolishing();
        }

        @Override
        public int getLittleRepairsPrice() {
            Price price = CarType.getPriceTable();
            return price.getBigLittleRepairs();
        }
    },
    TAXI ("Taxi") {
        @Override
        public int getSimplePrice() {
            Price price = CarType.getPriceTable();
            return price.getTaxi();
        }

        @Override
        public int getWaxPrice() {
            return 0;
        }

    },
    UBER ("Uber") {
        @Override
        public int getSimplePrice() {
            Price price = CarType.getPriceTable();
            return price.getUber();
        }

        @Override
        public int getWaxPrice() {
            return 0;
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

    @Override
    public int getWaxPrice() {
        Price price = CarType.getPriceTable();
        return price.getAdditionalWax();
    }

    @Override
    public int getResinPrice() {
        Price price = CarType.getPriceTable();
        return price.getAdditionalResin();
    }

    @Override
    public int getSanitationPrice() {
        return 0;
    }

    @Override
    public int getPolishingPrice() {
        return 0;
    }

    @Override
    public int getLittleRepairsPrice() {
        return 0;
    }


}
