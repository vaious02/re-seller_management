package management.stock.reseller.stylhunt.re_seller_management.Http;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;


/**
 * Created by Admin on 3/29/2016.
 */
public class HTTPEngine {
    private static HTTPEngine instance;

    public static HTTPEngine getInstance() {
        if (instance == null)
            instance = new HTTPEngine();
        return instance;
    }


    public enum RequestMethod {
        METHOD_GET,
        METHOD_POST
    }
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
    private HttpURLConnection connection = null;

    private HTTPEngine() {

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private <T extends BaseDao> Call loadUrlwithTreadPool(RequestMethod method,
                                                          String url,
                                                          Map<String, String> postData,
                                                          final HTTPEngineListener<T> listener,
                                                          final Class<T> tClass) {
        Request request;
        if (method == RequestMethod.METHOD_POST) {
            Map<String, String> postParams = new HashMap<>();
            if (postData != null) {
                for (Map.Entry<String, String> entry : postData.entrySet()) {
                    postParams.put(entry.getKey(), entry.getValue());
                }
            }
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType,mapToPostString(postParams));

            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }



        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
//        client.setConnectTimeout(5, TimeUnit.MINUTES);
//        client.setReadTimeout(5, TimeUnit.MINUTES);
        client.setRetryOnConnectionFailure(true);
        client.setFollowRedirects(true);
        Call call = client.newCall(request);

        HTTPRequestData data = new HTTPRequestData();
        data.url = url;
        data.method = method;
        data.postData = postData;
        data.call = call;

        /*try {
            URL urll = new URL(data.url);
            connection = (HttpURLConnection) urll.openConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }*/



        HTTPRequestTask task = new HTTPRequestTask(new HTTPRequestListener() {
            @Override
            public void onMessageReceived(int statusCode, String messageResponse) {
                if (listener != null) {

                    try {
                        T data = gson.fromJson(messageResponse, tClass);
                        listener.onResponse(data, messageResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            listener.onResponse(null, messageResponse);
                        } catch (Exception e2) {
                            listener.onResponse(null, "");
                        }
                    }
                }
            }

            @Override
            public void onMessageError(int statusCode, String message) {
                if (listener != null) {
                    HTTPEngineException error = new HTTPEngineException("Cannot load data", statusCode);
                    listener.onFailure(null, "e.getMessage()", error);
                }
            }
        });


        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data);


        //execute(data)
        //executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, data)
        //executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data)

        return call;
    }


    public <T extends BaseDao> Call loadPostUrlwithTreadPool(String url, Map<String, String> postData, final HTTPEngineListener<T> listener, final Class<T> tClass) {
        return loadUrlwithTreadPool(RequestMethod.METHOD_POST, url, postData, listener, tClass);
    }

    private String mapToPostString(Map<String, String> data) {
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (content.length() > 0) {
                content.append('&');
            }
            try {
                content.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
                        .append('=')
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return content.toString();
    }


}


