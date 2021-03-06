package br.com.curiousguy.aerocar.model;

import android.text.TextUtils;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.CarType;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Parcel
public class Car extends RealmObject {

    @PrimaryKey
    @Getter @Setter
    private String plate;

    @Getter @Setter
    private Client client;

    private String type;

    @Getter @Setter
    private String model;

    @Getter @Setter
    private String uberRegistry;

    public CarType getType() {
        return CarType.valueOf(type);
    }

    public void setType(CarType type) {
        this.type = String.valueOf(type);
    }

    public List<Integer> getCreationErrorIdList() {
        ArrayList<Integer> errors = new ArrayList<>();

        if(TextUtils.isEmpty(plate)) {
            errors.add(R.string.new_work_session_error_no_plate);
        }

        if(TextUtils.isEmpty(model)) {
            errors.add(R.string.new_work_session_error_no_model);
        }

        if(TextUtils.isEmpty(type)) {
            errors.add(R.string.new_work_session_error_no_car_type);
        } else if(type.equals(String.valueOf(CarType.UBER)) && TextUtils.isEmpty(uberRegistry)) {
            errors.add(R.string.new_work_session_error_no_uber_register);
        }

        return errors;
    }

    public boolean hasClient() {
        return getClient() != null ;
    }

    public boolean isSpecial() {
        return this.getType() == CarType.TAXI || this.getType() == CarType.UBER;
    }
}
