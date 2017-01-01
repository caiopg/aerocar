package br.com.curiousguy.aerocar.feature.newcar;

import android.widget.RadioGroup;

public interface NewCarViewModel {

    void onOkClicked();

    void onCarTypeChanged(RadioGroup radioGroup, int checkedId);

    void onWashChanged(RadioGroup radioGroup, int checkedId);

    void onServiceChanged(RadioGroup radioGroup, int checkedId);

    void onPlateTextChanged(CharSequence s, int start, int before, int count);

    void onClientNameTextChanged(CharSequence s, int start, int before, int count);

    void onClientTelTextChanged(CharSequence s, int start, int before, int count);

}
