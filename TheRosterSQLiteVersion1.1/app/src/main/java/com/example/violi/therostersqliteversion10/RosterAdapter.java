package com.example.violi.therostersqliteversion10;


import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.violi.therostersqliteversion10.database.DatabaseHandler;
import java.util.List;


public class RosterAdapter extends ArrayAdapter<Roster> {
    Context context;
    int listLayoutRes;
    List<Roster>  rosterList;
    DatabaseHandler myDatabase;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    public RosterAdapter(@NonNull Context context, int listLayoutRes, List<Roster> rosterList) {
        super(context, listLayoutRes, rosterList);


        this.context = context;
        this.listLayoutRes = listLayoutRes;
        this.rosterList = rosterList;
       // this.myDatabase = myDatabase;


    }


    @NonNull
    @Override
    public android.view.View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listLayoutRes, null);

        //getting data of specified position
        final Roster roster = rosterList.get(position);

        //getting view here
        TextView textViewID = view.findViewById(R.id.textViewID);
        TextView textViewFirstName = view.findViewById(R.id.textViewFirstName);
        TextView textViewLastName = view.findViewById(R.id.textViewLastName);
        TextView textViewBirthday = view.findViewById(R.id.textViewBirthday);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        TextView textViewPosition= view.findViewById(R.id.textViewPosition);

        //adding data here
        textViewID.setId(roster.getId());
        textViewID.setVisibility(View.INVISIBLE);
        textViewFirstName.setText(roster.getFirstName());
        textViewLastName.setText(roster.getLastName());
        textViewBirthday.setText(roster.getBirthDay());
        textViewTeam.setText(roster.getTeam());
        textViewPosition.setText(roster.getPosition());



        //getting view button
        Button buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRoster(roster);
                Toast.makeText(context, "Next steps for implementation!", Toast.LENGTH_LONG).show();
            }
        });

        Button buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRoster(roster);
            }
        });

        return view;
    }


    public void deleteRoster(final Roster roster) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_delete, null);
        builder.setView(view);

        //builder.setTitle("Are you sure?");
        final TextView tvID = view.findViewById(R.id.tvID);
        final TextView inputFirstName = (TextView) view.findViewById(R.id.inputFirstName);
        final TextView inputLastName = (TextView) view.findViewById(R.id.inputLastName);

        tvID.setId(roster.getId());
        inputFirstName.setText(roster.getFirstName());
        inputLastName.setText(roster.getLastName());

        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Data was successfully deleted! "+ tvID.getText().toString(), Toast.LENGTH_LONG).show();
               /* Integer deletedRow =   myDatabase.deleteData(tvID.getText().toString());
                  if (deletedRow > 0) {
                        Toast.makeText(context, "Data was successfully deleted! ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Data wasn't deleted", Toast.LENGTH_LONG).show();
                    }*/

              // reloadRosterFromDatabase();
            }
        });

        Button btnCancel= view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final AlertDialog dialog = builder.create();
        builder.setCancelable(true);
        dialog.show();


    }



    public void updateRoster(final Roster roster) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_update, null);
        builder.setView(view);
       // final EditText edID = (EditText) view.findViewById(R.id.edID);
        final EditText edFirstName = (EditText) view.findViewById(R.id.inputFirstName);
        final EditText edLastName = (EditText) view.findViewById(R.id.inputLastName);

        final Spinner spinnerTeam = (Spinner) view.findViewById(R.id.spinnerTeam);

        edFirstName.setText(roster.getFirstName());
        edLastName.setText(roster.getLastName());


        //spinnerEyeColor.setSelection(Integer.parseInt(roster.getEyeColor()));
        // mDisplayDate.setText(roster.getBirthDay());
        //radioGroupShirt.check(roster.getUsShirt());

        final AlertDialog dialog = builder.create();
        builder.setCancelable(true);
        dialog.show();

/*
        view.findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnSaveString = edFirstName.getText().toString();
                String lnSaveString = edLastName.getText().toString();
                if (checkBox.isChecked()) {
                    String cbSaveString = checkBox.getText().toString();
                }
                String spSaveString = spinnerEyeColor.getSelectedItem().toString();
                String birthdayString =  mDisplayDate.getText().toString();
                String usShirtSaveString =  ((RadioButton)view.findViewById(radioGroupShirt.getCheckedRadioButtonId())).getText().toString();
                String sbPantString = tvPantString.getText().toString();
                String sbShirtString = tvShirtString.getText().toString();
                String sbShoeString = tvShoeString.getText().toString();


                boolean isInserted = myDatabase.insertData(fnSaveString,lnSaveString,
                        cbSaveString,spSaveString,birthdayString,usShirtSaveString,sbPantString,
                        sbShirtString,sbShoeString);

                //Checking by showing messages
                if (isInserted == true) {
                    //ClearData();
                    Toast.makeText(context, "Data was successfully added! ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Data wasn't added into database", Toast.LENGTH_LONG).show();
                }
                reloadRosterFromDatabase();
                dialog.dismiss();
            }
        });

*/

    }




    private void reloadRosterFromDatabase() {
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
        notifyDataSetChanged();
    }




}





