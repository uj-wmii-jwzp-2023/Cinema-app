package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Employee;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        service = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long id) {
        LOGGER.debug("Getting employee by id: {}", id);

        Employee employee = service.getEmployeeById(id);

        if (employee == null) {
            LOGGER.info("Employee with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found employee with id {}: {}", id, employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        LOGGER.debug("Getting all employees");

        List<Employee> employees = service.getEmployees();

        LOGGER.info("Found {} employees", employees.size());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        LOGGER.debug("Adding employee: {}", employee);

        Employee addedEmployee = service.addEmployee(employee);

        LOGGER.info("Added employee with id {}: {}", addedEmployee.getId(), addedEmployee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long id) {
        LOGGER.debug("Deleting employee with id: {}", id);

        Employee employee = service.getEmployeeById(id);

        if (employee == null) {
            LOGGER.info("Employee with id {} not found", id);
            return new ResponseEntity<>("Employee with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedEmployee = service.deleteEmployee(id);
        LOGGER.info("Deleted employee with id {}: {}", id, deletedEmployee);
        return new ResponseEntity<>(deletedEmployee, HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable("employeeId") Long id,
                                                 @RequestParam String email,
                                                 @RequestParam String name,
                                                 @RequestParam String password) {
        LOGGER.debug("Updating employee with id: {}", id);

        String updatedEmployee = service.updateEmployee(id, email, name, password);

        LOGGER.info("Updated employee with id {}: {}", id, updatedEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @PatchMapping("{employeeId}")
    public ResponseEntity<String> patchEmployee(@PathVariable("employeeId") Long id,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String password) {
        LOGGER.debug("Patching employee with id: {}", id);

        Employee employee = service.getEmployeeById(id);

        if (employee == null) {
            LOGGER.info("Employee with id {} not found", id);
            return new ResponseEntity<>("Employee with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedEmployee = service.patchEmployee(id, email, name, password);
        LOGGER.info("Patched employee with id {}: {}", id, patchedEmployee);
        return new ResponseEntity<>(patchedEmployee, HttpStatus.OK);
    }
}
