package pro.sky.skyproEmployeeBookTestSpringMockito.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import model.gho.Employee;
import service.api.api.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")

public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{depId}/employees")
    public List<Employee> employees (@PathVariable Integer depId){
        return departmentService.findByDepartment(depId);
    }

    @GetMapping("/{depId}/salary/sum")
    public Double sum(@PathVariable Integer depId){
        return departmentService.sumSalary(depId);
    }

    @GetMapping("/{depId}/salary/min")
    public Double min(@PathVariable Integer depId){
        return departmentService.minSalary(depId);
    }

    @GetMapping("/{depId}/salary/max")
    public Double max(@PathVariable Integer depId){
        return departmentService.maxSalary(depId);
    }

    @GetMapping("/employees")
    public Map <Integer, List<Employee>> findAllByDep(){
        return departmentService.findAllByDep();
    }

}
