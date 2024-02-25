package skypro.service;

import org.springframework.stereotype.Service;
import skypro.dto.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService{

    private EmployeeService employeeService;

    public DepartmentServiceImp(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmpMaxSalaryInDepartment(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmpMinSalaryInDepartment(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getNameEmployeesInDepartment(int department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmpByDepartment() {
        return employeeService.getAllEmployees().stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }
}