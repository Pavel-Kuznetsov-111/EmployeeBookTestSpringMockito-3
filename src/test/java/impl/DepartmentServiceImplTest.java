package pro.sky.skyproEmployeeBookTestSpringMockito.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private EmployeeServiceImpl employeeService;

    private final Employee EMP = new Employee("Ivan", "Ivanov", 2000, 1);
    private final Employee EMP1 = new Employee("Petr", "Petrov", 2500, 1);
    private final Employee EMP2 = new Employee("Harry", "Potter", 3000, 2);
    private final Employee EMP3 = new Employee("Olga", "Simonova", 3500, 2);
    private final Employee EMP4 = new Employee("Anastasia", "Zvyagina", 5000, 3);
    private final Employee EMP5 = new Employee("Karina", "Smirnova", 5500, 3);

    @BeforeEach
    void setUp() {

        when(employeeService.getAll()).thenReturn(Arrays.asList(EMP, EMP1, EMP2, EMP3, EMP4, EMP5));

    }

    @Test
    void sumSalary() {

        assertEquals(6500, departmentService.sumSalary(2));

    }

    @Test
    void minSalary() {

        assertEquals(2000, departmentService.minSalary(1));

    }

    @Test
    void maxSalary() {

        assertEquals(5500, departmentService.maxSalary(3));

    }

    @Test
    void findByDepartment() {
        List<Employee> employees = departmentService.findByDepartment(2);

        assertEquals(2, employees.size());

        assertTrue(employees.containsAll(Arrays.asList(EMP2, EMP3)));
    }

    @Test
    void findAllByDep() {
        Map<Integer, List<Employee>> allByDep = departmentService.findAllByDep();

        assertEquals(3, allByDep.size());
        assertEquals(2, allByDep.get(2).size());

        assertTrue(allByDep.get(3).containsAll(Arrays.asList(EMP4, EMP5)));

    }
}
