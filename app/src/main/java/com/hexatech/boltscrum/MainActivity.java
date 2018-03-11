package com.hexatech.boltscrum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private String[] members;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return handleNavigationHomeItemSelected();
                case R.id.navigation_team:
                    return handleNavigationTeamItemSelected();
                case R.id.navigation_parameters:
                    return handleNavigationParametersItemSelected();
            }
            return false;
        }
    };

    private boolean handleNavigationHomeItemSelected() {
        Bundle bundle = new Bundle();
        bundle.putStringArray("members", members);
        HomeFragment home = new HomeFragment();
        home.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, home).commit();
        return true;
    }

    private boolean handleNavigationTeamItemSelected() {
        Bundle bundle = new Bundle();
        bundle.putStringArray("members", members);
        TeamFragment team = new TeamFragment();
        team.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, team).commit();
        return true;
    }

    private boolean handleNavigationParametersItemSelected() {
        ParametersFragment parameters = new ParametersFragment();
        fragmentManager.beginTransaction().replace(R.id.container, parameters).commit();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.members = getMembersArray();

        this.fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putStringArray("members", members);
        HomeFragment home = new HomeFragment();
        home.setArguments(bundle);
        fragmentManager.beginTransaction().add(R.id.container, home).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private String[] getMembersArray() {
        return new String[] {
                "Camille PIGNON",
                "Benjamin LECLERC",
                "Blandine SEZNEC",
                "Mathias BABIN",
                "Maxime LECLERC",
                "Myriam BRILLANT",
                "Yannick AUFFRET"
        };
    }

}
