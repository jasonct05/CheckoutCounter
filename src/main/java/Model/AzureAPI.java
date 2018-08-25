package Model;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class AzureAPI {
    public static void main(String[] args) {
        analyzeImage("cache/ShoppingBasket.png");
    }

    public static ArrayList<String> analyzeImage(String path) {
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/03f50af0-4ad1-47d9-bb7e-cbf492eb0367/url");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Prediction-Key", "b96bb8e106dd4dce9e6ccd1bd6292721");
            request.setHeader("Content-Type", "application/octet-stream");


            // Request body
            File img = new File(path);
            FileEntity reqEntity = new FileEntity(img, ContentType.APPLICATION_OCTET_STREAM);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            String jsonResult = EntityUtils.toString(entity);


            if (entity != null)
            {

                System.out.println(jsonResult);
            }


            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonResult);
            JSONObject jo = (JSONObject) obj;

            JSONArray predictions = (JSONArray) jo.get("predictions");
            Iterator products = predictions.iterator();

            ArrayList<String> result = new ArrayList<String>();

            while (products.hasNext()) {
                JSONObject product = (JSONObject) products.next();
                double productProbability = product.getDouble("probability");

                if (productProbability >= 0.85) {
                    result.add((String) product.get("tagName"));

                }
            }

            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
