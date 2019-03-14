package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // load employee data
    private List<Employee> theEmployee;

    @PostConstruct
    private void loadData(){
        // create employeem
        Employee emp1 = new Employee(1, "jiweon", "park", "rkawk3142@gmail.com");
        Employee emp2 = new Employee(2, "second", "kim", "second@gmail.com");
        Employee emp3 = new Employee(3, "third", "Lee", "third@gmail.com");

        //create list
        theEmployee = new ArrayList<>();

        // add to the list
        theEmployee.add(emp1);
        theEmployee.add(emp2);
        theEmployee.add(emp3);

    }

    @GetMapping("/list")
    public String listEmployee(Model theModel){
        theModel.addAttribute("employees", theEmployee);
        return "list-employees";
    }

}
