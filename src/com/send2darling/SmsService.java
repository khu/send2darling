package com.send2darling;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SmsService extends Service {
    private NotificationManager mNM;


    private static final String ACTION_START = "com.send2darling.START";
    private static final String ACTION_STOP = "com.send2darling.STOP";
    public static final String ACTION_NOTIFICATION = "com.send2darling.NOTIFICATION";
    private int NOTIFICATION = 1;


    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public static void launchService(Context ctx) {
        Intent i = new Intent(ctx, SmsService.class);
        i.setAction(ACTION_START);
        ctx.startService(i);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals(ACTION_NOTIFICATION)) {
            new NotificationHandler(getApplicationContext(), mNM, this).send();
        }

        if (action.equals(ACTION_START)) {
            new AlarmHandler().configure((AlarmManager) getSystemService(ALARM_SERVICE), this);
        }
    }

    @Override
    public void onDestroy() {
        mNM.cancel(NOTIFICATION);
        Toast.makeText(this, "stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

