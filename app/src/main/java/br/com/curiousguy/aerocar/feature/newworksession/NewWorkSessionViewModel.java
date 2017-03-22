package br.com.curiousguy.aerocar.feature.newworksession;

import android.widget.RadioGroup;

public interface NewWorkSessionViewModel {

    void onOkClicked();

    void onCarTypeChanged(RadioGroup radioGroup, int checkedId);

    void onWashChanged(RadioGroup radioGroup, int checkedId);

    void onServiceChanged(RadioGroup radioGroup, int checkedId);

    void onPlateTextChanged(CharSequence s, int start, int before, int count);

    void onModelTextChanged(CharSequence s, int start, int before, int count);

    void onClientNameTextChanged(CharSequence s, int start, int before, int count);

    void onClientTelTextChanged(CharSequence s, int start, int before, int count);

    void onUberRegistryTextChanged(CharSequence s, int start, int before, int count);

    void clearServiceFields();

}
