package br.com.curiousguy.aerocar.feature.newcar;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.RadioGroup;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.model.Car;

public class NewCarViewModelImpl implements NewCarViewModel {

    public ObservableField<String> plate = new ObservableField<>();
    public ObservableField<String> clientName = new ObservableField<>();
    public ObservableField<String> clientTel = new ObservableField<>();
    public ObservableField<String> uberRegistry = new ObservableField<>();

    private Car car = new Car();

    private Context context;

    public NewCarViewModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onOkClicked() {
        Log.d("CAIO", "car type: " + String.valueOf(car.getType()));
    }

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
    public void onWashTypeChanged(RadioGroup radioGroup, int checkedId) {

    }

    @Override
    public void onServiceChanged(RadioGroup radioGroup, int checkedId) {

    }
}
