package com.ldar01.demoemployees.controller;

import com.ldar01.demoemployees.dto.response.GeneralResponse;
import com.ldar01.demoemployees.dto.request.employee.EmployeeRequest;
import com.ldar01.demoemployees.dto.request.employee.EmployeeUpdateRequest;
import com.ldar01.demoemployees.dto.response.employee.EmployeeResponse;
import com.ldar01.demoemployees.dto.response.vacation.VacationResponse;
import com.ldar01.demoemployees.exception.EmployeeNotFoundException;
import com.ldar01.demoemployees.exception.VacationNotFoundException;
import com.ldar01.demoemployees.service.EmployeeService;
import com.ldar01.demoemployees.service.VacationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee") //Don´t forget to add this annotation for the controller and redirection
public class EmployeeController {
    // This is the service that will be used to handle the business logic
    private final EmployeeService employeeService;
    private final VacationService vacationService;

    // Constructor injection is preferred over field injection
    @Autowired
    public EmployeeController(EmployeeService employeeService, VacationService vacationService) {
        this.employeeService = employeeService;
        this.vacationService = vacationService;
    }

    // This is the endpoint that will be used to get all employees
    @GetMapping()
    public ResponseEntity<GeneralResponse> getEmployees() {
        List<EmployeeResponse> employees = employeeService.findAll();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Employees were not found");
        }
        return buildResponse("Employees found", HttpStatus.OK, employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getEmployeeById(@PathVariable int id) {
        EmployeeResponse employee = employeeService.findById(id);
        return buildResponse("Employee found", HttpStatus.OK, employee);
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> saveEmployee(@RequestBody @Valid EmployeeRequest employee) {

        return buildResponse("Employee created", HttpStatus.CREATED, employeeService.save(employee));
    }

    @PutMapping()
    public ResponseEntity<GeneralResponse> updateEmployee(@RequestBody @Valid EmployeeUpdateRequest employee) {
        employeeService.findById(employee.getEmployeeId());
        return buildResponse("Employee updated", HttpStatus.OK, employeeService.update(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteEmployee(@PathVariable int id) {
        EmployeeResponse employee = employeeService.findById(id);
        employeeService.delete(id);
        return buildResponse("Employee deleted", HttpStatus.OK, employee);
    }

    @GetMapping("/{employeeId}/vacations-requests")
    public ResponseEntity<GeneralResponse> getVacationsByEmployeeId(@PathVariable int employeeId) {
        List<VacationResponse> vacations = vacationService.findByEmployeeId(employeeId);

        if (vacations.isEmpty()) {
            throw new VacationNotFoundException("Vacations for employee with ID: " + employeeId + " were not found");
        }
        return buildResponse("Vacations found", HttpStatus.OK, vacations);
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(GeneralResponse.builder()
                .message(message)
                .status(status.value())
                .data(data)
                .uri(uri)
                .time(LocalDate.now())
                .build());
    }
}
