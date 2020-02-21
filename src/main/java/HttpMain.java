import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import org.json.JSONObject;

/**
 * Created by Evgeniia on 06.12.2018.
 */
public class HttpMain {
    //static String url ="http://dispatcher.netpoint-dc.com/sample.json";
    static String url ="https://stackoverflow.com/";

    public final static void main(String[] args) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws  IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        System.out.println("Code:    " + status);

                        /*
                        System.out.println( "Starting Json Validation." );
                        JsonValidation app = new JsonValidation();
                        String jsonData = "\"Redemption\"";
                        String jsonSchema = "{ \"type\": \"string\", \"minLength\": 2, \"maxLength\": 11}";
                        app.validate(jsonData, jsonSchema);
                        jsonData = "Agony";  // Quotes not included
                        app.validate(jsonData, jsonSchema);
                        jsonData = "42";
                        app.validate(jsonData, jsonSchema);
                        jsonData = "\"A\"";
                        app.validate(jsonData, jsonSchema);
                        jsonData = "\"The pity of Bilbo may rule the fate of many.\"";
                        app.validate(jsonData, jsonSchema);*/
                        //more
                        return null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
    }
}
