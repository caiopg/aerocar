package br.com.curiousguy.aerocar.model;

import org.parceler.Parcel;

import br.com.curiousguy.aerocar.enums.PaymentType;
import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;

@Parcel
public class Payment extends RealmObject {

    String paymentType;

    @Getter @Setter
    String value;

    public PaymentType getPaymentType() {
        return PaymentType.valueOf(this.paymentType);
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = String.valueOf(paymentType);
    }

    public int getIntValue() {
        return Integer.valueOf(this.value);
    }
}
