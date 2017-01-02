package br.com.curiousguy.aerocar.model;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.curiousguy.aerocar.R;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class WorkSession extends RealmObject {

    public WorkSession () {
        Random generator = new Random();
        this.id = generator.nextLong();
    }

    public enum Service {
        SANITATION, POLISHING, LITTLE_REPAIRS;
    }

    public enum Wash {
        SIMPLE, WAX, RESIN
    }

    @PrimaryKey
    @Getter @Setter
    private long id;

    @Getter @Setter
    private Car car;

    private String service;

    private String wash;

    @Getter @Setter
    private Date entry;

    @Getter @Setter
    private Date exit;

    public Service getService() {
        return Service.valueOf(service);
    }

    public void setService(Service service) {
        this.service = String.valueOf(service);
    }

    public Wash getWash() {
        return Wash.valueOf(wash);
    }

    public void setWash(Wash wash) {
        this.wash = String.valueOf(wash);
    }

    public List<Integer> getErrorIdList() {
        ArrayList<Integer> errors = new ArrayList<>();

        if(TextUtils.isEmpty(service) && TextUtils.isEmpty(wash)) {
            errors.add(R.string.new_car_error_no_work);
        }

        return errors;
    }
}
