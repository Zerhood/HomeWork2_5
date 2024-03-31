package skypro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import skypro.dto.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@DisplayName("тестируем сервис департамента")
public class DepartmentServiceTest {
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private List<Employee> testList;
    private Employee a1;
    private Employee a2;
    private Employee b1;
    private int departmentIdOne = 1;
    private int departmentIdTwo = 2;

    @BeforeEach
    public void setUp() {
        employeeService = mock(EmployeeService.class);
        departmentService = new DepartmentServiceImp(employeeService);

        testList = new ArrayList<>();
        a1 = new Employee();
        a1.setFirstName("a1");
        a1.setLastName("a1");
        a1.setDepartment(departmentIdOne);
        a1.setSalary(1_000);

        a2 = new Employee();
        a2.setFirstName("a2");
        a2.setLastName("a2");
        a2.setDepartment(departmentIdOne);
        a2.setSalary(2_000);

        b1 = new Employee();
        b1.setFirstName("b1");
        b1.setLastName("b1");
        b1.setDepartment(departmentIdTwo);
        b1.setSalary(3_000);

        testList.add(a1);
        testList.add(a2);
        testList.add(b1);
    }

    @Test
    @DisplayName("получаем сотрудника с максимальной зарплатой в департаменте")
    public void testGetEmpMaxSalaryInDepartment() {
        doReturn(testList).when(employeeService).getAllEmployees();
        Employee expected = departmentService.getEmpMaxSalaryInDepartment(departmentIdOne);

        assertEquals(expected, a2);
    }

    @Test
    @DisplayName("получаем сотрудника с минимальной зарплатой в департаменте")
    public void testGetEmpMinSalaryInDepartment() {
        doReturn(testList).when(employeeService).getAllEmployees();
        Employee expected = departmentService.getEmpMinSalaryInDepartment(departmentIdOne);

        assertEquals(expected, a1);
    }

    @Test
    @DisplayName("получаем список сотрудников департамента")
    public void testGetNameEmployeesInDepartment() {
        List<Employee> actual = new ArrayList<>(
                List.of(a1,a2)
        );
        doReturn(testList).when(employeeService).getAllEmployees();
        List<Employee> expected = departmentService.getNameEmployeesInDepartment(departmentIdOne);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получаем списки сотрудников по всем департаментам")
    public void testGetAllEmpByDepartment() {
        Map<Integer, List<Employee>> actual = new HashMap<>();
        actual.put(departmentIdOne, List.of(a1,a2));
        actual.put(departmentIdTwo, List.of(b1));

        doReturn(testList).when(employeeService).getAllEmployees();
        Map<Integer, List<Employee>> expected = departmentService.getAllEmpByDepartment();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получаем сумму зарплат выбранного департамента")
    public void testGetSumSalaryInDepartment() {
        int actual = a1.getSalary() + a2.getSalary();

        doReturn(testList).when(employeeService).getAllEmployees();
        int expected = departmentService.getSumSalaryInDepartment(departmentIdOne);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получаем максимальную зарплату выбранного департамента")
    public void testGetMaxSalaryInDepartment() {
        int actual = a2.getSalary();

        doReturn(testList).when(employeeService).getAllEmployees();
        int expected = departmentService.getMaxSalaryInDepartment(departmentIdOne);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получаем минимальную зарплату выбранного департамента")
    public void testGetMinSalaryInDepartment() {
        int actual = a1.getSalary();

        doReturn(testList).when(employeeService).getAllEmployees();
        int expected = departmentService.getMinSalaryInDepartment(departmentIdOne);

        assertEquals(expected, actual);
    }

    /****************************************************************/

    @Test
    @DisplayName("получаем максимальную зарплату несуществующего департамента")
    public void testGetMaxSalaryInNotDepartment() {
        int actual = 0;

        doReturn(testList).when(employeeService).getAllEmployees();
        int expected = departmentService.getMaxSalaryInDepartment(3);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("получаем сумму зарплат несуществующего департамента")
    public void testGetSumSalaryNotDepartment() {
        int actual = 0;

        doReturn(testList).when(employeeService).getAllEmployees();
        int expected = departmentService.getSumSalaryInDepartment(3);

        assertEquals(expected, actual);
    }
}