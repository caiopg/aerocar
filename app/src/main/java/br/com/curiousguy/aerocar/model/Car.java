package br.com.curiousguy.aerocar.model;

import br.com.curiousguy.aerocar.enums.CarType;
import lombok.Data;

@Data
public class Car {

    private String plate;

    private Client client;

    private CarType type;
}
