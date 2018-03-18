@playlist
Feature: As user who wants to organise his videos
  I want to create, watch, update and delete my videos
  so that I can enjoy manage my videos easily


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

  Scenario: request one playlist through the service
    Given endpoint path /playlist
    And using GET method
    When a service request is established
    Then response status 200
    And response body item list equal to some in get-playlist-all.json file
    And header content-type equals to applications

  @failing
  Scenario: request one playlist by ID through the service
    Given endpoint path /playlist/596cc4736ed7c10011a68b29
    And using GET method
    When a service request is established
    Then response status 200
    And response body item equal to get-playlist-byId.json file
    And header content-type equals to applications


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

  Scenario: request to delete a playlist by ID through the service
    Given endpoint path /playlist/5aaedf320e498a01a930dec7
    And using DELETE method
    When a service request is established
    Then response status 204
    And header content-type equals to applications