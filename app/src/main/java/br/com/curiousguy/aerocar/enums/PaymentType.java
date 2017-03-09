package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum PaymentType {

    CREDIT_CARD ("Crédito"),
    DEBIT_CARD ("Débito"),
    MONEY ("Dinheiro");

    @Getter
    private String name;

    PaymentType(String name) {
        this.name = name;
    }

}
