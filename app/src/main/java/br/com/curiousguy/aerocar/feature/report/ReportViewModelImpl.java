package br.com.curiousguy.aerocar.feature.report;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.HawkKey;
import br.com.curiousguy.aerocar.util.ReportBuilder;
import br.com.curiousguy.aerocar.util.Validator;

public class ReportViewModelImpl implements ReportViewModel {

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> otherDateStart = new ObservableField<>();
    public final ObservableField<String> otherDateEnd = new ObservableField<>();

    public final ObservableInt otherDateVisibility = new ObservableInt(View.GONE);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Context context;
    private Date start = new Date();
    private Date end = new Date();

    public ReportViewModelImpl(Context context) {
        this.context = context;

        populateFields();
    }

    @Override
    public void onDateOptionChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.report_date_today:
                Date today = new Date();

                start = today;
                end = today;

                otherDateVisibility.set(View.GONE);
                break;
            case R.id.report_date_other:
                otherDateVisibility.set(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onStartDateClicked() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar startCal = Calendar.getInstance();
                        setCalendar(year, month, dayOfMonth, startCal);

                        start = startCal.getTime();
                        otherDateStart.set(dateFormat.format(start));
                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onEndDateClicked() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar endCal = Calendar.getInstance();
                        setCalendar(year, month, dayOfMonth, endCal);

                        end = endCal.getTime();
                        otherDateEnd.set(dateFormat.format(end));
                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onCreateClicked() {
        Date initialDate = startOfDay(start);
        Date finalDate = endOfDay(end);

        String emailValue = email.get();
        if(TextUtils.isEmpty(emailValue) || !Validator.isValidEmailAddress(emailValue)) {
            String title = context.getString(R.string.report_error_invalid_email_title);
            String content = context.getString(R.string.report_error_invalid_email_content);

            showError(title, content);
            return;
        }


        if(start.after(end)) {
            String title = context.getString(R.string.report_error_invalid_date_title);
            String content = context.getString(R.string.report_error_invalid_date_content);

            showError(title, content);
            return;
        }

        Hawk.put(HawkKey.RECIPIENT_EMAIL.getKey(), emailValue);

        ReportBuilder builder = new ReportBuilder(context, initialDate, finalDate);
        builder.build();

    }

    private void populateFields() {
        email.set(Hawk.get(HawkKey.RECIPIENT_EMAIL.getKey(), ""));
    }

    private void showError(String title, String content) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setNeutralButton(R.string.new_work_session_error_ok, null)
                .show();
    }

    private Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    private Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

    private void setCalendar(int year, int month, int dayOfMonth, Calendar startCal) {
        startCal.set(Calendar.YEAR, year);
        startCal.set(Calendar.MONTH, month);
        startCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }
}
