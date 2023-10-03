package payroll.changeEmployee;

import payroll.affiliation.Affiliation;
import payroll.entity.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

    public ChangeAffiliationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void change(Employee e) throws Exception {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    abstract void recordMembership(Employee e) throws Exception;

    abstract Affiliation getAffiliation();

}
