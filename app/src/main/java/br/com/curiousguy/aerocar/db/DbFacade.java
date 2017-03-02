package br.com.curiousguy.aerocar.db;

import java.util.List;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.RealmObject;

public interface DbFacade {

    Car fetchCarCopyByPlate(String plate) throws CarNotFoundException;

    List<WorkSession> fetchAllWorkSessions();

    Price fetchNewestPriceTable();

    <T extends RealmObject> void updateOrSave(T data);
}
