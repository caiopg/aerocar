package br.com.curiousguy.aerocar.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.model.WorkSession;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lombok.Getter;

public class ReportBuilder {

    private static final String SUFFIX = ".xls";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("ddMMyyyy_HHmmss");
    private static final int INITIAL_ITEM = 1;

    private Context context;
    private Date start;
    private Date end;
    private List<WorkSession> workSessions;

    public ReportBuilder(Context context, Date start, Date end) {
        this.context = context;
        this.start = start;
        this.end = end;

        DbFacade facade = new RealmFacade();
        this.workSessions = facade.fetchInactiveWorkSessions(start, end);
    }

    public File build() {
        File reportFile = null;
        try {
            File reportFolder = createReportFolder();
            reportFile = createReportFile(reportFolder);
            populateExcel(reportFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return reportFile;
    }

    @NonNull
    private File createReportFile(File reportFolder) throws IOException {
        File reportFile = new File(reportFolder, createFileTitle());

        reportFile.createNewFile();
        reportFile.setReadable(true);
        reportFile.setWritable(true);

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
    private void populateExcel(File file) throws IOException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        workbook.createSheet("Resultado", 0);
        WritableSheet sheet = workbook.getSheet(0);

        WritableCellFormat cellFormat = createCellFormat();
        createTableTitles(cellFormat, sheet);

        int row = Cell.getContentAnchor();
        int item = INITIAL_ITEM;
        for(WorkSession workSession : workSessions) {
            // TODO: 09/03/17 continue populating

            Label label;

            label = new Label(Cell.NUMBER.getColumn(), row, String.valueOf(item), cellFormat);
            sheet.addCell(label);
            label = new Label(Cell.PLATE.getColumn(), row, workSession.getCar().getPlate(), cellFormat);
            sheet.addCell(label);
            label = new Label(Cell.TIP.getColumn(), row, workSession.getTip(), cellFormat);
            sheet.addCell(label);
            label = new Label(Cell.ENTRY.getColumn(), row, workSession.getEntry().toString(), cellFormat);
            sheet.addCell(label);

            item++;
            row++;
        }

        workbook.write();
        workbook.close();
    }

    @NonNull
    private WritableCellFormat createCellFormat() throws WriteException {
        WritableFont times16pt = new WritableFont(WritableFont.TIMES, 16);
        WritableCellFormat cellFormat = new WritableCellFormat(times16pt);
        return cellFormat;
    }

    private void createTableTitles(CellFormat cellFormat, WritableSheet sheet) throws WriteException {

        for(Cell cell : Cell.values()) {
            int row = cell.getRow();
            int column = cell.getColumn();
            String name = cell.getName();

            Label label = new Label(column, row, name, cellFormat);
            sheet.addCell(label);
        }
    }

    @NonNull
    private String createFileTitle() {
        String fileTitle = "Recebimento_" + DATE_FORMAT.format(new Date()) + SUFFIX;

        return fileTitle;
    }

    private enum Cell {
        NUMBER(1, 1, "No."),
        VEHICLE(1, 2, "Veículo"),
        PLATE(1, 3, "Placa"),
        SERVICE(1, 4, "Serviço"),
        VALUE(1, 5, "Valor"),
        DEBIT(1, 6, "Débito"),
        CREDIT(1, 7, "Crédito"),
        MONEY(1, 8, "Dinheiro"),
        TIP(1, 9, "Gorjeta"),
        ENTRY(1, 10, "Entrada"),
        EXIT(1, 11, "Saída");

        @Getter
        private int row;

        @Getter
        private int column;

        @Getter
        private String name;

        Cell(int row, int column, String name) {
            this.row = row;
            this.column = column;
            this.name = name;
        }

        public static int getContentAnchor() {
            return EXIT.getRow() + 1;
        }
    }
}
