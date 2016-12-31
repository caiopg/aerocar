package br.com.curiousguy.aerocar.feature.newcar;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.RadioGroup;

public class NewCarViewModelImpl implements NewCarViewModel {

    public ObservableField<String> plate = new ObservableField<>();
    public ObservableField<String> clientName = new ObservableField<>();
    public ObservableField<String> clientTel = new ObservableField<>();
    public ObservableField<String> uberRegistry = new ObservableField<>();

    private Context context;

    public NewCarViewModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onOkClicked() {
        Log.d("CAIO", "plate: " + plate.get());
    }

    @Override
    public void onCarTypeChanged(RadioGroup radioGroup, int checkedId) {
        Log.d("CAIO", "onCarTypeChanged: ");
    }

    @Override
    public void onWashTypeChanged() {

    }

    @Override
    public void onServiceChanged() {

    }

}
