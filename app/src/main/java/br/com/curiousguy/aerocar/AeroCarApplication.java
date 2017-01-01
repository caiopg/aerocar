package br.com.curiousguy.aerocar;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AeroCarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRealm();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }

}
