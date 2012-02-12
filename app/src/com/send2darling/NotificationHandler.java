package com.send2darling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationHandler {

    private Context context;
    private NotificationManager mNM;
    private SmsService service;

    public NotificationHandler(Context context, NotificationManager mNM, SmsService service) {
        this.context = context;
        this.mNM = mNM;
        this.service = service;
    }

    public void send() {
        CharSequence tickerText = "发条消息给爱人吧！";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(R.drawable.heart, tickerText, when);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        CharSequence contentTitle = "给爱人发条消息吧！";
        CharSequence contentText = "";
        Intent launchSend2DarlingApplication = new Intent(service, Send2Darling.class);
        PendingIntent contentIntent = PendingIntent.getActivity(service, 0, launchSend2DarlingApplication, 0);

        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

        mNM.notify(1, notification);
    }
}
