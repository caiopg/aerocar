package br.com.curiousguy.aerocar.db;

import java.util.List;

import br.com.curiousguy.aerocar.model.Car;
import io.realm.Realm;

public class RealmFacade implements DbFacade {

    Realm realm;

    public RealmFacade() {
        realm.getDefaultInstance();
    }

    @Override
    public boolean containsCar(String plate) {
        List<Car> cars = realm.where(Car.class)
                .equalTo("plate", plate)
                .findAll();

        return cars.size() > 0;
    }
}
