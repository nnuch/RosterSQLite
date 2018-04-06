package com.example.violi.therostersqliteversion10;



import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.violi.therostersqliteversion10.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class RosterActivity extends AppCompatActivity {
    List<Roster> rosterList;
    DatabaseHandler myDatabase;
    ListView listViewRosters;
    RosterAdapter adapter;
    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        // Set Custom action bar
        final ActionBar abCustom = getSupportActionBar();
        //abCustom.setHomeAsUpIndicator(R.drawable.ic_menu);
        abCustom.setDisplayHomeAsUpEnabled(true);
        abCustom.setTitle(R.string.Search);


        listViewRosters =(ListView)findViewById(R.id.listViewRoster);

        //get it from RosterAdapter
        rosterList = new ArrayList<>();
        //open database from DatabaseHandler class
        myDatabase = new DatabaseHandler(this);

        showData();

       //btnViewData = (Button) findViewById(R.id.btnView);
       //ViewData();
    }

    private void showData() {

        Cursor res = myDatabase.getAllData();
        //if the cursor has some data
        if (res.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the roster list
                rosterList.add(new Roster(
                        res.getInt(0),
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                ));
            } while (res.moveToNext());
        }
        //creating the adapter object
        adapter = new RosterAdapter(this,R.layout.layout_list_roster, rosterList);

        //adding the adapter to listview
        listViewRosters.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
