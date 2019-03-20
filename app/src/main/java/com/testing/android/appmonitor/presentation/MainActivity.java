package com.testing.android.appmonitor.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.testing.android.appmonitor.R;
import com.testing.android.appmonitor.presentation.eventslist.EventsListFragment;

final public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EventsListFragment.newInstance())
                    .commit();
        }
    }
}
