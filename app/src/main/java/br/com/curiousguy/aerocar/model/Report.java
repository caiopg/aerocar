package br.com.curiousguy.aerocar.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Report {

    private Date createdIn;

    private String path;

    private File file;

    public String getFormattedCreatedIn() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy -HH:mm");

        return format.format(this.createdIn);
    }

}
