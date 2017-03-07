package br.com.curiousguy.aerocar.feature.closeworksession;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;
import br.com.curiousguy.aerocar.util.Pricer;

public class CloseWorkSessionViewModelImpl implements CloseWorkSessionViewModel {

    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> clientName = new ObservableField<>();
    public final ObservableField<String> clientTel = new ObservableField<>();
    public final ObservableField<String> entry = new ObservableField<>();
    public final ObservableField<String> carType = new ObservableField<>();
    public final ObservableField<String> wash = new ObservableField<>();
    public final ObservableField<String> service = new ObservableField<>();
    public final ObservableField<String> washPrice = new ObservableField<>();
    public final ObservableField<String> servicePrice = new ObservableField<>();
    public final ObservableField<String> totalPrice = new ObservableField<>();

    public final ObservableInt clientNameVisibility = new ObservableInt();
    public final ObservableInt clientTelVisibility = new ObservableInt();
    public final ObservableInt washVisibility = new ObservableInt();
    public final ObservableInt serviceVisibility = new ObservableInt();

    private final static NumberFormat real = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

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

        populateTotal();
    }

    private void populateTotal() {
        totalPrice.set(real.format(Pricer.price(workSession)));
    }

    private void populateService() {
        String unformattedService = context.getString(R.string.close_work_session_values_service);
        String formattedService = String.format(unformattedService, workSession.getService().getName());
        service.set(formattedService);

        String serviceInReal = real.format(workSession.getService().getPrice(workSession.getCar().getType()));
        servicePrice.set(serviceInReal);
    }

    private void populateWash() {
        String unformattedWash = context.getString(R.string.close_work_session_values_wash);
        String formattedWash = String.format(unformattedWash, workSession.getWash().getName());
        wash.set(formattedWash);

        String washInReal = real.format(workSession.getWash().getPrice(workSession.getCar().getType()));
        washPrice.set(washInReal);

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
