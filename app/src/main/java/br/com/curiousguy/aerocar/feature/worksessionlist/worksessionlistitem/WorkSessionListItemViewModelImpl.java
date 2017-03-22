package br.com.curiousguy.aerocar.feature.worksessionlist.worksessionlistitem;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v4.content.ContextCompat;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.RequestCode;
import br.com.curiousguy.aerocar.feature.closeworksession.CloseWorkSessionActivity;
import br.com.curiousguy.aerocar.feature.newworksession.NewWorkSessionActivity;
import br.com.curiousguy.aerocar.model.WorkSession;
import br.com.curiousguy.aerocar.util.Pricer;

public class WorkSessionListItemViewModelImpl implements WorkSessionListItemViewModel {

    public final ObservableField<String> paymentStatus = new ObservableField<>();
    public final ObservableField<Integer> paymentStatusColor = new ObservableField<>();
    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> price = new ObservableField<>();

    private WorkSession workSession;
    private Activity activity;

    public WorkSessionListItemViewModelImpl(Activity activity, WorkSession workSession) {
        this.activity = activity;
        this.workSession = workSession;

        populateItem();
    }

    @Override
    public void populateItem() {
        populatePaymentStatus();
        populatePlate();
        populatePrice();

    }

    @Override
    public void onItemClicked() {
        Intent intent = CloseWorkSessionActivity.getStartIntent(activity, workSession);
        activity.startActivityForResult(intent, RequestCode.CLOSE_WORK_SESSION.getRequestCode());
    }

    @Override
    public boolean onLongItemClicked() {
        Intent intent = NewWorkSessionActivity.getStartIntent(activity, workSession);
        activity.startActivityForResult(intent, RequestCode.NEW_WORK_SESSIO.getRequestCode());

        return true;
    }

    private void populatePrice() {
        NumberFormat real = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        String priceInReal = real.format(Pricer.price(workSession));
        String unformattedPrice = activity.getString(R.string.item_car_list_price);
        String formattedPrice = String.format(unformattedPrice, priceInReal);

        price.set(formattedPrice);
    }

    private void populatePlate() {
        String plateText = String.format(activity.getString(R.string.item_car_list_plate),
                workSession.getCar().getPlate());

        plate.set(plateText);
    }

    private void populatePaymentStatus() {
        String paymentStatusText = workSession.isPayed() ?
                activity.getString(R.string.item_car_list_payed) :
                activity.getString(R.string.item_car_list_not_payed);

        paymentStatus.set(paymentStatusText);

        int colorId = workSession.isPayed() ? R.color.green : R.color.red;
        paymentStatusColor.set(ContextCompat.getColor(activity, colorId));
    }
}
