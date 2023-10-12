package payroll.classification;

import payroll.entity.PayCheck;
import payroll.entity.SalesReceipt;

import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double salary;
    private double rate;
    private Map<Integer, SalesReceipt> salesReceipts = new HashMap<>();

    public CommissionedClassification(double salary, double rate) {
        this.salary = salary;
        this.rate = rate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.put(salesReceipt.getEmpId(), salesReceipt);
    }

    public SalesReceipt getSalesReceipt(int empId) {
        return salesReceipts.get(empId);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        return 0;
    }
}
