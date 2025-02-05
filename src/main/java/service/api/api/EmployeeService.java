package service.api.api;

import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeAlreadyAddedException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeNotFoundException;
import pro.sky.skyproEmployeeBookTestSpringMockito.exception.EmployeeStorageIsFullException;
import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee add(Employee employee)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;

    Employee remove(String firstName, String lastName)
            throws EmployeeNotFoundException;

    Employee find(String firstName, String lastName)
            throws EmployeeNotFoundException;

    List<Employee> getAll();
}
