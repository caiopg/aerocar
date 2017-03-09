package br.com.curiousguy.aerocar.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.WorkSession;
import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class ReportBuilder {

    public static final String SUFFIX = ".xls";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd_MM_yyyy");

    private Context context;
    private Date start;
    private Date end;

    public ReportBuilder(Context context, Date start, Date end) {
        this.context = context;
        this.start = start;
        this.end = end;
    }

    public File build() {
        DbFacade facade = new RealmFacade();
        List<WorkSession> WorkSessions = facade.fetchInactiveWorkSessions(start, end);

        File reportFile = createExcelFile();

        return reportFile;
    }

    private File createExcelFile() {
        File reportFolder = createReportFolder();
        File reportFile = createReportFile(reportFolder);

        return reportFile;
    }

    @NonNull
    private File createReportFile(File reportFolder) {
        File reportFile = new File(reportFolder, createFileTitle());
        try {
            reportFile.createNewFile();
            reportFile.setReadable(true);
            reportFile.setWritable(true);
        } catch (IOException e) {
            // TODO: 09/03/17 show error
            e.printStackTrace();
        }
        return reportFile;
    }

    @NonNull
    private File createReportFolder() {
        File sourceFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File reportFolder = new File(sourceFolder.getAbsolutePath(), "Relatorios - Aerocar");
        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }
        return reportFolder;
    }

    @NonNull
    private WritableWorkbook createExcel(File file) throws IOException {
        return Workbook.createWorkbook(file);
    }

    @NonNull
    private String createFileTitle() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startDay = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(end);
        int endDay = calendar.get(Calendar.DAY_OF_YEAR);

        String fileTitle = "Recebimento - " + DATE_FORMAT.format(startDay);
        if(startDay != endDay) {
            fileTitle.concat(" a " + DATE_FORMAT.format(endDay));
        }

        return fileTitle + SUFFIX;
    }
}
