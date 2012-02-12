package com.send2darling;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AlarmHandlerTest {
    @Test
    public void should_return_this_coming_noon_when_given_time_is_morning() {
        DateTime dateTime = new DateTime(2010, 12, 12, 6, 0, 0);
        assertThat(new AlarmHandler().morning(dateTime), is(new DateTime(2010, 12, 12, 8, 0, 0)));
    }

    @Test
    public void should_return_the_next_morning_when_given_time_passed_morning() {
        DateTime dateTime = new DateTime(2010, 12, 12, 10, 0, 0);
        assertThat(new AlarmHandler().morning(dateTime), is(new DateTime(2010, 12, 13, 8, 0, 0)));
    }

    @Test
    public void should_return_the_next_morning_when_given_time_is_moring() {
    }

}
