package com.arun.cucumber;

import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@Data
public class TestContext {

  private RequestSpecification request;
  private Response response;
}
