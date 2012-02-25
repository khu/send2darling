package com.send2darling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class AlarmHandler {
    private static final long KEEP_ALIVE_INTERVAL_FOR_1_DAY = 1000 * 60 * 60 * 24;

    public void configure(AlarmManager alarmMgr, SmsService ss) {
        List<DateTime> dateTimes = availableTime();
        for (DateTime dateTime : dateTimes) {
            Intent i = new Intent(ss, SmsService.class);
            i.setAction(SmsService.ACTION_NOTIFICATION);
            i.setData(Uri.parse("timer:" + dateTime.getMillis()));
            PendingIntent pi = PendingIntent.getService(ss, 0, i, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, dateTime.getMillis(), KEEP_ALIVE_INTERVAL_FOR_1_DAY, pi);
        }
    }

    public List<DateTime> availableTime() {
        List<DateTime> dateTimes = new ArrayList<DateTime>();
        dateTimes.add(morning(DateTime.now()));
        dateTimes.add(noon(DateTime.now()));
        dateTimes.add(evening(DateTime.now()));
        //dateTimes.add(DateTime.now().plusSeconds(10));
        return dateTimes;
    }

    public DateTime morning(DateTime now) {
        return nextTime(now, this_morning(now));
    }

    public DateTime noon(DateTime now) {
        return nextTime(now, this_noon(now));
    }

    public DateTime evening(DateTime now) {
        return nextTime(now, this_evening(now));
    }

    private DateTime nextTime(DateTime now, DateTime thisMorning) {
        if (now.isBefore(thisMorning)) {
            return thisMorning;
        } else {
            return thisMorning.plusDays(1);
        }
    }

    private DateTime this_morning(DateTime now) {
        return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 8, 0, 0);
    }

    private DateTime this_noon(DateTime now) {
        return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 12, 0, 0);
    }

    private DateTime this_evening(DateTime now) {
        return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 17, 30, 0);
    }
}
