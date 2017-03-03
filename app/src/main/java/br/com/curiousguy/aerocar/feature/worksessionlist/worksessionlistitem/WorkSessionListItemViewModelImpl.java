package br.com.curiousguy.aerocar.feature.worksessionlist.worksessionlistitem;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v4.content.ContextCompat;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.model.WorkSession;
import br.com.curiousguy.aerocar.util.Pricer;

public class WorkSessionListItemViewModelImpl implements WorkSessionListItemViewModel {

    public final ObservableField<String> paymentStatus = new ObservableField<>();
    public final ObservableField<Integer> paymentStatusColor = new ObservableField<>();
    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> price = new ObservableField<>();

    private WorkSession workSession;
    private Context context;

    public WorkSessionListItemViewModelImpl(Context context, WorkSession workSession) {
        this.context = context;
        this.workSession = workSession;

        populateItem();
    }

    @Override
    public void populateItem() {
        populatePaymentStatus();
        populatePlate();
        populatePrice();

    }

    private void populatePrice() {
        NumberFormat real = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        String priceInReal = real.format(Pricer.price(workSession));
        String unformattedPrice = context.getString(R.string.item_car_list_price);
        String formattedPrice = String.format(unformattedPrice, priceInReal);

        price.set(formattedPrice);
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
        paymentStatusColor.set(ContextCompat.getColor(context, colorId));
    }
}
