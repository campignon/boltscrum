package com.hexatech.boltscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeetingReportActivity extends AppCompatActivity {

    private static final double MILLISECONDS_PER_SECOND = 1000d;
    private static final double SECONDS_PER_MINUTE = 60d;

    private View.OnClickListener mOnBackToHomeButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_report);

        buildReport();

        Button backToHomeButton = findViewById(R.id.backToHomeButton);
        backToHomeButton.setOnClickListener(mOnBackToHomeButtonClickedListener);
    }

    private void buildReport() {
        final Bundle meetingData = getIntent().getBundleExtra("meetingData");
        final int participantsNumber = meetingData.getInt("participantsNumber");
        final long totalTime = meetingData.getLong("totalTime");

        TextView participantsNumberTextView = findViewById(R.id.participantsNumberTextView);
        TextView totalTimeTextView = findViewById(R.id.totalTimeTextView);
        TextView averageTimeTextView = findViewById(R.id.averageTimeTextView);

        String totalTimeFmtStr = getMillisecondsToFmtString(totalTime);
        String averageTimeFmtStr = getAverageTimeAsFmtString(totalTime, participantsNumber);

        participantsNumberTextView.setText(String.valueOf(participantsNumber));
        totalTimeTextView.setText(totalTimeFmtStr);
        averageTimeTextView.setText(averageTimeFmtStr);
    }

    private String getMillisecondsToFmtString(long milliseconds) {
        double totalTimeSeconds = milliseconds / MILLISECONDS_PER_SECOND;
        double totalTimeMinutes = Math.floor(totalTimeSeconds / SECONDS_PER_MINUTE);
        totalTimeSeconds -= totalTimeMinutes * SECONDS_PER_MINUTE;
        totalTimeSeconds = Math.floor(totalTimeSeconds);
        return String.format("%02d:%02d", (int) totalTimeMinutes, (int) totalTimeSeconds);
    }

    private String getAverageTimeAsFmtString(long totalTime, int participantsNumber) {
        long averageTimeMilliseconds = totalTime / participantsNumber;
        return getMillisecondsToFmtString(averageTimeMilliseconds);
    }
}
