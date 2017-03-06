package br.com.curiousguy.aerocar.enums;

import br.com.curiousguy.aerocar.util.Pricer;
import lombok.Getter;

public enum Wash {
    SIMPLE ("Simples"),
    WAX ("Cera"),
    RESIN ("Resina");

    @Getter
    private String name;

    Wash(String name) {
        this.name = name;
    }
}
