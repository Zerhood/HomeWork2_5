package skypro.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import skypro.dto.Employee;
import skypro.exceptions.EmployeeAlreadyAddedException;
import skypro.exceptions.EmployeeNonMatchException;
import skypro.exceptions.EmployeeNotFoundException;
import skypro.exceptions.EmployeeStorageIsFullException;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("тестируем сервис сотрудников")
public class EmployeeServiceTest {
    private EmployeeService service;

    @BeforeEach
    public void setUp() {
        service = new EmployeeServiceImp();
    }

    @Test
    @DisplayName("добавляем сотрудника")
    public void testAddEmployee() {
        Employee a1 = new Employee();
        a1.setFirstName("Anton");
        a1.setLastName("Semenov");
        a1.setSalary(10_000);
        a1.setDepartment(1);

        Employee expected = service.addEmployee("Anton", "Semenov", 1, 10_000);

        assertEquals(expected, a1);
    }

    @Test
    @DisplayName("добавляем повторно сотрудника")
    public void testAddAgainEmployee() {
        service.addEmployee("Anton", "Semenov", 1, 10_000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.addEmployee("Anton", "Semenov", 1, 10_000));
    }

    @Test
    @DisplayName("добавляем неликвидного сотрудника")
    public void testAddWrongEmployee() {
        assertThrows(EmployeeNonMatchException.class, () -> service.addEmployee("Ant-on", "Semenov", 1, 10_000));
    }

    @Test
    @DisplayName("добавляем сотрудника при полном штате компании")
    public void testAddEmployeeFullCompany() {
        for (int i = 0; i < 20; i++) {
            String s = String.valueOf((char) new Random().nextInt(97, 123));
            int department = new Random().nextInt(5);
            service.addEmployee(s, s, department, new Random().nextInt());
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> service.addEmployee("Anton", "Semenov", 1, 10_000));
    }

    @Test
    @DisplayName("удаляем сотрудника")
    public void testRemoveEmployee() {
        service.addEmployee("Anton", "Semenov", 1, 10_000);
        service.deleteEmployee("Anton", "Semenov");
        List<Employee> expected = service.getAllEmployees();
        assertEquals(expected.size(), 0);
    }

    @Test
    @DisplayName("удаляем сотрудника которого нет")
    public void testRemoveAgainEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee("Anton", "Semenov"));
    }

    @Test
    @DisplayName("ищем сотрудника")
    public void testSearchEmployee() {
        Employee actual = service.addEmployee("Anton", "Semenov", 1, 10_000);
        Employee expected = service.searchEmployee("Anton", "Semenov");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ищем сотрудника которого нет")
    public void testNotFoundEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.searchEmployee("Anton", "Semenov"));
    }
}