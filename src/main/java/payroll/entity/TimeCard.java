package payroll.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class TimeCard {

    private Long itsDate;
    private double itsHours;

    public TimeCard(Long itsDate, double itsHours) {
        this.itsDate = itsDate;
        this.itsHours = itsHours;
    }

}
