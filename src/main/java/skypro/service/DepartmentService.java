package skypro.service;

import skypro.dto.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmpMaxSalaryInDepartment(int department);
    Employee getEmpMinSalaryInDepartment(int department);
    List<Employee> getNameEmployeesInDepartment(int department);
    Map<Integer, List<Employee>> getAllEmpByDepartment();
    Integer getSumSalaryInDepartment(int id);
    Integer getMaxSalaryInDepartment(int id);
    Integer getMinSalaryInDepartment(int id);
}