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

    int smallSanitation = 30;

    int mediumSanitation = 35;

    int bigSanitation = 40;

    int smallPolishing = 30;

    int mediumPolishing = 35;

    int bigPolishing = 40;

    int smallLittleRepairs = 30;

    int mediumLittleRepairs = 35;

    int bigLittleRepairs = 40;

    int additionalWax = 5;

    int additionalResin = 10;
}
