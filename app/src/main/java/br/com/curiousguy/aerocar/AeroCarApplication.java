package br.com.curiousguy.aerocar;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.DataFacade;
import br.com.curiousguy.aerocar.model.PriceTable;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AeroCarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRealm();
        initializeHawk();

        createDefaultPriceTableIfNecessary();
    }

    private void initializeHawk() {
        Hawk.init(this).build();
    }

    private void initializeRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();

        Realm.setDefaultConfiguration(config);
    }

    private void createDefaultPriceTableIfNecessary() {
        DbFacade facade = new DataFacade();
        PriceTable priceTable = facade.fetchPriceTable();

        if(priceTable == null) {
            facade.updateOrSave(new PriceTable());
        }
    }

}
