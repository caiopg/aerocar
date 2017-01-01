package br.com.curiousguy.aerocar.model;

import io.realm.RealmObject;
import lombok.Data;

@Data
public class Client extends RealmObject {

    private String name;

    private String telefone;

}
