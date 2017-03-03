package br.com.curiousguy.aerocar.db;

import java.util.List;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.Sort;

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
    public List<WorkSession> fetchActiveWorkSessions() {
        List<WorkSession> workSessions = realm.where(WorkSession.class)
                .equalTo("isActive", true)
                .findAllSorted("entry", Sort.DESCENDING);

        return realm.copyFromRealm(workSessions);
    }

    @Override
    public Price fetchNewestPriceTable() {
        Price price = realm.where(Price.class)
                .findFirst();

        if(price == null) {
            return null;
        }

        return realm.copyFromRealm(price);
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
