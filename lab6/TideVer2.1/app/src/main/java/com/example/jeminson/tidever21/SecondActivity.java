package com.example.jeminson.tidever21;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by jeminson on 2017. 7. 18..
 */

public class SecondActivity extends AppCompatActivity {

    private TextView showLocation;
    private TextView showDate;

    private DataAccessLayer dal = new DataAccessLayer(this);
    Cursor cursor = null;
    SimpleCursorAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        showLocation = (TextView) findViewById(R.id.showLocation);
        showDate = (TextView) findViewById(R.id.showDate);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get data from the MainActivity
        Intent intent = getIntent();
        String value = intent.getExtras().getString("location");
        String date = intent.getExtras().getString("date");

        // Get the default location
        cursor = dal.getTideByLocation(value, date);

        // Set up the adapter for the ListView to display
        adapter = new SimpleCursorAdapter(this, R.layout.listview_items, cursor, new String[]{
                TideSQLiteHelper.DATE,
                TideSQLiteHelper.DAY,
                TideSQLiteHelper.TIME,
                TideSQLiteHelper.PRED_IN_FT,
                TideSQLiteHelper.PRED_IN_CM,
                TideSQLiteHelper.HIGH_LOW
        },
                new int[]{
                        R.id.dateTextView,
                        R.id.dayTextView,
                        R.id.timeTextView,
                        R.id.predInFtView,
                        R.id.predInCmView,
                        R.id.highLowView
                },
                0 );	// no flags

        ListView itemsListView = (ListView)findViewById(R.id.tideListView);
        itemsListView.setAdapter(adapter);

        showLocation.setText(value);
        showDate.setText(date);
    } // End onResume
} // End SecondActivity
