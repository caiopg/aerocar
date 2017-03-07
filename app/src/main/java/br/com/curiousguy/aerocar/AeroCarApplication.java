package br.com.curiousguy.aerocar;

import android.app.Application;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.PriceTable;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AeroCarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRealm();

        createDefaultPriceTableIfNecessary();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();

        Realm.setDefaultConfiguration(config);
    }

    private void createDefaultPriceTableIfNecessary() {
        DbFacade facade = new RealmFacade();
        PriceTable priceTable = facade.fetchPriceTable();

        if(priceTable == null) {
            facade.updateOrSave(new PriceTable());
        }
    }

}
