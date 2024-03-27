
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  				| password 		|	productName 	|
      | rsk@gmail.com | RSKathar@11 | ZARA COAT 3 	|
      