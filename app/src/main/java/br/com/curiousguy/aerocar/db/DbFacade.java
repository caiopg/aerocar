package br.com.curiousguy.aerocar.db;

import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;

public interface DbFacade {

    Car fetchCarCopy(String plate) throws CarNotFoundException;

    void updateOrSaveClient(Client client);

    void updateOrSaveCar (Car car);

    void updateOrSaveWorkSession(WorkSession workSession);
}
