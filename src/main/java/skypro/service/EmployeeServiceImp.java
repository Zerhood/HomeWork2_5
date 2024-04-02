package skypro.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.dto.Employee;
import skypro.exceptions.EmployeeAlreadyAddedException;
import skypro.exceptions.EmployeeNonMatchException;
import skypro.exceptions.EmployeeNotFoundException;
import skypro.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final int maxCountEmployee = 20;

    private List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) {
        if (employees.size() >= maxCountEmployee) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }

        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeNonMatchException("Фамилия или имя не соответствует формату данных!");
        }

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(department);
        employee.setSalary(salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("добавляемый сотрудник уже имеется");
        } else {
            employees.add(employee);
        }
        return employees.stream().filter(s -> s.equals(employee)).findFirst().orElse(null);
    }

    public Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee expected = employees.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .filter(s -> s.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
        if (expected == null) {
            throw new EmployeeNotFoundException("сотрудник не найден");
        }
        employees.remove(expected);
        return expected;
    }

    public Employee searchEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee expected = employees.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .filter(s -> s.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
        if (expected == null) {
            throw new EmployeeNotFoundException("сотрудник не найден");
        }
        return expected;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
}