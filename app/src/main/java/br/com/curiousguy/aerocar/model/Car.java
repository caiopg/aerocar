package br.com.curiousguy.aerocar.model;

import java.util.Date;

import lombok.Data;

@Data
public class Car {

    public enum CarType {
        SMALL, MEDIUM, BIG, TAXI, UBER;
    }

    private String plate;

    private Client client;

    private CarType type;

    private String uberRegistry;

    private Date entry;

    private Date exit;
}
