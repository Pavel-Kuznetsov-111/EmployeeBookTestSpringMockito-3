package service.api.api;

import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Double sumSalary (int depId);
    Double minSalary (int depId);
    Double maxSalary (int depId);

    List<Employee> findByDepartment(int depId);
    Map<Integer, List<Employee>> findAllByDep();

}
