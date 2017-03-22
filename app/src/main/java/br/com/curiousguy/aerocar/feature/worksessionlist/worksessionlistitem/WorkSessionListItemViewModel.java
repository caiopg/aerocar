package br.com.curiousguy.aerocar.feature.worksessionlist.worksessionlistitem;

public interface WorkSessionListItemViewModel {

    void populateItem();

    void onItemClicked();

    boolean onLongItemClicked();
}
