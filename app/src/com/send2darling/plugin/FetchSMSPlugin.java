package com.send2darling.plugin;

import android.content.res.Resources;
import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.send2darling.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class FetchSMSPlugin extends Plugin {
    public final String ACTION_SMS_CONTENT = "Fetch";


    @Override
    public PluginResult execute(String action, JSONArray arg1, String callbackId) {
        PluginResult result = new PluginResult(PluginResult.Status.INVALID_ACTION);
        Resources resources = this.webView.getContext().getResources();

        if (action.equals(ACTION_SMS_CONTENT)) {
            try {
                thingy(resources);
                JSONObject message = new JSONObject("{morning:'good morning',noon:'good afternoon',everning:'everning'}");
                result = new PluginResult(PluginResult.Status.OK, message);
            } catch (JSONException ex) {
                result = new PluginResult(PluginResult.Status.JSON_EXCEPTION, ex.getMessage());
            }
        }

        return result;
    }

    private JSONObject thingy(Resources resources) throws RuntimeException {
        InputStream morning = resources.openRawResource(R.raw.morning);
        InputStream noon = resources.openRawResource(R.raw.noon);
        InputStream evening = resources.openRawResource(R.raw.evening);
        try {
            GreetingsManager greetingsManager = new GreetingsManager(morning, noon, evening);
            return greetingsManager.buildJson();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                morning.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                noon.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                evening.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
