package com.arun.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;

public class DataTransformer implements En {

  @Autowired
  private ObjectMapper objectMapper;

  // For data transformation in lambdas
  public DataTransformer() {
    // Methods defined in En interface above
    DefaultParameterTransformer((String fromValue, Type toValueType) -> objectMapper.convertValue(fromValue,
        objectMapper.constructType(toValueType)));

    DefaultDataTableCellTransformer((fromValue, toValueType) ->
        objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType)));

    DefaultDataTableEntryTransformer((fromValue, toValueType) ->
        objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType)));

  }
}
