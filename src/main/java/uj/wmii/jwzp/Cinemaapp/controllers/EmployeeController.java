package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Employee;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        service = employeeService;
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long id) {
        Employee employee = service.getEmployeeById(id);

        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(
                service.getEmployees(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                service.addEmployee(employee), HttpStatus.OK
        );
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long id) {
        Employee employee = service.getEmployeeById(id);

        if(employee == null)
            return new ResponseEntity<>("Employee with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteEmployee(id), HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("employeeId") Long id,
                             @RequestParam String email,
                             @RequestParam String name,
                             @RequestParam String password) {
        
        return new ResponseEntity<>(service.updateEmployee(id, email, name, password), HttpStatus.OK);
    }

    @PatchMapping("{employeeId}")
    public ResponseEntity<String> patchEmployee(@PathVariable("employeeId") Long id,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String password) {

        Employee employee = service.getEmployeeById(id);

        if(employee == null)
            return new ResponseEntity<>("Employee with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchEmployee(id, email, name, password), HttpStatus.OK);
    }
}
