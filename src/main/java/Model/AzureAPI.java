package Model;

import java.io.File;
import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import static Util.Utility.debug;

public class AzureAPI {
    public static final boolean USE_DUMMY_IMAGE = false;
    public static final String IMAGE_TO_ANALYZE = "https://image.ibb.co/dbfYyU/IMG_5848.jpg";

    // COKE WITH XBOX           ->  https://image.ibb.co/b4rHQ9/IMG_5848.jpg
    // COKE WITH XBOX AND LYSOL ->  https://image.ibb.co/dbfYyU/IMG_5849.jpg

    public static Set<String> analyzeImage(String path) {
        HttpClient httpclient = HttpClients.createDefault();
        Set<String> result = new HashSet<String>();
        try
        {
            URIBuilder builder = new URIBuilder("https://southcentralus.api.cognitive.microsoft.com/customvision/v2.0/Prediction/03f50af0-4ad1-47d9-bb7e-cbf492eb0367/image");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // using image
            if (USE_DUMMY_IMAGE) {
                request.setHeader("Prediction-Key", "b96bb8e106dd4dce9e6ccd1bd6292721");
                request.setHeader("Content-Type", "application/json");

                // Request body
                StringEntity reqEntity = new StringEntity("{\"url\":\"" + IMAGE_TO_ANALYZE + "\"}");
                request.setEntity(reqEntity);
            } else {
                // using local file
                request.setHeader("Prediction-Key", "b96bb8e106dd4dce9e6ccd1bd6292721");
                request.setHeader("Content-Type", "application/octet-stream");

                // Request body
                File img = new File(path);
                FileEntity reqEntity = new FileEntity(img, ContentType.APPLICATION_OCTET_STREAM);
                request.setEntity(reqEntity);
            }

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

            while (products.hasNext()) {
                JSONObject product = (JSONObject) products.next();
                double productProbability = ((Number) product.get("probability")).doubleValue();

                if (productProbability >= 0.1) {
                    result.add((String) product.get("tagName"));

                }
            }
        }
        catch (Exception e)
        {
            // do nothing
        }
        return result;
    }
}
