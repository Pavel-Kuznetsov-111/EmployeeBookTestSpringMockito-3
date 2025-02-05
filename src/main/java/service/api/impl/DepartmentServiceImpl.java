package pro.sky.skyproEmployeeBookTestSpringMockito.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.skyproEmployeeBookTestSpringMockito.model.Employee;
import pro.sky.skyproEmployeeBookTestSpringMockito.service.api.DepartmentService;
import pro.sky.skyproEmployeeBookTestSpringMockito.service.api.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    @Override
    public Double sumSalary(int depId) {
        return getStreamByDep(depId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Double minSalary(int depId) {
        return getStreamByDep(depId)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);
    }

    @Override
    public Double maxSalary(int depId) {
        return getStreamByDep(depId)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0);
    }

    @Override
    public List<Employee> findByDepartment(int depId) {
        return getStreamByDep(depId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllByDep() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }

    private Stream<Employee> getStreamByDep(int depId) {
        return employeeService.getAll().stream()
                .filter(it -> depId == it.getDepartmentId());
    }
}
