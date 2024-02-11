package skypro.service;

import skypro.dto.Employee;
import skypro.exceptions.EmployeeAlreadyAddedException;
import skypro.exceptions.EmployeeStorageIsFullException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;
    Employee deleteEmployee(String firstName, String lastName);
    Employee searchEmployee(String firstName, String lastName);
    List<Employee> getAllEmployees();
}