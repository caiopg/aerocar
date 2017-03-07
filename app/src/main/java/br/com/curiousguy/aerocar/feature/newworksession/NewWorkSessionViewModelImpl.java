package br.com.curiousguy.aerocar.feature.newworksession;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.db.CarNotFoundException;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.enums.CarType;
import br.com.curiousguy.aerocar.enums.Service;
import br.com.curiousguy.aerocar.enums.Wash;
import br.com.curiousguy.aerocar.model.Car;
import br.com.curiousguy.aerocar.model.Client;
import br.com.curiousguy.aerocar.model.WorkSession;
import lombok.val;

public class NewWorkSessionViewModelImpl implements NewWorkSessionViewModel {

    public static final int SEARCH_DELAY_IN_MILLIS = 2000;
    public static final int TEXT_CHANGED_DELAY_IN_MILLIS = 250;

    public final ObservableField<String> plate = new ObservableField<>();
    public final ObservableField<String> clientName = new ObservableField<>();
    public final ObservableField<String> clientTel = new ObservableField<>();
    public final ObservableField<String> uberRegistry = new ObservableField<>();

    public final ObservableField<Boolean> isSmallChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isMediumChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isBigChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isTaxiChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isUberChecked = new ObservableField<>(false);

    public final ObservableField<Boolean> isWaxChecked = new ObservableField<>(false);

    public final ObservableField<Boolean> isSanitationChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isLittleRepairsChecked = new ObservableField<>(false);
    public final ObservableField<Boolean> isPolishingChecked = new ObservableField<>(false);

    public final ObservableInt simpleVisibility = new ObservableInt(View.VISIBLE);
    public final ObservableInt serviceVisibility = new ObservableInt(View.VISIBLE);
    public final ObservableInt uberRegisterVisibility = new ObservableInt(View.GONE);

    private DbFacade facade = new RealmFacade();
    Handler handler = new Handler();
    private Car car = new Car();
    private WorkSession workSession = WorkSession.build();
    private Client client = new Client();
    private Runnable searchCar = new Runnable() {
        @Override
        public void run() {
            try {
                car = facade.fetchCarCopyByPlate(plate.get().toUpperCase());
                if(car.getClient() != null) {
                    client = car.getClient();
                }

                updateFields();

                String carFound = context.getString(R.string.new_work_session_toast_car_found);
                showToast(carFound);
            } catch (CarNotFoundException e) {
                String carNotFound = context.getString(R.string.new_work_session_toast_car_not_found);
                showToast(carNotFound);
            } finally {
                plate.set(plate.get().toUpperCase());
                car.setPlate(plate.get().toUpperCase());
            }
        }
    };

    Context context;

    public NewWorkSessionViewModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onCarTypeChanged (RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_work_session_small_radiobutton:
                car.setType(CarType.SMALL);
                hideUberFields();
                enableSimple();
                enableService();
                break;
            case R.id.new_work_session_medium_radiobutton:
                car.setType(CarType.MEDIUM);
                hideUberFields();
                enableSimple();
                enableService();
                break;
            case R.id.new_work_session_big_radiobutton:
                car.setType(CarType.BIG);
                hideUberFields();
                checkWax();
                disableSimple();
                enableService();
                break;
            case R.id.new_work_session_taxi_radiobutton:
                car.setType(CarType.TAXI);
                hideUberFields();
                checkWax();
                disableSimple();
                disableService();
                break;
            case R.id.car_uber_radiobutton:
                car.setType(CarType.UBER);
                showUberFields();
                checkWax();
                disableSimple();
                disableService();
                break;
        }
    }

    private void disableSimple() {
        simpleVisibility.set(View.GONE);
    }

    private void disableService() {
        isLittleRepairsChecked.set(false);
        isPolishingChecked.set(false);
        isSanitationChecked.set(false);

        serviceVisibility.set(View.GONE);

        workSession.setService(null);
    }

    private void enableService() {
        serviceVisibility.set(View.VISIBLE);
    }

    private void enableSimple() {
        simpleVisibility.set(View.VISIBLE);
    }

    private void checkWax() {
        isWaxChecked.set(true);
    }

    @Override
    public void onWashChanged (RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_work_session_simple_radiobutton:
                workSession.setWash(Wash.SIMPLE);
                break;
            case R.id.new_work_session_wax_radiobutton:
                workSession.setWash(Wash.WAX);
                break;
            case R.id.new_work_session_resin_radiobutton:
                workSession.setWash(Wash.RESIN);
                break;
        }
    }

    @Override
    public void onServiceChanged (RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.new_work_session_sanitation_radiobutton:
                workSession.setService(Service.SANITATION);
                break;
            case R.id.new_work_session_polishing_radiobutton:
                workSession.setService(Service.POLISHING);
                break;
            case R.id.new_work_session_little_repairs_radiobutton:
                workSession.setService(Service.LITTLE_REPAIRS);
                break;
        }
    }

    @Override
    public void onPlateTextChanged (CharSequence s, int start, int before, int count) {
        handler.removeCallbacks(searchCar);
        handler.postDelayed(searchCar, SEARCH_DELAY_IN_MILLIS);
    }

    @Override
    public void onClientNameTextChanged(CharSequence s, int start, int before, int count) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                client.setName(clientName.get());
            }
        }, TEXT_CHANGED_DELAY_IN_MILLIS);
    }

    @Override
    public void onClientTelTextChanged(CharSequence s, int start, int before, int count) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                client.setTelephone(clientTel.get());
            }
        }, TEXT_CHANGED_DELAY_IN_MILLIS);
    }

    @Override
    public void onUberRegistryTextChanged(CharSequence s, int start, int before, int count) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                car.setUberRegistry(uberRegistry.get());
            }
        }, TEXT_CHANGED_DELAY_IN_MILLIS);
    }

    @Override
    public void onOkClicked() {
        if(verifyAndShowErrors()) {
            return;
        }

        persistData();
        returnToMain();
    }

    private void returnToMain() {
        Activity activity = (Activity) this.context;

        activity.setResult(Activity.RESULT_OK);
        activity.finish();
    }

    private void showUberFields() {
        uberRegisterVisibility.set(View.VISIBLE);
    }

    private void hideUberFields() {
        uberRegisterVisibility.set(View.GONE);
    }

    private void persistData() {
        car.setClient(client);
        facade.updateOrSave(car);

        workSession.setCar(car);
        workSession.setEntry(new Date());
        facade.updateOrSave(workSession);
    }

    private boolean verifyAndShowErrors() {
        val errorIds = car.getCreationErrorIdList();
        errorIds.addAll(workSession.getCreationErrorIdList());

        if(errorIds.size() > 0) {
            showErrors(errorIds);
            return true;
        }

        return false;
    }

    private void showErrors(List<Integer> errorIds) {
        String title = context.getString(R.string.new_work_session_error_title);
        String content = assembleErrorContent(errorIds);

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setNeutralButton(R.string.new_work_session_error_ok, null)
                .show();
    }

    private String assembleErrorContent(List<Integer> errorIds) {
        String content = context.getString(R.string.new_work_session_error_content);
        for(int errorId : errorIds) {
            content = content.concat(context.getString(errorId));
        }

        return content;
    }

    private void showToast(String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    private void updateClientFields(Client client) {
        if(!TextUtils.isEmpty(client.getName())) {
            clientName.set(client.getName());
        }

        if(!TextUtils.isEmpty(client.getTelephone())) {
            clientTel.set(client.getTelephone());
        }
    }

    private void updateFields() {
        if(car.getClient() != null) {
            updateClientFields(car.getClient());
        }

        if(car != null) {
            updateCarFields();
        }
    }

    private void updateCarFields() {
        if(car.getType() != null) {
            switch (car.getType()) {
                case SMALL:
                    isSmallChecked.set(true);
                    hideUberFields();
                    break;
                case MEDIUM:
                    isMediumChecked.set(true);
                    hideUberFields();
                    break;
                case BIG:
                    isBigChecked.set(true);
                    hideUberFields();
                    break;
                case TAXI:
                    isTaxiChecked.set(true);
                    hideUberFields();
                    break;
                case UBER:
                    isUberChecked.set(true);
                    showUberFields();

                    uberRegistry.set(car.getUberRegistry());
                    break;
            }
        }
    }
}
