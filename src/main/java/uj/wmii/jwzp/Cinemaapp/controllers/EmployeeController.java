package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Employee;
import uj.wmii.jwzp.Cinemaapp.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        service = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @DeleteMapping("{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long id) {
        return service.deleteEmployee(id);
    }

    @PutMapping("{employeeId}")
    public String updateEmployee(@PathVariable("employeeId") Long id,
                             @RequestParam String email,
                             @RequestParam String name,
                             @RequestParam String password) {
        return service.updateEmployee(id, email, name, password);
    }

    @PatchMapping("{employeeId}")
    public String patchEmployee(@PathVariable("employeeId") Long id,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String password) {
        return service.patchEmployee(id, email, name, password);
    }
}
