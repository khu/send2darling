package com.send2darling;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.phonegap.DroidGap;

public class Send2Darling extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmsService.launchService(this.getApplicationContext());
        super.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
