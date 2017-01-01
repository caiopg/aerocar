package br.com.curiousguy.aerocar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

public class Car extends RealmObject {

    public enum CarType {
        SMALL, MEDIUM, BIG, TAXI, UBER;
    }

    @PrimaryKey
    @Getter @Setter
    private String plate;

    @Getter @Setter
    private Client client;

    private String type;

    @Getter @Setter
    private String uberRegistry;

    public CarType getType() {
        return CarType.valueOf(type);
    }

    public void setType(CarType type) {
        this.type = String.valueOf(type);
    }
}
