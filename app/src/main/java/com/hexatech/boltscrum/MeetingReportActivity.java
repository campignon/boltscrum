package com.hexatech.boltscrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MeetingReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_report);

        buildReport();

        Button backToHomeButton = findViewById(R.id.backToHomeButton);
        
    }

    private void buildReport() {
        final int participantsNumber = getIntent().getIntExtra("participantsNumber", 1);
        final long totalTime = getIntent().getLongExtra("totalTime", 0);
        final long[] timesArray = getIntent().getLongArrayExtra("timesArray");

        TextView participantsNumberTextView = findViewById(R.id.participantsNumberTextView);
        TextView totalTimeTextView = findViewById(R.id.totalTimeTextView);

        participantsNumberTextView.setText(String.valueOf(participantsNumber));
        totalTimeTextView.setText(String.valueOf(totalTime));
    }

    private long getAverageTime(long[] times) {
        return 0l;
    }
}
