package payroll.changeEmployee;

import payroll.entity.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

    private String address;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public void change(Employee e) {
        e.setAddress(address);
    }

}
