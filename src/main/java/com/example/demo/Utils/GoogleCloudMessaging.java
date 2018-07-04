package com.example.demo.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;


@Component
public class GoogleCloudMessaging {
    private final String ANDROID_NOTIFICATION_URL = "https://fcm.googleapis.com/fcm/send";
    private final String ANDROID_NOTIFICATION_KEY = "AIzaSyAlpvsT4MLyooBMcivqpE6PajJx2AvgBdo";
    private final String SENDER_ID= "gabriela-199414";


    /*
    public void sendPost() throws IOException {

        //Below is a good tutorial , how to post json data
        //http://hmkcode.com/android-send-json-data-to-server/

        String REGISTRATION_ID ="APA91bHH4iNCFdWUIXSHRXV3hsBeF8IU0ZElts9AXaHItDfRdRld-kwkVx69EFYZePPuLOW1hTkUCmAwyTeGdoirr25KJ3RG1AikGbBzsvqaPCLLz9YYCwPDuB6xUupVKmllNoTn2v0BRTTkC6OS_i8zerATtBP3gg" ;
        String API_KEY = "AIzaSyARQTvQ5pRYEbW-9V98uDHNnn10Rwffx18";

       REGISTRATION_ID = SENDER_ID;
       API_KEY = ANDROID_NOTIFICATION_KEY;



        String url = "https://android.googleapis.com/gcm/send";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject mainData = new JSONObject();
        try {
            JSONObject data = new JSONObject();
            data.putOpt("message1", "test msg");
            data.putOpt("message2", "testing..................");
            JSONArray regIds = new JSONArray();
            regIds.put(REGISTRATION_ID);
            mainData.put("registration_ids", regIds);
            mainData.put("data", data);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        StringEntity se = new StringEntity(mainData.toString());
        post.setEntity(se);
        post.addHeader("Authorization", "key="+API_KEY);
        post.addHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(post);
        String a = Integer.toString(response.getStatusLine().getStatusCode());
       System.out.println("response -> " + a);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            result.append(line);
        }

    }
*/
    public void sendAndroidNotification(String deviceToken,String message,String title) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        try {
            JSONObject obj = new JSONObject();
            JSONObject msgObject = new JSONObject();
            msgObject.put("body", message);
            msgObject.put("title", title);
            obj.put("to", deviceToken);
            obj.put("notification",msgObject);
            RequestBody body = RequestBody.create(mediaType, obj.toString());
            Request request = new Request.Builder().url(ANDROID_NOTIFICATION_URL).post(body)
                    .addHeader("authorization", "key="+"AIzaSyDz_OOG9l5G-VsiLIWL3P0xZYPOXPUKD6U").build();

            Response response = client.newCall(request).execute();
            System.out.print("Notification response >>>" +response.body().string());
        }catch (JSONException ex){
            System.out.print(ex.getCause());
        }
    }
}