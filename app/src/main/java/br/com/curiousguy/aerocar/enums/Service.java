package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Price;
import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum Service{
    SANITATION ("Higienização"),
    POLISHING ("Polimento"),
    LITTLE_REPAIRS ("Pequenos Reparos");

    @Getter
    private String name;

    Service(String name) {
        this.name = name;
    }

}
