package br.com.curiousguy.aerocar.db;

public class CarNotFoundException extends Throwable {

    public CarNotFoundException(String message) {
        super(message);
    }
}
