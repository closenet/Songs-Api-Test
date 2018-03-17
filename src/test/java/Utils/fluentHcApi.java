package Utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.simple.parser.JSONParser;

import static org.assertj.core.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class fluentHcApi {

    String baseUrl = "http://turing.niallbunting.com:3003/api";

    String resourceName = null;
    String body = null;
    String verb = null;
    String responseBody = null;
    int statusCode;
    String contentType = null;

    public int StatusCode() {
        return this.statusCode;
    }


    public void setResource(String ResourceName) {
        this.resourceName = ResourceName;
    }

    public void setBody(String BodyContent) {
        this.body = BodyContent;
    }

    public void setVerb(String Verb) {
        this.verb = Verb;
    }

    public void setResponse(String res) {
        this.responseBody = res;
    }

    public void setContentType(String ContentType) {this.contentType = ContentType;}


    public HttpResponse baseRequest() throws IOException {

        if (this.verb.equals("GET")) {
            return Request.Get(this.baseUrl + this.resourceName)
                    .execute()
                    .returnResponse();
        } else if (this.verb.equals("POST")) {
            return Request.Post(this.baseUrl + this.resourceName)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString(this.body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        } else if (this.verb.equals("PUT")) {
            return Request.Put(this.baseUrl + this.resourceName)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString(this.body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        } else {
            return Request.Delete(this.baseUrl + this.resourceName)
                    .execute()
                    .returnResponse();
        }
    }

    public void establishRequest() throws IOException {

        this.responseBody = EntityUtils.toString(this.baseRequest().getEntity());
        this.statusCode = this.baseRequest().getStatusLine().getStatusCode();
        this.contentType = this.baseRequest().getFirstHeader("Content-Type").getValue();


        System.out.println("#######STATUS#########   " + this.statusCode);
        System.out.println("#######BODY#########   " + this.responseBody);
        System.out.println("#######HEADER#########   " + this.contentType);

    }


    public void verifyStatusCode(int status) throws IOException, ParseException, JSONException {

        assertThat(status).isEqualTo(this.statusCode);

    }

    public void verifyContentType() throws IOException, ParseException, JSONException {

        assertThat("application/json; charset=utf-8").isEqualTo(this.contentType);

    }

    public void verifybody(String filename) throws IOException, ParseException, JSONException {

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("./src/test/resources/" + filename));
        String expected = obj.toString();
        String actual = this.responseBody.toString();
        JSONAssert.assertEquals(expected, actual, false);

    }


}
