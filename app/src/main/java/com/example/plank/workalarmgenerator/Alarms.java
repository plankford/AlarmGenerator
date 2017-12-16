package com.example.plank.workalarmgenerator;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.id;

public class Alarms extends AppCompatActivity {

    //Button for checking if the event exists on the use calendar
    private Button mCheckCalendarButton;

    //Button for setting the alarm that will be used
    private Button mSetAlarmButton;

    //Switch used for enabling or disabling the event
    private Switch mAlarmEnable;

    //User designated event that will be used on the calendar
    private EditText mAlarmEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        //Get a reference to the test button for checking the calendar for the event
        mCheckCalendarButton = (Button)findViewById(R.id.test_button);
        mCheckCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check the user calendar for the event that is stored in the
                //edit text box
                //checkCalendarEvent();

            }
        });

    }

    public static void checkCalendarEvent(Context context) throws ParseException {

        ContentResolver contentResolver = context.getContentResolver();

        final Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"),
                (new String[] { "_id", "displayName", "selected"}), null, null, null);

        while (cursor.moveToNext()) {
            final String _id = cursor.getString(0);
            final String displayName = cursor.getString(1);
            final Boolean selected = !cursor.getString(2).equals("0");

            System.out.println("Id: " + _id + " Display Name: " + displayName + " Selected: " +
                                selected);
        }

        //get the events from the calendar using the calendar ID
        Uri.Builder builder = Uri.parse("content://calendat/instances/when").buildUpon();
        long now = new Date().getTime();
        ContentUris.appendId(builder, now - DateUtils.WEEK_IN_MILLIS);
        ContentUris.appendId(builder, now + DateUtils.WEEK_IN_MILLIS);

        Cursor eventCursor = contentResolver.query(builder.build(),
                new String[] { "title", "begin", "end", "allDay"}, "Calendars._id=" + id,
                null, "startDay ASC, startMinute ASC");

        while (eventCursor.moveToNext()) {
            final String title = eventCursor.getString(0);
            final Date begin = new Date(eventCursor.getLong(1));
            final Date end = new Date(eventCursor.getLong(2));
            final Boolean allDay = !eventCursor.getString(3).equals("0");

            System.out.println("Title: " + title + " Begin: " + begin + " End: " + end +
                                " All Day: " + allDay);
        }

//        ContentResolver contentResolver = context.getContentResolver();
//        Calendar calendar = Calendar.getInstance();
//        String dateStart = "dateStart";
//        String dateEnd = "dateEnd";
//        String startTime;
//
//        SimpleDateFormat displayFormatter = new SimpleDateFormat("MMMM dd, yyyy (EEEE)");
//
//        startTime = displayFormatter.format(calendar.getTime());
//
//        SimpleDateFormat startFormatter = new SimpleDateFormat("MM/dd/yy");
//        String dateString = startFormatter.format(calendar.getTime());
//
//        long after = calendar.getTimeInMillis();
//        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss MM/dd/yy");
//
//        //Create the end of day time
//        Calendar endOfDay = Calendar.getInstance();
//        Date dateCC = formater.parse("23:59:59 " + dateString);
//        endOfDay.setTime(dateCC);
//
//        //Create the cursor
//        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.calendar/events"),
//                (new String[]{"calendar_id", "title", "description", "dateStart", "dateEnd",
//                        "eventTimezone", "eventLocation"}), "(" + dateStart + ">" + after + " and " + dateEnd
//                        + "<" + endOfDay.getTimeInMillis() + ")", null, "dateStart ASC");
//
//        //googleCalendar = new ArrayList<GoogleCalendar>();
    }
}
