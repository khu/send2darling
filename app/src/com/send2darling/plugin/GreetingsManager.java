package com.send2darling.plugin;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GreetingsManager {
    private InputStream morning;
    private InputStream noon;
    private InputStream evening;

    public GreetingsManager(InputStream morning, InputStream noon, InputStream evening) {
        this.morning = morning;
        this.noon = noon;
        this.evening = evening;
    }

    public JSONObject buildJson() {
        JSONObject object;
        try {
            object = new JSONObject("{}");
            object.put("morning", buildJsonArray(morning));
            object.put("noon", buildJsonArray(noon));
            object.put("evening", buildJsonArray(evening));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private JSONArray buildJsonArray(InputStream morning) {
        JSONArray jsonArray = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(morning));
            String line = reader.readLine();
            jsonArray = new JSONArray();
            while (line != null) {
                jsonArray.put(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return jsonArray;
    }
}
