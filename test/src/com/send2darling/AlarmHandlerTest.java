package com.send2darling;

import android.test.AndroidTestCase;
import org.joda.time.DateTime;

public class AlarmHandlerTest extends AndroidTestCase {

    public void should_return_this_coming_noon_when_given_time_is_morning() {
        DateTime dateTime = new DateTime(2010, 12, 12, 6, 0, 0);
        assertEquals(new AlarmHandler().morning(dateTime), new DateTime(2010, 12, 12, 8, 0, 0));
    }


    public void should_return_the_next_morning_when_given_time_passed_morning() {
        DateTime dateTime = new DateTime(2010, 12, 12, 10, 0, 0);
        assertEquals(new AlarmHandler().morning(dateTime), new DateTime(2010, 12, 13, 8, 0, 0));
    }

}
