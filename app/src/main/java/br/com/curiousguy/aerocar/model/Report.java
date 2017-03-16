package br.com.curiousguy.aerocar.model;

import java.io.File;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Report {

    private Date createdIn;

    private String path;

    private File file;

}
