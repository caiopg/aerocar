package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum CarType {

    SMALL ("Carro Pequeno"),
    MEDIUM ("Carro Médio"),
    BIG ("Carro Grande"),
    TAXI ("Taxi"),
    UBER ("Uber");

    @Getter
    private String name;

    CarType(String name) {
        this.name = name;
    }

}
