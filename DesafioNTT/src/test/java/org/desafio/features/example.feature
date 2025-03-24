Feature: Example feature

  Scenario: TC0001 - Open home page
    Given I open the home page
    And I should see the title "React App"
    Then I should see the text "Login"

  Scenario: TC0002 - Do login
    Given I open the home page
    And fill e-mail input "nadsonjr08@gmail.com"
    And fill password input "password"
    Then click on btn login