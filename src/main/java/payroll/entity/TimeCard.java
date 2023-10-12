package payroll.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class TimeCard {

    private Calendar itsDate;
    private double itsHours;

    public TimeCard(Calendar itsDate, double itsHours) {
        this.itsDate = itsDate;
        this.itsHours = itsHours;
    }

}
