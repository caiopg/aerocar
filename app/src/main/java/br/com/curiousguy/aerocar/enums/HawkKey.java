package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum HawkKey {
    RECIPIENT_EMAIL("recipient.email"),
    LAST_REPORT("last.report.address"),
    PRICE_TABLE("price.table");

    @Getter
    private String Key;

    HawkKey(String key) {
        Key = key;
    }
}
