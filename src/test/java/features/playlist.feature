Feature: the user should be able to create or use a video using the
  api/video api

  @dev
  Scenario: create one playlist through the service
    Given endpoint path /playlist
    And using POST method
    And the body content will be the following
   """
       {
       "desc": "My first playlist.",
       "title": "My List"
       }
        """
    When a service request is established
    And response body contains
   """
       {
       "desc": "My first playlist.",
       "title": "My List",
       "__v": 0
       }
       """
    Then response status 201


  @dev
  Scenario: request one playlist through the service
    Given endpoint path /playlist
    And using GET method
    When a service request is established
    Then response status 200
    And response body item list equal to some in get-playlist-all.json file
    And header content-type equals to applications

  @dev @failing
  Scenario: request one playlist by ID through the service
    Given endpoint path /playlist/596cc4736ed7c10011a68b29
    And using GET method
    When a service request is established
    Then response status 200
    And response body item equal to get-playlist-byId.json file
    And header content-type equals to applications


  @dev
  Scenario: request to update playlist by ID through the service
    Given endpoint path /playlist/596cc4736ed7c10011a68b29
    And using PATCH method
    And the body content will be the following
    """
    {
	"videos": [
		{
			"596cac389f0525001db52244": "add"
		},
		{
			"596cabbe9f0525001db52242": "add"
		}
	]
    }
    """
    When a service request is established
    Then response status 204
    And header content-type equals to applications
    Given endpoint path /playlist/5aadbf1834bd6f011f99f9df
    And using PATCH method
    And the body content will be the following
    """
    {
	"videos": [
		{
			"596cabbe9f0525001db52242": "remove"
		}
	]
    }
    """
    When a service request is established
    Then response status 501
    And header content-type equals to applications
    And response body equal to Not Implemented message

  @dev
  Scenario: request to delete a playlist by ID through the service
    Given endpoint path /playlist/5aae5dae34bd6f011f99f9e7
    And using DELETE method
    When a service request is established
    Then response status 204
    And header content-type equals to applications