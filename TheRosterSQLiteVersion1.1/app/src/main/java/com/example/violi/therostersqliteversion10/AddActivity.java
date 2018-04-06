package com.example.violi.therostersqliteversion10;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.violi.therostersqliteversion10.database.DatabaseHandler;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    DatabaseHandler myDatabase;

    private EditText edFirstName;
    private EditText edLastName;

    // Spinner NHL team dropdown
    private Spinner  spinnerTeam;

    // Birthday Date-Calender
    private EditText mDisplayDate;
    private int day, month, year;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "MainActivity";

    // Position
    private RadioGroup radioGroupPosition;
    private RadioButton radioButtonGoalie;
    private RadioButton radioButtonDefensemen;
    private RadioButton radioButtonCenter;
    private RadioButton radioButtonRightWing;
    private RadioButton radioButtonLeftWing;

    //Button
    private Button btnSave;

    //String save into SQLite
    private String firstNameString, lastNameString, teamString,birthdayString ,positionString;
    private int teamValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Set Custom action bar
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.Add);

        //connect SQLite databaseHandler.class
        myDatabase = new DatabaseHandler(this);

        //Connect ByID
        edFirstName = (EditText) findViewById(R.id.inputFirstName);
        edLastName = (EditText) findViewById(R.id.inputLastName);

        radioGroupPosition = (RadioGroup) findViewById(R.id.radioGroupPosition);
        radioButtonGoalie = (RadioButton) findViewById(R.id.radioButton_Goalie);
        radioButtonDefensemen = (RadioButton) findViewById(R.id.radioButton_Defensemen);
        radioButtonCenter = (RadioButton) findViewById(R.id.radioButton_Center);
        radioButtonRightWing = (RadioButton) findViewById(R.id.radioButton_RightWing);
        radioButtonLeftWing = (RadioButton) findViewById(R.id.radioButton_LeftWing);

        SpinnerEyeColor();
        DateCalender();
    }

    public void SpinnerEyeColor() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.team, android.R.layout.simple_spinner_item);
        // initializing spinner,
        // Resources - string-array name="team"
        spinnerTeam = (Spinner) this.findViewById(R.id.spinnerTeam);
        spinnerTeam.setAdapter(adapter);
        spinnerTeam.setSelection(teamValue);

    }

    public void DateCalender() {
        // 3.5 DatePickerDialog
        mDisplayDate = (EditText) findViewById(R.id.etDisplayDate);
        //For Calender Button
        Button btnGoCalender = (Button) findViewById(R.id.btnCalender);
        btnGoCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        // Set date Calender
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd.yyy:" + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void doSave(View view) {

        //To check all input fields are not empty
        firstNameString = edFirstName.getText().toString();
        lastNameString = edLastName.getText().toString();
        birthdayString =  mDisplayDate.getText().toString();
        teamString = spinnerTeam.getSelectedItem().toString();
        positionString =  ((RadioButton)this.findViewById(radioGroupPosition.getCheckedRadioButtonId())).getText().toString();

            //if true perform AddData(), if not show a toast message
            if ((edFirstName.length() != 0) && (edLastName.length() != 0)) {
                AddData();
            } else {
                Toast.makeText(this, "Firstname and lastname are required! ", Toast.LENGTH_LONG).show();
            }
    }

    public void AddData() {
        //declare boolean variable to check if insert data successfully
        //insertData method for DatabaseHandler.java
        boolean isInserted = myDatabase.insertData(firstNameString, lastNameString,birthdayString, teamString, positionString);
        //Checking by showing messages
        if (isInserted == true) {
            //ClearData();
            edFirstName.setText("");
            edLastName.setText("");
            mDisplayDate.setText("");
            spinnerTeam.setAdapter(null);
            radioGroupPosition.clearCheck();

            Toast.makeText(this, "Data was successfully added! ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data wasn't added into database", Toast.LENGTH_LONG).show();
        }
    }

    // This method when user click <- then it will go back to previous home.
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
