package br.com.curiousguy.aerocar.feature.report;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.db.DataFacade;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.model.Report;
import br.com.curiousguy.aerocar.util.ReportBuilder;
import br.com.curiousguy.aerocar.util.Validator;

public class ReportViewModelImpl implements ReportViewModel {

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> otherDateStart = new ObservableField<>();
    public final ObservableField<String> otherDateEnd = new ObservableField<>();
    public final ObservableField<String> lastReportCreated = new ObservableField<>();

    public final ObservableInt otherDateVisibility = new ObservableInt(View.GONE);

    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private DbFacade facade = new DataFacade();
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

        if(start.after(end)) {
            String title = context.getString(R.string.report_error_invalid_date_title);
            String content = context.getString(R.string.report_error_invalid_date_content);

            showError(title, content);
            return;
        }

        Report lastReport = buildReportAndSave(initialDate, finalDate);
        updateLastReportCreated(lastReport);

        showOpenReportDialog(lastReport);
    }

    private void updateLastReportCreated(Report lastReport) {
        if(lastReport != null) {
            String unformattedCreationDate = context.getString(R.string.report_last_report_created);
            lastReportCreated.set(String.format(unformattedCreationDate, lastReport.getFormattedCreatedIn()));
        }
    }

    private Report buildReportAndSave(Date initialDate, Date finalDate) {
        ReportBuilder builder = new ReportBuilder(initialDate, finalDate);
        builder.build();

        Report lastReport = builder.build();
        facade.saveLastReport(lastReport);

        return lastReport;
    }

    private void showOpenReportDialog(final Report lastReport) {
        new AlertDialog.Builder(context)
                .setMessage(R.string.report_open_dialog_message)
                .setPositiveButton(R.string.report_open_dialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openReport(lastReport.getPath());
                    }
                })
                .setNegativeButton(R.string.report_open_dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                })
                .show();
    }

    private void openReport(String path) {
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
        context.startActivity(intent);
    }

    @Override
    public void onShareClicked() {
        Report lastReport = facade.fetchLastReport();
        String recipient = facade.fetchRecipientEmail();

        String title;
        String content;
        if(!lastReport.getFile().exists()) {
            title = context.getString(R.string.report_error_invalid_file_title);
            content = context.getString(R.string.report_error_invalid_file_content);

            showError(title, content);
            return;
        }

        if(TextUtils.isEmpty(recipient) || !Validator.isValidEmailAddress(recipient)) {
            title = context.getString(R.string.report_error_invalid_email_title);
            content = context.getString(R.string.report_error_invalid_email_content);

            showError(title, content);
            return;
        }

        facade.saveRecipientEmail(email.get());

        Intent shareIntent = createShareIntent(lastReport, recipient);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.report_share_title)));

    }

    @NonNull
    private Intent createShareIntent(Report lastReport, String recipient) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.report_share_email_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.report_share_email_body));

        Uri uri = Uri.fromFile(lastReport.getFile());

        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return shareIntent;
    }

    private void populateFields() {
        Report lastReport = facade.fetchLastReport();
        updateLastReportCreated(lastReport);

        email.set(facade.fetchRecipientEmail());
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
