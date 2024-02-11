package skypro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.dto.Employee;
import skypro.exceptions.EmployeeAlreadyAddedException;
import skypro.exceptions.EmployeeStorageIsFullException;
import skypro.service.EmployeeServiceImp;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImp employeeService;

    public EmployeeController(EmployeeServiceImp employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName)
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.searchEmployee(firstName, lastName);
    }

    @GetMapping
    public List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }
}
