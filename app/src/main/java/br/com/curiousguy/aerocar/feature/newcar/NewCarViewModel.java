package br.com.curiousguy.aerocar.feature.newcar;

import android.widget.RadioGroup;

public interface NewCarViewModel {

    void onOkClicked();

    void onCarTypeChanged(RadioGroup radioGroup, int checkedId);

    void onWashTypeChanged();

    void onServiceChanged();

}
