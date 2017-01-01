package br.com.curiousguy.aerocar.db;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.Realm;

public class RealmFacade implements DbFacade {

    Realm realm;

    public RealmFacade() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Car fetchCarCopy(String plate) throws CarNotFoundException {
        Car car = realm.where(Car.class)
                .equalTo("plate", plate)
                .findFirst();

        if(car == null) {
            throw new CarNotFoundException("Car not found. Plate: " + plate);
        }

        return realm.copyFromRealm(car);
    }

    @Override
    public void updateOrSaveClient(final Client client) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(client);
            }
        });
    }

    @Override
    public void updateOrSaveCar(final Car car) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(car);
            }
        });
    }

    @Override
    public void updateOrSaveWorkSession(final WorkSession workSession) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(workSession);
            }
        });
    }
}
