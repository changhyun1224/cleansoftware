package payroll.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PayCheck {

    private double grossPay;
    private double deductions;
    private double netPay;

    private Calendar payPeriodStartDate;
    private Calendar payPeriodEndDate;
    private Map<String, String> fields = new HashMap<>();

    public PayCheck(Calendar payPeriodStartDate, Calendar payPeriodEndDate) {
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
    }

    public String getField(String key) {
        return fields.get(key);
    }
}
