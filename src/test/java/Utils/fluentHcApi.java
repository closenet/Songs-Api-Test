package Utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class fluentHcApi {

    String baseUrl = "http://turing.niallbunting.com:3003/api";

    String resourceName = null;
    String body = null;
    String verb = null;
    String responseBody = null;
    int statusCode;


    public int StatusCode()
    {
       return this.statusCode;
    }



    public void setResource(String ResourceName)
    {
        this.resourceName = ResourceName;
    }

    public void setBody(String BodyContent)
    {
        this.body = BodyContent;
    }

    public void setVerb(String Verb)
    {
        this.verb = Verb;
    }

    public void setResponse(String res)
    {
        this.responseBody = res;
    }


    public HttpResponse baseRequest () throws  IOException {

        if (this.verb.equals("GET"))
        {
            return Request.Get(this.baseUrl + this.resourceName)
                    .execute()
                    .returnResponse();
        }
        else if (this.verb.equals("POST"))
        {
            return Request.Post(this.baseUrl + this.resourceName)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString(this.body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        }
        else if (this.verb.equals("PUT"))
        {
            return Request.Put(this.baseUrl + this.resourceName)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString(this.body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        }
        else {
                return Request.Delete(this.baseUrl + this.resourceName)
                        .execute()
                        .returnResponse();
        }
    }

    public void establishRequest() throws IOException
    {
       this.responseBody = EntityUtils.toString(this.baseRequest().getEntity());
        this.statusCode = this.baseRequest().getStatusLine().getStatusCode();

        System.out.println("#######STATUS#########   " + this.statusCode);
        System.out.println("#######BODY#########   " +  this.responseBody);

    }










}
