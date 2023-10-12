package payroll.database;

import payroll.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PayrollDatabase {

    private static Map<Integer, Employee> itsEmployee = new HashMap<>();
    private static Map<Integer, Employee> itsUnionMember = new HashMap<>();

    public PayrollDatabase() {}

    public static Employee getEmployee(int empId) {
        return itsEmployee.get(empId);
    }

    public static void addEmployee(int empId, Employee employee) {
        itsEmployee.put(empId, employee);
    }

    public static Employee deleteEmployee(int empId) {
        return itsEmployee.remove(empId);
    }

    public static void clear() {
        itsEmployee.clear();
    }

    public static Employee getUnionMember(int memberId) {
        return itsUnionMember.get(memberId);
    }

    public static void addUnionMember(int memberId, Employee e) {
        itsUnionMember.put(memberId, e);
    }

    public static void removeUnionMember(int memberId) {
        itsUnionMember.remove(memberId);
    }

    public static Set<Integer> getAllEmployee() {
        return itsEmployee.keySet();
    }

    public static void getAllEmployeeIds(ArrayList<Integer> empIds) {
        empIds.addAll(itsEmployee.keySet());
    }
}
