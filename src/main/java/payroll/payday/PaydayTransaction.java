package payroll.payday;

import lombok.Builder;
import payroll.Transaction;
import payroll.database.PayrollDatabase;
import payroll.entity.Employee;
import payroll.entity.PayCheck;

import java.util.*;

@Builder
public class PaydayTransaction implements Transaction {

    private Calendar itsPayDate;
    private Map<Integer, PayCheck> itsPayChecks = new HashMap<Integer, PayCheck>();

    @Override
    public void execute() throws Exception {
        ArrayList<Integer> empIds = new ArrayList<Integer>();
        PayrollDatabase database = new PayrollDatabase();
        database.getAllEmployeeIds(empIds);

        Employee e;
        for (Integer i : empIds) {
            if ((e = database.getEmployee(i)) != null) {
                if (e.isPayDate(itsPayDate)) {
                    PayCheck pc = new PayCheck(e.getPayPeriodStartDate(itsPayDate), itsPayDate);
                    itsPayChecks.put(i, pc);
                    e.payDay(pc);
                }
            }
        }

    }

    public PayCheck getPayCheck(int empId) {
        return itsPayChecks.get(empId);
    }
}
