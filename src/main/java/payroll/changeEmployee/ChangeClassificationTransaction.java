package payroll.changeEmployee;

import payroll.classification.PaymentClassification;
import payroll.entity.Employee;
import payroll.schedule.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();

}
