package br.com.curiousguy.aerocar.db;

import com.orhanobut.hawk.Hawk;

import java.util.Date;
import java.util.List;

import br.com.curiousguy.aerocar.enums.HawkKey;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.PriceTable;
import br.com.curiousguy.aerocar.model.Report;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.Sort;

public class DataFacade implements DbFacade {

    Realm realm;

    public DataFacade() {
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
    public <T extends RealmObject> void updateOrSave(final T data) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    @Override
    public void saveRecipientEmail(String email) {
        Hawk.put(HawkKey.RECIPIENT_EMAIL.getKey(), email);
    }

    @Override
    public void saveLastReport(Report report) {
        Hawk.put(HawkKey.LAST_REPORT.getKey(), report);
    }

    @Override
    public void savePriceTable(PriceTable priceTable) {
        Hawk.put(HawkKey.PRICE_TABLE.getKey(), priceTable);
    }

    @Override
    public List<WorkSession> fetchInactiveWorkSessions(Date start, Date end) {
        List<WorkSession> workSessions = realm.where(WorkSession.class)
                .equalTo("isActive", false)
                .between("entry", start, end)
                .findAllSorted("entry", Sort.ASCENDING);

        return realm.copyFromRealm(workSessions);
    }

    @Override
    public String fetchRecipientEmail() {
        return Hawk.get(HawkKey.RECIPIENT_EMAIL.getKey(), "");
    }

    @Override
    public Report fetchLastReport() {
        return Hawk.get(HawkKey.LAST_REPORT.getKey(), null);
    }

    @Override
    public PriceTable fetchPriceTable() {
        return Hawk.get(HawkKey.PRICE_TABLE.getKey(), null);
    }
}
