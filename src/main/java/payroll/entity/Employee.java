package payroll.entity;

import lombok.Getter;
import lombok.Setter;
import payroll.affiliation.Affiliation;
import payroll.affiliation.UnionAffiliation;
import payroll.classification.PaymentClassification;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;

import java.util.Calendar;

@Getter
@Setter
public class Employee {
    private int empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;
    private UnionAffiliation unionAffiliation;

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    public void payDay(PayCheck pc) {
        double grossPay = classification.calculatePay(pc);
        double deductions = unionAffiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        method.pay(pc);
    }

    public boolean isPayDate(Calendar payDate) {
        return schedule.isPayDate(payDate);
    }

    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return schedule.getPayPeriodStartDate(payDate);
    }
}
