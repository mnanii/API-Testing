Feature: Create a Trello Board

Scenario: Successful create a new board
  Given I have valid API credentials
  When I send a post request to create a board
  Then response status is 200
  And response should contain id