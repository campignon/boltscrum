package com.hexatech.boltscrum;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private MediaPlayer timeElapsedSound;

    private int participantNumber;
    private boolean isFinished;
    private long totalMeetingTime;

    private long maxTimePerParticipant = 120 * 1000;
    private boolean isTimeAlmostUp;
    private boolean isTimeElapsed;

    private View.OnClickListener mOnNextParticipantButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nextParticipant();
        }
    };

    private View.OnClickListener mOnEndMeetingButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isFinished) {
                mainChronometer.stop();
                totalChronometer.stop();
                totalMeetingTime = SystemClock.elapsedRealtime() - totalChronometer.getBase();
                isFinished = true;
            }

            Intent intent = new Intent(view.getContext(), MeetingReportActivity.class);
            Bundle meetingDataBundle = new Bundle();
            meetingDataBundle.putInt("participantsNumber", participantNumber);
            meetingDataBundle.putLong("totalTime", totalMeetingTime);
            intent.putExtra("meetingData", meetingDataBundle);
            startActivity(intent);
        }
    };

    private Chronometer.OnChronometerTickListener mOnMainChronometerTickedListener = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer) {
            long currentParticipantTime = SystemClock.elapsedRealtime() - mainChronometer.getBase();
            if (!isTimeAlmostUp && isTimeAlmostUp(currentParticipantTime)) {
                mainChronometer.setTextColor(Color.RED);
                isTimeAlmostUp = true;
            }
            if (!isTimeElapsed && isTimeElapsed(currentParticipantTime)) {
                startTimeElapsedSound();
                isTimeElapsed = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);

        this.participantNumberTextView = findViewById(R.id.participantNumberTextView);

        this.participantNumber = 0;
        this.isFinished = false;
        this.totalMeetingTime = 0;
        this.isTimeAlmostUp = false;
        this.isTimeElapsed = false;

        this.mainChronometer = findViewById(R.id.mainChronometer);
        this.mainChronometer.setOnChronometerTickListener(this.mOnMainChronometerTickedListener);
        this.totalChronometer = findViewById(R.id.totalChronometer);

        Button nextParticipantButton = findViewById(R.id.nextParticipantButton);
        nextParticipantButton.setOnClickListener(this.mOnNextParticipantButtonClickedListener);

        Button endMeetingButton = findViewById(R.id.endMeetingButton);
        endMeetingButton.setOnClickListener(this.mOnEndMeetingButtonClickedListener);

        timeElapsedSound = MediaPlayer.create(MeetingActivity.this, R.raw.timesup);
        timeElapsedSound.setLooping(true);

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
        // We reset the isTimeAlmostUp check
        this.isTimeAlmostUp = false;
        this.mainChronometer.setTextColor(Color.BLACK);

        if (this.isTimeElapsed) {
            timeElapsedSound.stop();
            timeElapsedSound.release();
            this.isTimeElapsed = false;
            timeElapsedSound = MediaPlayer.create(MeetingActivity.this, R.raw.timesup);
        }
    }

    private boolean isTimeAlmostUp(long participantCurrentTime) {
        return (Double.valueOf(participantCurrentTime) / maxTimePerParticipant) > 0.75;
    }

    private boolean isTimeElapsed(long participantCurrentTime) {
        return participantCurrentTime > maxTimePerParticipant;
    }

    private void startTimeElapsedSound() {
        timeElapsedSound.start();
    }
}
