package Api;

import domain.Matches;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;

import java.util.ArrayList;

import java.util.List;

public class Api {


    public String request(String endpoint) {

        String result = "";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.football-data.org/v2/" + endpoint + "?dateFrom=2022-05-10&dateTo=2022-05-19")
                .get()
                .addHeader("X-Auth-Token", System.getenv("TOKEN"))
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Matches> setEvents() {
        Api api = new Api();
        String body = api.request("matches");

        System.out.println(body);
        Gson gson = new Gson();
        JsonObject jsonObject;

        jsonObject = gson.fromJson(body, JsonObject.class);
        Type eventListType = new TypeToken<ArrayList<Matches>>() {
        }.getType();
        List<Matches> TodayMatches = gson.fromJson((jsonObject.get("matches")), eventListType);

        return TodayMatches;
    }
}

