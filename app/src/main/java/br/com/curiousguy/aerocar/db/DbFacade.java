package br.com.curiousguy.aerocar.db;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;
import io.realm.RealmObject;

public interface DbFacade {

    Car fetchCarCopyByPlate(String plate) throws CarNotFoundException;

    <T extends RealmObject> void updateOrSave(T data);
}
