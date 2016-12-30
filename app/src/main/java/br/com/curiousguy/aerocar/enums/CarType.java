package br.com.curiousguy.aerocar.enums;


import br.com.curiousguy.aerocar.R;
import lombok.Getter;

public enum CarType {

    SMALL(R.string.new_car_type_small),
    MEDIUM(R.string.new_car_type_medium),
    BIG(R.string.new_car_type_big),
    TAXI(R.string.new_car_type_taxi),
    UBER(R.string.new_car_type_uber);

    @Getter
    private int stringId;

    CarType(int stringId) {
        this.stringId = stringId;
    }
}
