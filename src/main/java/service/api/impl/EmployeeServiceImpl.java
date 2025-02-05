package pro.sky.skyproEmployeeBookTestSpringMockito.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeNameIncorrectException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeNotFoundException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeStorageIsFullException;
import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;
import pro.sky.skyproEmployeeBookTestSpringMockito.service.api.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String REGEX_NAME = "[a-zA-Zа-яА-Я]+";
    private static final Integer MAX_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(Employee employee)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("The list size is exceeded!!!");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Such an employee already exists!!!");
        }

        validateName(employee.getFirstName());
        validateName(employee.getLastName());

        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName)
            throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("This employee has not been found");
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName)
            throws EmployeeNotFoundException {

        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("This employee has not been found");
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private void validateName(String name) {
        if (!name.matches(REGEX_NAME)) {
            throw new EmployeeNameIncorrectException("Name incorrect");
        }
    }
}