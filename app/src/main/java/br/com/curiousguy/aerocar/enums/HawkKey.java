package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum HawkKey {
    RECIPIENT_EMAIL("recipient.email");

    @Getter
    private String Key;

    HawkKey(String key) {
        Key = key;
    }
}
