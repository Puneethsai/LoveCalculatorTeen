package com.example.puneeth.lovecalculatorteen.Utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 24339 on 5/30/2018.
 */

public class OkHttpUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void postRequest(final Activity activity, final String url, final ResponseListenerString responseListenerString){

        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(JSON,jsonObject.toString());

        final Request request = new Request.Builder()
                .url(url)
//                .post(requestBody)
                .header("X-Mashape-Key","R7jZ0mfgetmshacdHC5WUYJb9kv2p17u6nZjsn74RLZ0ANcWeS")
                .header("X-Mashape-Host","love-calculator.p.mashape.com")
                .header("Content-Type","application/x-www-form-urlencoded")
                .build();
        Log.d("Request", request + "");

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()){

                    Log.d("Response", response + "");

                    final String result = response.body().string();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListenerString.onResponseReceived(result);
                        }
                    });

                    /*Log.d("Response", response + "");
                   JSONArray jsonArray = null;
                    JSONObject jsonObject1 = new JSONObject();

                    try{

                        String result = response.body().string();
                        jsonObject1.put(responseKey, result);

                    } catch (JSONException | IOException e){
                        e.printStackTrace();
                    }*/

                   /* activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListener.onResponseReceived(jsonObject);
                        }
                    });*/
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity, "PostRequest Exception" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void postQuoteRequest(final Activity activity, final String url, final ResponseListenerString responseListenerString){

        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(JSON,jsonObject.toString());

        final Request request = new Request.Builder()
                .url(url)
//                .post(requestBody)
                .header("X-Mashape-Key","kXJS72jCPFmshBbi8BxxCTG5ucT7p1kgrUqjsnSuiDG9w5HHD5")
                .header("X-Mashape-Host","andruxnet-random-famous-quotes.p.mashape.com")
                .header("Content-Type","application/x-www-form-urlencoded")
                .build();
        Log.d("Request", request + "");

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()){

                    Log.d("Response", response + "");

                    final String result = response.body().string();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListenerString.onResponseReceived(result);
                        }
                    });

                    /*Log.d("Response", response + "");
                   JSONArray jsonArray = null;
                    JSONObject jsonObject1 = new JSONObject();

                    try{

                        String result = response.body().string();
                        jsonObject1.put(responseKey, result);

                    } catch (JSONException | IOException e){
                        e.printStackTrace();
                    }*/

                   /* activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListener.onResponseReceived(jsonObject);
                        }
                    });*/
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity, "PostRequest Exception" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void getRequest(final Activity activity, final String url, final ResponseListenerString responseListenerString) throws JSONException {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d("Request", request + "");

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                if (response.isSuccessful()){

                    Log.d("Response", response + "");
                    final String result = response.body().string();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListenerString.onResponseReceived(result);
                        }
                    });
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity, "GetRequest Exception" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public static void getStationSuggestRequest(final Activity activity, final String url, final ResponseListener responseListener
                                                , final String responseKey) throws JSONException {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Log.d("Request", request + "");

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                if (response.isSuccessful()){

                    Log.d("Response", response + "");

                    final JSONObject jsonObject1 = new JSONObject();

                    try{

                        String result = response.body().string();
                        jsonObject1.put(responseKey, result);

                    } catch (JSONException | IOException e){
                        e.printStackTrace();
                    }

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            responseListener.onResponseReceived(jsonObject1);
                        }
                    });
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity, "GetRequest Exception" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
