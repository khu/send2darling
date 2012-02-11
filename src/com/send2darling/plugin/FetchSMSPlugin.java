package com.send2darling.plugin;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchSMSPlugin extends Plugin {
    public final String ACTION_SMS_CONTENT = "Fetch";

    @Override
    public PluginResult execute(String action, JSONArray arg1, String callbackId) {
        PluginResult result = new PluginResult(PluginResult.Status.INVALID_ACTION);

        if (action.equals(ACTION_SMS_CONTENT)) {
            try {
                result = new PluginResult(PluginResult.Status.OK, new JSONObject("{morning:'good morning',noon:'good afternoon',everning:'everning'}"));
            } catch (JSONException ex) {
                result = new PluginResult(PluginResult.Status.JSON_EXCEPTION, ex.getMessage());
            }
        }

        return result;
    }
}
