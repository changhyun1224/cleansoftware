package payroll.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class ServiceCharge {

    private double charge;
    private Calendar date;

    public ServiceCharge(Calendar date, double charge) {
        this.charge = charge;
        this.date = date;
    }

}
