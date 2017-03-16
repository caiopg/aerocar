package br.com.curiousguy.aerocar.db;

import java.util.Date;
import java.util.List;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.PriceTable;
import br.com.curiousguy.aerocar.model.Report;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.RealmObject;

public interface DbFacade {

    Car fetchCarCopyByPlate(String plate) throws CarNotFoundException;

    List<WorkSession> fetchActiveWorkSessions();

    PriceTable fetchPriceTable();

    <T extends RealmObject> void updateOrSave(T data);

    void saveRecipientEmail (String email);

    void saveLastReport(Report report);

    List<WorkSession> fetchInactiveWorkSessions(Date start, Date end);

    String fetchRecipientEmail();

    Report fetchLastReport();
}
