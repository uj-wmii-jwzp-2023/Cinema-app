package uj.wmii.jwzp.Cinemaapp.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Employee;
import uj.wmii.jwzp.Cinemaapp.repositories.EmployeeRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.EmployeeService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }


    boolean CorrectEmail(String email) {

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (email.length() > 0 &&
                matcher.matches()) {

            Optional<Employee> optionalEmployee = repository.findEmployeeByEmail(email);
            if (optionalEmployee.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }

            return true;
        }

        throw new IllegalStateException("Invalid email");
    }

    boolean CorrectName(String name) {
        if (name != null &&
                name.length() > 0) {

            return true;
        }

        throw new IllegalStateException("Invalid name");
    }

    boolean CorrectPassword(String password) {
        if (password != null &&
                password.length() > 5) {

            return true;
        }

        throw new IllegalStateException("Invalid password");
    }


    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        if (CorrectEmail(employee.getEmail()) && CorrectName(employee.getName()) && CorrectPassword(employee.getPassword()))
            return repository.save(employee);
        else
            throw new IllegalStateException("Cannot create employee");
    }

    @Override
    public String deleteEmployee(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalStateException("Employee with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "Employee account with id " + id + " was deleted";
    }

    @Transactional
    public String updateEmployee(Long id, String email, String name, String password) {
        Employee employee = repository.findById(id).orElse(null);

        if(employee == null) {
            Employee newEmployee = new Employee(email, name, password);

            addEmployee(newEmployee);
            return "Employee created";
        }

        String result = "";

        if (CorrectEmail(email) &&
                !Objects.equals(employee.getEmail(), email)) {

            employee.setEmail(email);
            result += "Email changed\n";
        }

        if (CorrectName(name) &&
                !Objects.equals(employee.getName(), name)) {

            employee.setName(name);
            result += "Name changed\n";
        }

        if (CorrectPassword(password) &&
                !Objects.equals(employee.getPassword(), password)){

            employee.setPassword(password);
            result += "Password changed\n";
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }

    @Transactional
    public String patchEmployee(Long id, String email, String name, String password) {
        Employee employee = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Employee with id " + id + " does not exist")
        );

        String result = "";

        if (email != null) {
            if (CorrectEmail(email) &&
                    !Objects.equals(employee.getEmail(), email)) {

                employee.setEmail(email);
                result += "Email changed\n";
            }
        }

        if (name != null) {
            if (CorrectName(name) &&
                    !Objects.equals(employee.getName(), name)) {

                employee.setName(name);
                result += "Name changed\n";
            }
        }

        if (password != null) {
            if (CorrectPassword(password) &&
                    !Objects.equals(employee.getPassword(), password)){

                employee.setPassword(password);
                result += "Password changed\n";
            }
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }
}
