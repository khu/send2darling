package com.send2darling;

import android.test.AndroidTestCase;
import com.send2darling.plugin.GreetingsManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GreetingsManagerTest extends AndroidTestCase {
    private ByteArrayInputStream morning;
    private ByteArrayInputStream evening;
    private ByteArrayInputStream noon;

    String morningStr = "早上好\n" + "今天天气不错哦，我去上班啦，你也早点走，别赖床。";
    String eveningStr = "晚上去哪儿吃饭？";
    String noonStr = "吃了么？\n" + "我准备去楼下吃饭啦，你也要乖乖吃饭哦。";

    public void setUp() throws Exception {
        morning = new ByteArrayInputStream(morningStr.getBytes("UTF-8"));
        noon = new ByteArrayInputStream(noonStr.getBytes("UTF-8"));
        evening = new ByteArrayInputStream(eveningStr.getBytes("UTF-8"));
    }


    public void tearDown() throws Exception {
        morning.close();
        evening.close();
        noon.close();
    }


    public void test_should_return_this_coming_noon_when_given_time_is_morning() throws JSONException {
        GreetingsManager greetingsManager = new GreetingsManager(morning, noon, evening);
        JSONObject jsonObject = greetingsManager.buildJson();
        assertEquals(jsonObject.get("morning"), new JSONArray("[早上好, 今天天气不错哦，我去上班啦，你也早点走，别赖床。]"));
    }

    public void test_should_return_the_json_for_evening() throws JSONException {
        GreetingsManager greetingsManager = new GreetingsManager(morning, noon, evening);
        JSONObject jsonObject = greetingsManager.buildJson();
        assertEquals(jsonObject.get("evening"), new JSONArray("[晚上去哪儿吃饭？]"));
    }


    public void test_the_out_put_json() throws JSONException {
        GreetingsManager greetingsManager = new GreetingsManager(morning, noon, evening);
        JSONObject jsonObject = greetingsManager.buildJson();
        JSONObject actual = new JSONObject("{morning:[早上好, 今天天气不错哦，我去上班啦，你也早点走，别赖床。], noon:[吃了么？, 我准备去楼下吃饭啦，你也要乖乖吃饭哦。], evening:[晚上去哪儿吃饭？]}");

        assertEquals(jsonObject.get("morning"), actual.get("morning"));
        assertEquals(jsonObject.get("evening"), actual.get("evening"));
        assertEquals(jsonObject.get("noon"), actual.get("noon"));
    }


}
