package br.com.curiousguy.aerocar.feature.report;

import android.support.annotation.NonNull;
import android.widget.RadioGroup;

public interface ReportViewModel {

    void onDateOptionChanged(RadioGroup radioGroup, int checkedId);

    void onStartDateClicked();

    void onEndDateClicked();

    void onCreateClicked();

    void onShareClicked();

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
