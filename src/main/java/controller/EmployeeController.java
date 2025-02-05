package pro.sky.skyproEmployeeBookTestSpringMockito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import model.gho.Employee;
import service.api.api.EmployeeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        if (!StringUtils.hasText(employee.getFirstName()) || !StringUtils.hasText(employee.getLastName())){
            throw new IllegalArgumentException("Employee mast have a first and last  name ");
        }
        return employeeService.add(employee);
    }

    @DeleteMapping (path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }


    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping(path = "/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }
}
