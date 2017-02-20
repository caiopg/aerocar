package br.com.curiousguy.aerocar.db;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.Realm;
import io.realm.RealmObject;

public class RealmFacade implements DbFacade {

    Realm realm;

    public RealmFacade() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Car fetchCarCopyByPlate(String plate) throws CarNotFoundException {
        Car car = realm.where(Car.class)
                .equalTo("plate", plate)
                .findFirst();

        if(car == null) {
            throw new CarNotFoundException("Car not found. Plate: " + plate);
        }

        return realm.copyFromRealm(car);
    }

    @Override
    public <T extends RealmObject> void updateOrSave(final T data) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }
}
