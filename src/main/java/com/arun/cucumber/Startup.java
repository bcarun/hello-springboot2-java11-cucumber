package com.arun.cucumber;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Startup {

  public static void main(String[] args) {
    SpringApplication.run(Startup.class, args);
  }

  @RestController
  @RequestMapping(path = "/employees")
  public static class EmployeeController {

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
      return ResponseEntity.ok(employee);
    }
  }


  @Data
  public static class Employee {
    private String id;
    private String firstName;
    private String lastName;
  }
}
