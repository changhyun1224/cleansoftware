package payroll.classification;

import payroll.entity.PayCheck;

public class SalariedClassification implements PaymentClassification {

    private double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(PayCheck pc) {
        return 0;
    }
}
