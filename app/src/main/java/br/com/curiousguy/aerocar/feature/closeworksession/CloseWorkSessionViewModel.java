package br.com.curiousguy.aerocar.feature.closeworksession;

public interface CloseWorkSessionViewModel {

    void populateScreen();

    void onTipTextChanged(CharSequence s, int start, int before, int count);

    void onMoneyTextChanged(CharSequence s, int start, int before, int count);

    void onCreditTextChanged(CharSequence s, int start, int before, int count);

    void onDebitTextChanged(CharSequence s, int start, int before, int count);

    void onPayedClicked();

    void onPayedAndRetreviedClicked();
}
