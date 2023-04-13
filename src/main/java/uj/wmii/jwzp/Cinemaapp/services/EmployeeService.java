package uj.wmii.jwzp.Cinemaapp.services;

import uj.wmii.jwzp.Cinemaapp.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addEmployee(Employee user);

    String deleteEmployee(Long id);

    String updateEmployee(Long id, String email, String name, String password);

    String patchEmployee(Long id, String email, String name, String password);
}
