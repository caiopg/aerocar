package br.com.curiousguy.aerocar.feature.carlist.carlistitem;

import br.com.curiousguy.aerocar.model.WorkSession;

public class CarListItemViewModelImpl implements CarListItemViewModel {

    private WorkSession workSession;

    public CarListItemViewModelImpl(WorkSession workSession) {
        this.workSession = workSession;
    }
}
