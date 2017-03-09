package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum RequestCode {
    NEW_WORK_SESSIO(0),
    CLOSE_WORK_SESSION(1),
    REQUEST_PERMISSION(2);

    @Getter
    int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
