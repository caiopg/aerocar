package br.com.curiousguy.aerocar.feature.report;

import android.widget.RadioGroup;

public interface ReportViewModel {

    void onDateOptionChanged(RadioGroup radioGroup, int checkedId);

    void onStartDateClicked();

    void onEndDateClicked();

    void onCreateClicked();
}
