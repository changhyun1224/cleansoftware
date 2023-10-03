package payroll.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCharge {

    private double charge;
    private long date;

    public ServiceCharge(long date, double charge) {
        this.charge = charge;
        this.date = date;
    }

}
