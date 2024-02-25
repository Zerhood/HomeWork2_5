package skypro.service;

import skypro.dto.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getEmpMaxSalaryInDepartment(int department);
    Employee getEmpMinSalaryInDepartment(int department);
    List<Employee> getNameEmployeesInDepartment(int department);
    List<Employee> getAllEmpByDepartment();
}