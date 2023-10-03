package payroll.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@Builder
public class SalesReceipt {
    private int empId;
    private long date;
    private int amount;

    public SalesReceipt(int empId, long date, int amount) {
        this.empId = empId;
        this.date = date;
        this.amount = amount;
    }

}
