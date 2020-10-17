package com.arun.cucumber;

import static io.restassured.RestAssured.given;

import com.arun.cucumber.Startup.Employee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class EmployeeStepDefs implements En {

  @LocalServerPort
  private int port;

  @Autowired
  private TestContext testContext;

  public EmployeeStepDefs() {

    Given("user wants to create a employee with following attributes", ( DataTable dataTable) -> {
      List<Employee> employees = dataTable.asList(Employee.class);

      final RequestSpecification request = given().contentType(ContentType.JSON)
          .accept(ContentType.JSON)
          .body(employees.get(0))
          .log()
          .all();

      testContext.setRequest(request);
    });

    When("user saves employee", () -> {
      String url = "http://localhost:" + port + "/employees";
      final Response response = testContext.getRequest()
          .post(url)
          .thenReturn();

      response.then()
          .log()
          .all();

      testContext.setResponse(response);
    });

    Then("the save is successful", () -> {
      testContext.getResponse()
          .then()
          .statusCode(200);
    });
  }
}
