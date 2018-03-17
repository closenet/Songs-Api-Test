Feature: the user should be able to create or use a video using the
  api/video api

#  Scenario: create one video through the service
#    Given endpoint path /video
#    And using POST method
#    And the body content will be the following
#    """
#    {
#    "artist": "Lady Gaga",
#    "song": "Poker face",
#    "publishDate": "2017-09-01"
#    }
#     """
#    When a service request is established
#    Then response status equals to 201

  Scenario: request one video through the service
    Given endpoint path /video
    And using GET method
    When a service request is established
    Then response status 200
    And response body equal to get-video-all.json file
    And header content-type equals to applications

  Scenario: request one video by ID through the service
    Given endpoint path /video/5aac4dcfa186db00112025bb
    And using GET method
    When a service request is established
    Then response status 200
    And response body equal to get-video-byId.json file
    And header content-type equals to applications

  @failing
  Scenario: request to update video by ID through the service
    Given endpoint path /video/5aac4905a186db00112025ba
    And using PATCH method
    When a service request is established
    Then response status 501
    And header content-type equals to applications
    And response body equal to Not Implemented message