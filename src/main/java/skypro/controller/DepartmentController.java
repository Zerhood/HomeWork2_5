package skypro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.dto.Employee;
import skypro.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmpMaxSalaryInDepartment(@RequestParam(value = "departmentId", required = false) int department) {
        return departmentService.getEmpMaxSalaryInDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmpMinSalaryInDepartment(@RequestParam(value = "departmentId", required = false) int department) {
        return departmentService.getEmpMinSalaryInDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllEmpByDepartment() {
        return departmentService.getAllEmpByDepartment();
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public List<Employee> getNameEmployeesInDepartment(@RequestParam(value = "departmentId", required = false, defaultValue = "0") Integer department) {
        return departmentService.getNameEmployeesInDepartment(department);
    }
}