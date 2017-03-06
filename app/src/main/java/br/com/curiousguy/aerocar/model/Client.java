package br.com.curiousguy.aerocar.model;

import android.text.TextUtils;

import org.parceler.Parcel;

import io.realm.RealmObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Parcel
public class Client extends RealmObject {

    private String name = "";

    private String telephone = "";

    public boolean hasName() {
        return !TextUtils.isEmpty(name);
    }

    public boolean hasTelephone() {
        return !TextUtils.isEmpty(telephone);
    }

}
