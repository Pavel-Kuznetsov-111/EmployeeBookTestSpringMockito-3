package pro.sky.skyproEmployeeBookTestSpringMockito.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeNameIncorrectException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeNotFoundException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeStorageIsFullException;
import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private final Employee EMP = new Employee("Ivan", "Ivanov", 2000, 1);
    private final Employee EMP1 = new Employee("Petr", "Petrov", 2500, 1);
    private final Employee EMP2 = new Employee("1233", "12345");

    @BeforeEach
    void setUp() {

        employeeService = new EmployeeServiceImpl();

        assertEquals(0, employeeService.getAll().size());

    }

    @Test
    void add() {

        employeeService.add(EMP);

        assertEquals(1, employeeService.getAll().size());

        assertEquals(EMP, employeeService.getAll().get(0));

    }

    @Test
    void remove() {

        employeeService.add(EMP);
        employeeService.add(EMP1);

        assertEquals(2, employeeService.getAll().size());
        employeeService.remove(EMP.getFirstName(), EMP.getLastName());

        assertEquals(1, employeeService.getAll().size());

    }

    @Test
    void find() {

        employeeService.add(EMP);

        assertEquals(EMP, employeeService.find(EMP.getFirstName(), EMP.getLastName()));

    }

    @Test
    void getAll() {

        employeeService.add(EMP);
        employeeService.add(EMP1);

        assertEquals(2, employeeService.getAll().size());

    }

    @Test
    void TestEmployeeAlreadyAddedException() {

        employeeService.add(EMP);

        EmployeeAlreadyAddedException e = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.add(EMP)
        );

        assertEquals(e.getMessage(), "Such an employee already exists!!!");

    }

    @Test
    void TestEmployeeNameIncorrectException() {

        employeeService.add(EMP);

        EmployeeNameIncorrectException e = assertThrows(
                EmployeeNameIncorrectException.class,
                () -> employeeService.add(EMP2)
        );

        assertEquals(e.getMessage(), "Name incorrect");

    }

    @Test
    void TestEmployeeNotFoundException() {
        employeeService.add(EMP);

        EmployeeNotFoundException e = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(EMP2.getFirstName(), EMP2.getLastName())
        );

        assertEquals(e.getMessage(), "This employee has not been found");

    }

    @Test
    void TestEmployeeStorageIsFullException() {

        employeeService.add(EMP);
        employeeService.add(new Employee("Evgeniy", "Smirnov"));
        employeeService.add(new Employee("Svetlana", "Kostrova"));
        employeeService.add(new Employee("Harry", "Potter"));
        employeeService.add(new Employee("Ron", "Weesley"));

        EmployeeStorageIsFullException e = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(EMP2)
        );

        assertEquals(e.getMessage(), "The list size is exceeded!!!");

    }

}
