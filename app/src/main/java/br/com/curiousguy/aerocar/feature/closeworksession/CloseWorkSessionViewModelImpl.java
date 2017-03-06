package br.com.curiousguy.aerocar.feature.closeworksession;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.text.SimpleDateFormat;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;

public class CloseWorkSessionViewModelImpl implements CloseWorkSessionViewModel {

    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> clientName = new ObservableField<>();
    public final ObservableField<String> clientTel = new ObservableField<>();
    public final ObservableField<String> specialCar = new ObservableField<>();
    public final ObservableField<String> entry = new ObservableField<>();
    public final ObservableField<String> carType = new ObservableField<>();
    public final ObservableField<String> wash = new ObservableField<>();
    public final ObservableField<String> service = new ObservableField<>();
    public final ObservableField<String> carTypePrice = new ObservableField<>();
    public final ObservableField<String> washPrice = new ObservableField<>();
    public final ObservableField<String> servicePrice = new ObservableField<>();

    public final ObservableInt clientNameVisibility = new ObservableInt();
    public final ObservableInt clientTelVisibility = new ObservableInt();
    public final ObservableInt specialCarVisibility = new ObservableInt();
    public final ObservableInt washVisibility = new ObservableInt();
    public final ObservableInt serviceVisibility = new ObservableInt();

    private Context context;
    private WorkSession workSession;

    public CloseWorkSessionViewModelImpl(Context context, WorkSession workSession) {
        this.context = context;
        this.workSession = workSession;

        populateScreen();
    }

    @Override
    public void populateScreen() {
        Car car = workSession.getCar();
        populateData(car);
        populateValues(car);
    }

    private void populateValues(Car car) {
        populateCarType(car);

        //todo populate prices

        if(workSession.hasWash()) {
            washVisibility.set(View.VISIBLE);
            populateWash();
        } else {
            washVisibility.set(View.GONE);
        }

        if(workSession.hasService()) {
            serviceVisibility.set(View.VISIBLE);
            populateService();
        } else {
            serviceVisibility.set(View.GONE);
        }
    }

    private void populateService() {
        String unformattedService = context.getString(R.string.close_work_session_values_service);
        String formattedService = String.format(unformattedService, workSession.getService().getName());
        service.set(formattedService);
    }

    private void populateWash() {
        String unformattedWash = context.getString(R.string.close_work_session_values_wash);
        String formattedWash = String.format(unformattedWash, workSession.getWash().getName());
        wash.set(formattedWash);
    }

    private void populateCarType(Car car) {
        String unformattedCarType = context.getString(R.string.close_work_session_values_car_type);
        String formattedCarType = String.format(unformattedCarType, car.getType().getName());
        carType.set(formattedCarType);
    }

    private void populateData(Car car) {
        populatePlate(car);

        if(car.hasClient()) {
            Client client = car.getClient();

            if(client.hasName()) {
                clientNameVisibility.set(View.VISIBLE);
                populateName(client);
            } else {
                clientNameVisibility.set(View.GONE);
            }

            if(client.hasTelephone()) {
                clientTelVisibility.set(View.VISIBLE);
                populateTel(client);
            } else {
                clientTelVisibility.set(View.GONE);
            }
        }

        if(car.isSpecialCar()) {
            specialCarVisibility.set(View.VISIBLE);
            specialCar.set(car.getType().getName());
        } else {
            specialCarVisibility.set(View.GONE);
        }

        populateEntry();
    }

    private void populateEntry() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - kk:mm");

        String unformattedDate = context.getString(R.string.close_work_session_data_entry);
        String formattedDate = String.format(unformattedDate, format.format(workSession.getEntry()));
        entry.set(formattedDate);
    }

    private void populateTel(Client client) {
        String unformattedTel = context.getString(R.string.close_work_session_data_telephone);
        String formattedTel = String.format(unformattedTel, client.getTelephone());
        clientTel.set(formattedTel);
    }

    private void populateName(Client client) {
        String unformattedName = context.getString(R.string.close_work_session_data_name);
        String formattedName = String.format(unformattedName, client.getName());
        clientName.set(formattedName);
    }

    private void populatePlate(Car car) {
        String unformattedplate = context.getString(R.string.close_work_session_data_plate);
        String formattedplate = String.format(unformattedplate, car.getPlate());
        plate.set(formattedplate);
    }
}
