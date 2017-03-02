package br.com.curiousguy.aerocar.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class Price extends RealmObject {

    @PrimaryKey
    int version = 0;

    int simpleSmallCar = 30;

    int simpleMediumCar = 35;

    int simpleBigCar = 40;

    int Taxi = 25;

    int Uber = 25;

    int sanitation = 25;

    int polishing = 25;

    int littleRepairs = 50;

    int additionalWax = 5;

    int additionalResin = 10;
}
