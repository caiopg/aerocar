package br.com.curiousguy.aerocar.feature.prices;

import android.databinding.ObservableField;
import android.text.TextUtils;

import br.com.curiousguy.aerocar.db.DataFacade;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.model.PriceTable;

public class PricesViewModelImpl implements PricesViewModel {

    public final ObservableField<String> smallSimpleWash = new ObservableField<>();
    public final ObservableField<String> mediumSimpleWash = new ObservableField<>();
    public final ObservableField<String> bigSimpleWash = new ObservableField<>();
    public final ObservableField<String> taxi = new ObservableField<>();
    public final ObservableField<String> uber = new ObservableField<>();
    public final ObservableField<String> additionalWax = new ObservableField<>();
    public final ObservableField<String> additionalResin = new ObservableField<>();
    public final ObservableField<String> additionalResinBig = new ObservableField<>();
    public final ObservableField<String> smallPolishing = new ObservableField<>();
    public final ObservableField<String> mediumPolishing = new ObservableField<>();
    public final ObservableField<String> bigPolishing  = new ObservableField<>();
    public final ObservableField<String> smallSanitation  = new ObservableField<>();
    public final ObservableField<String> mediumSanitation  = new ObservableField<>();
    public final ObservableField<String> bigSanitation  = new ObservableField<>();
    public final ObservableField<String> smallLittleRepairs  = new ObservableField<>();
    public final ObservableField<String> mediumLittleRepairs  = new ObservableField<>();
    public final ObservableField<String> bigLittleRepairs  = new ObservableField<>();

    private DbFacade facade = new DataFacade();

    private PriceTable priceTable;

    public PricesViewModelImpl() {
        this.priceTable = facade.fetchPriceTable();

        populateFields();
    }

    private void populateFields() {
        smallSimpleWash.set(String.valueOf(priceTable.getSimpleSmallCar()));
        mediumSimpleWash.set(String.valueOf(priceTable.getSimpleMediumCar()));
        bigSimpleWash.set(String.valueOf(priceTable.getSimpleBigCar()));

        taxi.set(String.valueOf(priceTable.getTaxi()));
        uber.set(String.valueOf(priceTable.getUber()));

        additionalWax.set(String.valueOf(priceTable.getAdditionalWax()));
        additionalResin.set(String.valueOf(priceTable.getAdditionalResin()));
        additionalResinBig.set(String.valueOf(priceTable.getAdditionalBigCarResin()));

        smallPolishing.set(String.valueOf(priceTable.getSmallPolishing()));
        mediumPolishing.set(String.valueOf(priceTable.getMediumPolishing()));
        bigPolishing.set(String.valueOf(priceTable.getBigPolishing()));

        smallSanitation.set(String.valueOf(priceTable.getSmallSanitation()));
        mediumSanitation.set(String.valueOf(priceTable.getMediumSanitation()));
        bigSanitation.set(String.valueOf(priceTable.getBigSanitation()));

        smallLittleRepairs.set(String.valueOf(priceTable.getSmallLittleRepairs()));
        mediumLittleRepairs.set(String.valueOf(priceTable.getMediumLittleRepairs()));
        bigLittleRepairs.set(String.valueOf(priceTable.getBigLittleRepairs()));
    }

    @Override
    public void onUpdateClicked() {
        updateFields();

        facade.savePriceTable(priceTable);
    }

    private void updateFields() {
        priceTable.setSimpleSmallCar(toInt(smallSimpleWash.get()));
        priceTable.setSimpleMediumCar(toInt(mediumSimpleWash.get()));
        priceTable.setSimpleBigCar(toInt(bigSimpleWash.get()));

        priceTable.setTaxi(toInt(taxi.get()));
        priceTable.setUber(toInt(uber.get()));

        priceTable.setAdditionalWax(toInt(additionalWax.get()));
        priceTable.setAdditionalResin(toInt(additionalResin.get()));
        priceTable.setAdditionalBigCarResin(toInt(additionalResinBig.get()));

        priceTable.setSmallPolishing(toInt(smallPolishing.get()));
        priceTable.setMediumPolishing(toInt(mediumPolishing.get()));
        priceTable.setBigPolishing(toInt(bigPolishing.get()));

        priceTable.setSmallSanitation(toInt(smallSanitation.get()));
        priceTable.setMediumSanitation(toInt(mediumSanitation.get()));
        priceTable.setBigSanitation(toInt(bigSanitation.get()));

        priceTable.setSmallLittleRepairs(toInt(smallLittleRepairs.get()));
        priceTable.setMediumLittleRepairs(toInt(mediumLittleRepairs.get()));
        priceTable.setBigLittleRepairs(toInt(bigLittleRepairs.get()));
    }

    private int toInt(String value) {
        return TextUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
    }
}
