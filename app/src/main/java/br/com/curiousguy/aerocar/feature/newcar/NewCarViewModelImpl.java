package br.com.curiousguy.aerocar.feature.newcar;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.db.CarNotFoundException;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;

public class NewCarViewModelImpl implements NewCarViewModel {

    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> clientName = new ObservableField<>();
    public final ObservableField<String> clientTel = new ObservableField<>();
    public final ObservableField<String> uberRegistry = new ObservableField<>();

    public final ObservableField<Boolean> isSmallChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isMediumChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isBigChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isTaxiChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isUberChecked = new ObservableField<>(false);

    private DbFacade facade = new RealmFacade();
    Handler handler = new Handler();
    private Car car = new Car();
    private WorkSession workSession = new WorkSession();
    private Client client = new Client();
    private Runnable searchCar = new Runnable() {
        @Override
        public void run() {
            String searchingForCar = context.getString(R.string.new_car_toast_search_for_car);
            showToast(searchingForCar);

            try {
                car = facade.fetchCarCopy(plate.get().toUpperCase());
                if(car.getClient() != null) {
                    client = car.getClient();
                }

                updateFields(car);
                String carFound = context.getString(R.string.new_car_toast_car_found);
                showToast(carFound);
            } catch (CarNotFoundException e) {
                String carNotFound = context.getString(R.string.new_car_toast_car_not_found);
                showToast(carNotFound);
            } finally {
                car.setPlate(plate.get());
            }
        }
    };

    Context context;

    public NewCarViewModelImpl (Context context) {
        this.context = context;
    }


    @Override
    public void onCarTypeChanged (RadioGroup radioGroup, int checkedId) {
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
            case R.id.car_uber_radiobutton:
                car.setType(Car.CarType.UBER);
                break;
        }
    }

    @Override
    public void onWashChanged (RadioGroup radioGroup, int checkedId) {
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
    public void onServiceChanged (RadioGroup radioGroup, int checkedId) {
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
    public void onPlateTextChanged (CharSequence s, int start, int before, int count) {
        handler.removeCallbacks(searchCar);
        handler.postDelayed(searchCar, 1000);
    }

    @Override
    public void onClientNameTextChanged(CharSequence s, int start, int before, int count) {
        client.setName(clientName.get());
    }

    @Override
    public void onClientTelTextChanged(CharSequence s, int start, int before, int count) {
        client.setTelefone(clientTel.get());
    }

    @Override
    public void onOkClicked() {
        //todo verify if everything valid
        //todo client not saving
        facade.updateOrSaveClient(client);

        car.setClient(client);
        facade.updateOrSaveCar(car);

        workSession.setCar(car);
        facade.updateOrSaveWorkSession(workSession);

    }

    private void showToast(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    private void updateClientFields(Client client) {
        if(!TextUtils.isEmpty(client.getName())) {
            clientName.set(client.getName());
        }

        if(!TextUtils.isEmpty(client.getTelefone())) {
            clientTel.set(client.getTelefone());
        }
    }

    private void updateFields(Car car) {
        if(car.getClient() != null) {
            updateClientFields(car.getClient());
        }

        if(car != null) {
            updateCarFields(car);
        }
    }

    private void updateCarFields(Car car) {
        if(car.getType() != null) {
            switch (car.getType()) {
                case SMALL:
                    isSmallChecked.set(true);
                    break;
                case MEDIUM:
                    isMediumChecked.set(true);
                    break;
                case BIG:
                    isBigChecked.set(true);
                    break;
                case TAXI:
                    isTaxiChecked.set(true);
                    break;
                case UBER:
                    isUberChecked.set(true);
                    break;
            }
        }
    }
}
