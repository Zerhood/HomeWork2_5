package skypro.controller;

import org.springframework.web.bind.annotation.*;
import skypro.dto.Employee;
import skypro.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController2 {
    private DepartmentService departmentService;

    public DepartmentController2(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getNameEmployeesInDepartment(@PathVariable int id) {
        return departmentService.getNameEmployeesInDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSumSalaryInDepartment(@PathVariable int id) {
        return departmentService.getSumSalaryInDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public Integer getEmpMaxSalaryInDepartment(@PathVariable int id) {
        return departmentService.getMaxSalaryInDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public Integer getMinSalaryInDepartment(@PathVariable int id) {
        return departmentService.getMinSalaryInDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getMinSalaryInDepartment() {
        return departmentService.getAllEmpByDepartment();
    }
}