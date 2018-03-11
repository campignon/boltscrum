package com.hexatech.boltscrum;

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

    private int participantNumber;
    private List<Long> timesList;

    private View.OnClickListener mOnNextParticipantButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nextParticipant();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        this.participantNumberTextView = findViewById(R.id.participantNumberTextView);

        this.participantNumber = 0;

        this.timesList = new ArrayList<>();

        this.mainChronometer = findViewById(R.id.mainChronometer);

        Button nextParticipantButton = findViewById(R.id.nextParticipantButton);
        nextParticipantButton.setOnClickListener(this.mOnNextParticipantButtonClickedListener);

        nextParticipant();
    }

    private void nextParticipant() {
        // We increase the participant number
        this.participantNumber++;
        // We change the participant number in the text view
        this.participantNumberTextView.setText(String.valueOf(participantNumber));
        // We reset the main chronometer
        this.mainChronometer.setBase(SystemClock.elapsedRealtime());
        this.mainChronometer.start();
    }
}
