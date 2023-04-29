package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long id);

    List<Employee> getEmployees();

    Employee addEmployee(Employee user);

    String deleteEmployee(Long id);

    String updateEmployee(Long id, String email, String name, String password);

    String patchEmployee(Long id, String email, String name, String password);
}
