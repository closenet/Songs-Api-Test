Feature: the user should be able to create or use a video using the
  api/video api

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
    Then response status equals to 201