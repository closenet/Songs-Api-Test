package Utils;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_ARRAY_ITEMS;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_EXTRA_FIELDS;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class fluentHcApi {

    String baseUrl = "http://turing.niallbunting.com:3003/api";
    HttpResponse response = null;
    String resourceName = null;
    String body = null;
    String verb = null;
    String responseBody = null;
    int statusCode;
    String contentType = null;
    String headerName = null;
    String headerValue = null;
    HeaderIterator headerIt = null;
    String reasonPhrase = null;


    public void setHttpResponse(HttpResponse Response) {
        this.response = Response;
    }

    public void setReasonPhrase(String ReasonPhrase) {
        this.reasonPhrase = ReasonPhrase;
    }

    public void setHeaderIt(HeaderIterator HeaderIt) {
        this.headerIt = HeaderIt;
    }

    public void setHeaderName(String HeaderName) {
        this.headerName = HeaderName;
    }

    public void setHeaderValue(String HeaderValue) {
        this.headerValue = HeaderValue;
    }

    public void setStatusCode(int StatusCode) {
        this.statusCode = StatusCode;
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

    public void setContentType(String ContentType) {
        this.contentType = ContentType;
    }



    public HttpResponse baseRequest() throws IOException, HttpHostConnectException {

        try {
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
            } else if (this.verb.equals("PATCH") && this.body != null) {
                return Request.Patch(this.baseUrl + this.resourceName)
                        .useExpectContinue()
                        .version(HttpVersion.HTTP_1_1)
                        .bodyString(this.body, ContentType.APPLICATION_JSON)
                        .execute()
                        .returnResponse();
            } else if (this.verb.equals("PATCH") && this.body == null) {
                return Request.Patch(this.baseUrl + this.resourceName)
                        .useExpectContinue()
                        .version(HttpVersion.HTTP_1_1)
                        .execute()
                        .returnResponse();
            } else {
                return Request.Delete(this.baseUrl + this.resourceName)
                        .execute()
                        .returnResponse();
            }
        } catch (HttpHostConnectException e) {
            System.out.println("NOTE: record you try to delete is already deleted \n");
            return this.response;
        }

    }

    public void establishRequest() throws HttpHostConnectException, Exception {
        try {
            setHttpResponse(this.baseRequest());
            if (this.response.getEntity() == null) {
                setStatusCode(this.response.getStatusLine().getStatusCode());
                setHeaderIt(this.response.headerIterator());
                setReasonPhrase(this.response.getStatusLine().getReasonPhrase());

            } else {
                setStatusCode(this.response.getStatusLine().getStatusCode());
                setHeaderIt(this.response.headerIterator());
                setReasonPhrase(this.response.getStatusLine().getReasonPhrase());
                setResponse(EntityUtils.toString(this.response.getEntity()));
            }

        } catch (Exception e) {
            System.out.println("NOTE: record you try to delete is already deleted \n");
        }
    }


    public void verifyStatusCode(int status) throws IOException {
        assertThat(status).isEqualTo(this.statusCode);
    }

    public void setHeaderNameAndValue(String expectedHeader) {
        for (int i = 0; i >= this.response.getAllHeaders().length; i++) {
            if (this.headerIt.nextHeader().getName().equals(expectedHeader)) {
                this.headerName = this.headerIt.nextHeader().getName();
                this.headerValue = this.headerIt.nextHeader().getValue();
            } else {
                this.headerIt.next();
            }
        }
    }

    public void verifyHeader(String HeaderName, String HeaderValue) throws IOException {
        this.setHeaderNameAndValue(HeaderName);
        assertThat(this.headerName).isEqualTo(headerName);
        assertThat(this.headerValue).isEqualTo(headerValue);
    }

    public void verifyPartOfBodyArray(String expectedFileName) throws IOException {
        String expected = this.readExpectedFile(expectedFileName);
        String actual = this.responseBody;
        assertThatJson(actual).when(IGNORING_EXTRA_ARRAY_ITEMS,IGNORING_ARRAY_ORDER, IGNORING_EXTRA_FIELDS).isArray().thatContains(expected);
    }

    public void verifyExactBodyContent(String expectedFileName) throws IOException {
        String expected = this.readExpectedFile(expectedFileName);
        String actual = this.responseBody;
        assertThatJson(actual).when(IGNORING_ARRAY_ORDER).isEqualTo(expected);
    }

    public void verifyPartOfBodyContent(String expectedJson) throws IOException {
        String actual = this.responseBody;
        assertThatJson(actual).when(IGNORING_ARRAY_ORDER, IGNORING_EXTRA_FIELDS).isEqualTo(expectedJson);
    }

    static String readExpectedFile(String expectedResponseFile) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get("./src/test/resources/" + expectedResponseFile));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public void verifyErrMsg(String errMsg) throws Exception {
        assertThat(errMsg).isEqualTo(this.reasonPhrase);
    }
}