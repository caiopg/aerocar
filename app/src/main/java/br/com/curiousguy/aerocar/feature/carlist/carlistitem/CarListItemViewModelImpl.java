package br.com.curiousguy.aerocar.feature.carlist.carlistitem;

import br.com.curiousguy.aerocar.model.Car;

public class CarListItemViewModelImpl implements CarListItemViewModel {

    private Car car;

    public CarListItemViewModelImpl(Car car) {
        this.car = car;
    }
}
