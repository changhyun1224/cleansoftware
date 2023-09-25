package payroll.classification;

public class SalariedClassification extends PaymentClassification {

    private final double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

}
