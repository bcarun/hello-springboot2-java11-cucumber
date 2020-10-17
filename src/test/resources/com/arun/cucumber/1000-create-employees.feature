Feature: Create Employee

  Scenario: Create employee with all required values
    Given user wants to create a employee with following attributes
      | id | firstName | lastName |
      | 1  | Sanjhit   | Prathap  |
      | 2  | Sanjiv    | Prathap  |

    When user saves employee

    Then the save is successful
