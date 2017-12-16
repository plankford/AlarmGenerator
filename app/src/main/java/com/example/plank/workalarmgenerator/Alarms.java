package com.example.plank.workalarmgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

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


            }
        });

    }
}
