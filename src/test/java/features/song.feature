Feature: the user should be able to create or use a video using the
  api/video api

  @dev
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

  @dev
  Scenario: request one video through the service
    Given endpoint path /video
    And using GET method
    When a service request is established
    Then response status 200
    And response body equal to get-video-all.json file
    And header content-type equals to applications

  @dev
  Scenario: request one video by ID through the service
    Given endpoint path /video/5aac4dcfa186db00112025bc
    And using GET method
    When a service request is established
    Then response status 200
    And response body equal to get-video-byId.json file
    And header content-type equals to applications

  @dev @failing
  Scenario: request to update video by ID through the service
    Given endpoint path /video/5aac4905a186db00112025ba
    And using PATCH method
    When a service request is established
    Then response status 501
    And header content-type equals to applications
    And response body equal to Not Implemented message

  @dev
  Scenario: request to delete video by ID through the service
    Given endpoint path /video/5aadae6e0fe4ae00c532c512
    And using DELETE method
    When a service request is established
    Then response status 204
    And header content-type equals to applications
