package stepDefs;

import Utils.fluentHcApi;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class commonSteps {

    fluentHcApi songsApi = new fluentHcApi();
    HttpResponse response = null;

    @Given("^endpoint path (/\\w+)")
    public void endpointPathVideo(String resourcesName) throws Throwable {
        songsApi.setResource(resourcesName);
    }

    @And("^using (POST|GET|PUT|DELETE) method$")
    public void aRequestSentToTheVideoEndpoint(String verb) throws Throwable {

        songsApi.setVerb(verb);
      //  System.out.println("##################R###    " + resourceName);

    }

    @And("^the body content will be the following$")
    public void aRequestSentToApiVideo(String bodyContent) throws Throwable {
        songsApi.setBody(bodyContent);
    }


    @When("^a service request is established$")
    public void theServiceRequestIsEstablished() throws Throwable {

        songsApi.establishRequest();

    }


    @Then("^response status equals to (\\d+)$")
    public void responseStatusEquals(int arg0) throws Throwable {


        songsApi.StatusCode();
    }
}
