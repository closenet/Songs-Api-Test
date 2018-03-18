@video
Feature: As user who wants to listen to video songs
  I want to create, browse, update and delete my playlist
  and add and remove videos from the lists
  so that I can enjoy manage my videos/playlist easily


  Scenario: create one video through the service
    Given endpoint path /video
    And using POST method
    And the body content will be the following
    """
    {
    "artist": "Lady Gaga",
    "song": "Poker face",
    "publishDate": "2017-09-01"
    }
     """
    When a service request is established
    And response body contains
    """
    {
    "__v":0,
    "song":"Poker face",
    "artist":"Lady Gaga",
    "publishDate":"2017-09-01T00:00:00.000Z"
    }
    """
    Then response status 201


  Scenario: request all videos through the service
    Given endpoint path /video
    And using GET method
    When a service request is established
    Then response status 200
    And response body item list equal to some in get-video-multiple.json file
    And header content-type equals to applications


  Scenario: request one video by ID through the service
    Given endpoint path /video/5aac4dcfa186db00112025bc
    And using GET method
    When a service request is established
    Then response status 200
    And response body item equal to get-video-byId.json file
    And header content-type equals to applications

  @failing
  Scenario: request to update video by ID through the service
    Given endpoint path /video/5aac4905a186db00112025ba
    And using PATCH method
    When a service request is established
    Then response status 501
    And header content-type equals to applications
    And response body equal to Not Implemented message


  Scenario: request to delete video by ID through the service
    Given endpoint path /video/5aaee169358f5501c73f5c0f
    And using DELETE method
    When a service request is established
    Then response status 204
    And header content-type equals to applications
