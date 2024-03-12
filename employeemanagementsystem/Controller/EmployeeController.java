package com.example.employeemanagementsystem.Controller;

import com.example.employeemanagementsystem.APIResponse.APIResponse;
import com.example.employeemanagementsystem.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

//
//    @GetMapping("/get")
//    public ArrayList<Employee> getEmployees() {
//        return employees;
//    }

    //EmployeeController Endpoint:
    //1. Get all employees: Retrieves a list of all employees.
    @GetMapping("/getall")
    public ResponseEntity getAllemployee() {
        if (employees.isEmpty()) {
            return ResponseEntity.status(400).body(new APIResponse("Empty Array"));
        }
        return ResponseEntity.status(400).body(employees);
    }

    //2. Add a new employee: Adds a new employee to the system.
    @PostMapping("addEmployee")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
//            String info = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        }
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.OK).
                body(new APIResponse("Employee " + employee.getName() + " is added Successfully"));
    }

    //3. Update an employee: Updates an existing employee's information.
    @PutMapping("upadteEmployee/{ID}")
    public ResponseEntity upadeEmployee(@PathVariable String ID, @RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID().equalsIgnoreCase(employee.getID())) {
                employees.set(i, employee);
                return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("Employee " + employee.getName() + " updated successfully"));
            }
        }
        return ResponseEntity.status(400).body(new APIResponse("There is no employee with ID " + employee.getID()));
    }

    //4. Delete an employee: Deletes an employee from the system.
    @DeleteMapping("delete/{ID}")
    public ResponseEntity delete(@PathVariable String ID) {
        for (Employee employee : employees) {
            if (employee.getID().equalsIgnoreCase(ID))
                employees.remove(employee);
            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(employee.getName() + " Deleted successfully"));
        }
        return ResponseEntity.status(400).body(new APIResponse("there is no employee with ID " + ID));
    }

    //Note:
    //▪ Verify that the employee exists.
    //5. Search Employees by Position: Retrieves a list of employees based on their
    //position (supervisor or coordinator).
    //Note:
    //▪ Ensure that the position parameter is valid (either "supervisor" or "coordinator").

    @GetMapping("/serachPostion/{position}")
    public ResponseEntity serachPosition(@PathVariable @Valid String position, Errors errors) {
        ArrayList<Employee> positionList = new ArrayList<Employee>();
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position))
                positionList.add(employee);
        }
        if (positionList.isEmpty())
            return ResponseEntity.status(400).body(new APIResponse("There is no employee with this position " + position));
        return ResponseEntity.status(HttpStatus.OK).body(positionList);
    }

    //6. Get Employees by Age Range: Retrieves a list of employees within a specified
    //age range.
    //Note:
    //▪ Ensure that minAge and maxAge are valid age values.
    @GetMapping("/rangeAge/{age_min}/{age_man}")
    public ResponseEntity rangeAge(@PathVariable @Valid int age_min, @PathVariable @Valid int age_max,
                                   Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        ArrayList<Employee> rangeAge = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAge() >= age_min) {
                if (employee.getAge() <= age_max) {
                    rangeAge.add(employee);
                }
            }
            if (rangeAge.isEmpty()) {
            }
            return ResponseEntity.status(400).body(new APIResponse("there is no employee their age between" + age_min + " " + age_max));
        }

        return ResponseEntity.status(HttpStatus.OK).body(rangeAge);
    }

    //7. Apply for annual leave: Allow employees to apply for annual leave.
    //Note:
    //▪ Verify that the employee exists.
    //▪ The employee must not be on leave (the onLeave flag must be false).
    //▪ The employee must have at least one day of annual leave remaining.
    //▪ Behavior:
    //▪ Set the onLeave flag to true.
    //▪ Reduce the annualLeave by 1.
    @PutMapping("/annualLeave/{ID}")
    public ResponseEntity annualLeave(@PathVariable @Valid String ID, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        for (Employee employee : employees) {
            if (employee.getID().equalsIgnoreCase(ID)) {//1-exist
                if (!employee.isOnLeave()) {//2- (the onLeave flag must be false).
                    if (employee.getAnnualLeave() > 0) {//3- annual leave remaining.
                        employee.setAnnualLeave(employee.getAnnualLeave() - 1);
                        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("annualLeave Successfully for " + ID));
                    }

                }
            }
        }
        return ResponseEntity.status(400).body(new APIResponse("There is Employee with ID " + ID));
    }

    //8. Get Employees with No Annual Leave: Retrieves a list of employees who have
    //used up all their annual leave.
    @GetMapping("/listAnnual")
    public ResponseEntity listAnnual() {
        ArrayList<Employee> listAnnual = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getAnnualLeave() == 0) {
                listAnnual.add(employee);
            }
        }
        if (listAnnual.isEmpty()) {
            return ResponseEntity.status(400)
                    .body(new APIResponse("There is no employees No Annual Leave"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(listAnnual);
    }

    //9. Promote Employee: Allows a supervisor to promote an employee to the position
    //of supervisor if they meet certain criteria.
    //Note:
    //▪ Verify that the employee with the specified ID exists.
    //▪ Ensure that the requester (user making the request) is a supervisor.
    //▪ Validate that the employee's age is at least 30 years.
    //▪ Confirm that the employee is not currently on leave.
    //▪ Change the employee's position to "supervisor" if they meet the criteria.
    @PutMapping("changePostion/{IDS}/{IDE}")
    public ResponseEntity changePostion(@PathVariable @Valid String IDS, @PathVariable @Valid String IDE, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        for (Employee employee : employees) {
            if (employee.getID().equalsIgnoreCase(IDS)) {
                if (employee.getPosition().equalsIgnoreCase("supervisor")) {
                    for (Employee employee1 : employees) {
                        if (employee1.getID().equalsIgnoreCase(IDE)) {//found
                            if (employee1.getAge() >= 30) {
                                if (!employee1.isOnLeave()) {//i put ! to convert false to true ;
                                    employee1.setPosition("supervisor");
                                    return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(employee1.getID() + " Upgrade by" + employee.getName()));
                                }
                            }
                        }
                    }
                    return ResponseEntity.status(400).body(new APIResponse("There is no employees with this IDE" + IDE));
                }
            }
        }
        return ResponseEntity.status(400).body(new APIResponse("There is no employees with this IDS" + IDS));
    }
}