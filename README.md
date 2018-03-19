# Songs-API-Test


**`Test Suite Structure`**

 I have used the following technologies to create the cucumbers tests

 - java language  (vJava8)
 - Cucumber as BDD tool  (v1.0.11)
 - Junit testing framework used by Cucumber and fluent hc  (v1.2.5)
 - Apache Fluent-Hc API tool   (v1.28.2)
 - Fluent Unit Test as assertion tool  (v1.28.2)
 - AssertJ as a assertion tool  (v3.8.0)


`Application Base Url`

        turing.niallbunting.com:3003

**`Endpoints`**
    /api/video
    /api/video/(id)
    /api/playlist
    /api/playlist/(ID)


**`what has been created?`**
I have created four folders which each got special purpose:
-----------------------------------------------------------

**`Feature folder`**
There are two feature files created for the songs APIs to cover the two feature areas

    video.feature
    playlist.feature


**`StepDef folder`**
this will be the mapped steps, written in Java which will implement the actual cucumber steps

**`Utils folder`**
This folder will hold the actual fluent hc generic funtionality and calls to the endpoints.

   **Testrunners**

It also hold four testRunner files two for the passing scenarios and two for the failed scenarios

 to run these runners from the command line, use the follwing command:

 **mvn test -Dtest=[Test Runner Name]**

**`Resources folder`**

 These are json files that will be used when asserting on the returned responses


 **Tests Results Explained**

    #Video feature test scenarios

 There are 4 scenarios should pass and 1 marked as failing, as this PATCH endpoint is Not implemented
 Although I have build the test to assert on status 501 and Not Implemented message, however I have tagged it as failing
 so, it will not be picked up by the test runner. Once, we get info from the devs that this is implemented then we fix
 the test and remove the @failing tag

 For the DELETE /api/video/(ID)  this would pass the first time and will fail afterwards as this endpoint will try to
 delete something already been deleted.
 There is no cucumber out of the box solution to parameterise a dynamic ID so that we link it. However, other frameworks like
 karate testing framework can help with saving it in a variable and used later on.


    #playlist feature test scenarios

 There are 6 scenarios 5 should pass and one is flagged as failing, as this PATCH endpoint to remove item is Not implemented
 Although I have built the test to assert on status 501 and Not Implemented message, however I have tagged it as failing
 so, it will not be picked up by the test runner. Once, we get info from the devs that this is implemented then we fix
 the test and remove the @failing tag.

 For the DELETE /api/playlist/(ID)  this would pass the first time and will fail later the same as the reason above with the videos endpoints
