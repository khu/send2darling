package com.send2darling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

public class AlarmHandler {
    private SmsService service;
    private AlarmManager alarmMgr;
    private AlarmTimer timer;


    public AlarmHandler(SmsService service, AlarmManager manager, AlarmTimer timer) {
        this.service = service;
        this.alarmMgr = manager;
        this.timer = timer;
    }

    public void setupAlarm() {
        timer.configure(alarmMgr, service);
    }
}
