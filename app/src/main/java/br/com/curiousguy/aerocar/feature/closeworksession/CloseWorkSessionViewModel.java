package br.com.curiousguy.aerocar.feature.closeworksession;

import android.widget.RadioGroup;

public interface CloseWorkSessionViewModel {

    void populateScreen();

    void onPaymentTypeChanged (RadioGroup radioGroup, int checkedId);

    void onTipTextChanged(CharSequence s, int start, int before, int count);

    void onPayedClicked();

    void onPayedAndRetreviedClicked();
}
