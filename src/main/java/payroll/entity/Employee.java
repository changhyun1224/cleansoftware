package payroll.entity;

import lombok.Getter;
import lombok.Setter;
import payroll.affiliation.Affiliation;
import payroll.classification.PaymentClassification;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;

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

    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

}
