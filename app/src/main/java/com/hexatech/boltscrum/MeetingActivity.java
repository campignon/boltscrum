package com.hexatech.boltscrum;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MeetingActivity extends AppCompatActivity {

    private TextView participantNumberTextView;
    private Chronometer mainChronometer;
    private Chronometer totalChronometer;

    private int participantNumber;

    private View.OnClickListener mOnNextParticipantButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nextParticipant();
        }
    };

    private View.OnClickListener mOnEndMeetingButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            long totalMeetingTime = SystemClock.elapsedRealtime() - totalChronometer.getBase();

            Intent intent = new Intent(view.getContext(), MeetingReportActivity.class);
            Bundle meetingDataBundle = new Bundle();
            meetingDataBundle.putInt("participantsNumber", participantNumber);
            meetingDataBundle.putLong("totalTime", totalMeetingTime);
            intent.putExtra("meetingData", meetingDataBundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        this.participantNumberTextView = findViewById(R.id.participantNumberTextView);

        this.participantNumber = 0;

        this.mainChronometer = findViewById(R.id.mainChronometer);
        this.totalChronometer = findViewById(R.id.totalChronometer);

        Button nextParticipantButton = findViewById(R.id.nextParticipantButton);
        nextParticipantButton.setOnClickListener(this.mOnNextParticipantButtonClickedListener);

        Button endMeetingButton = findViewById(R.id.endMeetingButton);
        endMeetingButton.setOnClickListener(this.mOnEndMeetingButtonClickedListener);

        nextParticipant();
        this.totalChronometer.start();
    }

    private void nextParticipant() {
        // We reset the main chronometer
        this.mainChronometer.setBase(SystemClock.elapsedRealtime());
        this.mainChronometer.start();
        // We increase the participant number
        this.participantNumber++;
        // We change the participant number in the text view
        this.participantNumberTextView.setText(String.valueOf(participantNumber));
    }
}
