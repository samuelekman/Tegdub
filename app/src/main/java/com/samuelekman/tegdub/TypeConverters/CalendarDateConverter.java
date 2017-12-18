package com.samuelekman.tegdub.TypeConverters;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Calendar;

/**
 * Created by samuel on 2017-12-18.
 */

public class CalendarDateConverter {
    @TypeConverter
    public static Long fromDate(Calendar date) {
        if (date == null) {
            return null;
        }

        return (date.getTimeInMillis());
    }

    @TypeConverter
    public static Calendar toDate(Long millis){
        if (millis == null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        return c;
    }



}