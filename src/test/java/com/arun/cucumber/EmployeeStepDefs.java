package com.arun.cucumber;

import static io.restassured.RestAssured.given;

import com.arun.cucumber.Startup.Employee;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class EmployeeStepDefs {

  @LocalServerPort
  private int port;

  @Autowired
  private TestContext testContext;

  @Given("user wants to create a employee with following attributes")
  public void user_wants_to_create_a_employee_with_following_attributes(List<Employee> employees) {
    final RequestSpecification request = given().contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(employees.get(0))
        .log()
        .all();

    testContext.setRequest(request);
  }

  @When("user saves employee")
  public void user_saves_employee() {
    String url = "http://localhost:" + port + "/employees";
    final Response response = testContext.getRequest()
        .post(url)
        .thenReturn();

    response.then()
        .log()
        .all();

    testContext.setResponse(response);
  }

  @Then("the save is successful")
  public void the_save_is_successful() {
    testContext.getResponse()
        .then()
        .statusCode(200);
  }
}
