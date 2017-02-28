package br.com.curiousguy.aerocar.feature.carlist.carlistitem;

import android.content.Context;
import android.databinding.ObservableField;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.model.WorkSession;

public class CarListItemViewModelImpl implements CarListItemViewModel {

    public final ObservableField<String> paymentStatus = new ObservableField<>();
    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<Integer> paymentStatusColor = new ObservableField<>();

    private WorkSession workSession;
    private Context context;

    public CarListItemViewModelImpl(Context context, WorkSession workSession) {
        this.context = context;
        this.workSession = workSession;

        populateItem();
    }

    @Override
    public void populateItem() {
        populatePaymentStatus();
        populatePlate();

    }

    private void populatePlate() {
        String plateText = String.format(context.getString(R.string.item_car_list_plate),
                workSession.getCar().getPlate());

        plate.set(plateText);
    }

    private void populatePaymentStatus() {
        String paymentStatusText = workSession.isPayed() ?
                context.getString(R.string.item_car_list_payed) :
                context.getString(R.string.item_car_list_not_payed);

        paymentStatus.set(paymentStatusText);

        int colorId = workSession.isPayed() ? R.color.green : R.color.red;

        paymentStatusColor.set(colorId);
    }
}
