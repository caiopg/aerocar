package br.com.curiousguy.aerocar.feature.newcar;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.RadioGroup;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.WorkSession;

public class NewCarViewModelImpl implements NewCarViewModel {

    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> clientName = new ObservableField<>();
    public final ObservableField<String> clientTel = new ObservableField<>();
    public final ObservableField<String> uberRegistry = new ObservableField<>();

    private DbFacade facade = new RealmFacade();
    private Car car = new Car();
    private WorkSession workSession = new WorkSession();

    @Override
    public void onCarTypeChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_car_small_radiobutton:
                car.setType(Car.CarType.SMALL);
                break;
            case R.id.new_car_medium_radiobutton:
                car.setType(Car.CarType.MEDIUM);
                break;
            case R.id.new_car_big_radiobutton:
                car.setType(Car.CarType.BIG);
                break;
            case R.id.new_car_taxi_radiobutton:
                car.setType(Car.CarType.TAXI);
                break;
            case R.id.new_car_uber_radiobutton:
                car.setType(Car.CarType.UBER);
                break;
        }
    }

    @Override
    public void onWashChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_car_simple_radiobutton:
                workSession.setWash(WorkSession.Wash.SIMPLE);
                break;
            case R.id.new_car_wax_radiobutton:
                workSession.setWash(WorkSession.Wash.WAX);
                break;
            case R.id.new_car_resin_radiobutton:
                workSession.setWash(WorkSession.Wash.RESIN);
                break;
        }
    }

    @Override
    public void onServiceChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_car_sanitation_radiobutton:
                workSession.setService(WorkSession.Service.SANITATION);
                break;
            case R.id.new_car_polishing_radiobutton:
                workSession.setService(WorkSession.Service.POLISHING);
                break;
            case R.id.new_car_little_repairs_radiobutton:
                workSession.setService(WorkSession.Service.LITTLE_REPAIRS);
                break;
        }
    }

    @Override
    public void onPlateTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("CAIO", "plate: " + s);
    }

    @Override
    public void onOkClicked() {
        Log.d("CAIO", "onOkClicked: ");
    }

}
