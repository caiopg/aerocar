package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum RequestCode {
    NEW_CAR(0);

    @Getter
    int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}