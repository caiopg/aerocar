package br.com.curiousguy.aerocar.model;

import android.text.TextUtils;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.Service;
import br.com.curiousguy.aerocar.enums.Wash;
import br.com.curiousguy.aerocar.model.parcelconverter.RealmListParcelConverter;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Parcel
public class WorkSession extends RealmObject {

    @PrimaryKey
    @Getter @Setter
    private long id;

    @Getter @Setter
    private boolean isActive = true;

    @Getter @Setter
    private Car car;

    private String service;

    private String wash;

    @Getter @Setter
    private boolean isPayed;

    @Getter @Setter
    @ParcelPropertyConverter(RealmListParcelConverter.class)
    private RealmList<Payment> payments;

    @Getter @Setter
    private String tip;

    @Getter @Setter
    private Date entry;

    @Getter @Setter
    private Date exit;

    public static WorkSession build() {
        WorkSession workSession = new WorkSession();

        Random generator = new Random();
        long id = generator.nextLong();
        workSession.setId(id);

        return workSession;
    }

    public Service getService() {
        return Service.valueOf(service);
    }

    public void setService(Service service) {
        if(service == null) {
            this.service = "";
            return;
        }

        this.service = String.valueOf(service);
    }

    public Wash getWash() {
        return Wash.valueOf(wash);
    }

    public void setWash(Wash wash) {
        if(wash == null) {
            this.wash = "";
            return;
        }

        this.wash = String.valueOf(wash);
    }

    public List<Integer> getCreationErrorIdList() {
        ArrayList<Integer> errors = new ArrayList<>();

        if(TextUtils.isEmpty(service) && TextUtils.isEmpty(wash)) {
            errors.add(R.string.new_work_session_error_no_work);
        }

        return errors;
    }

    public List<Integer> getPaymentErrorIdList() {
        ArrayList<Integer> errors = new ArrayList<>();

        if(payments.size() == 0) {
            errors.add(R.string.close_work_session_error_no_payment_type);
        }

        return errors;
    }

    public boolean hasService() {
        return !TextUtils.isEmpty(this.service);
    }

    public boolean hasWash() {
        return !TextUtils.isEmpty(this.wash);
    }

    public boolean hasTip() {
        return !TextUtils.isEmpty(this.tip);
    }

    public String getFormattedEntry() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy -HH:mm");

        return format.format(getEntry());
    }

    public int getIntTip() {
        return Integer.valueOf(this.tip);
    }
}
