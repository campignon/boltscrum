package com.hexatech.boltscrum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LotteryFragment extends Fragment {

    private String[] members;

    private TextView firstParticipantNameTextView;

    public LotteryFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener mOnLotteryButtonClickedListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
        int randomIndex = (int)(Math.random() * members.length);
        firstParticipantNameTextView.setText(members[randomIndex]);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lottery, container, false);

        this.members = getArguments().getStringArray("members");

        this.firstParticipantNameTextView = view.findViewById(R.id.firstParticipantNameTextView);

        Button lotteryButton = view.findViewById(R.id.lotteryButton);
        lotteryButton.setOnClickListener(mOnLotteryButtonClickedListener);

        return view;
    }

}
