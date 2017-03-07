package br.com.curiousguy.aerocar.enums;

import lombok.Getter;

public enum RequestCode {
    NEW_WORK_SESSION(0),
    CLOSE_WORK_SESSION(1);

    @Getter
    int requestCode;

    RequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
