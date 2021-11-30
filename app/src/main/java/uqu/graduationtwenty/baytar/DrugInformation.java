package uqu.graduationtwenty.baytar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

public class DrugInformation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_information);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        database myDbHelper = new database(this);
        TextView drugInfo = findViewById(R.id.tvDrugInformation1);
        TextView near = findViewById(R.id.tv1);
        Bundle bb = getIntent().getExtras();
        Intent extras = getIntent();

        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        } catch (SQLException sql) {
            throw sql;
        }

        if (bb != null) {
            String drug = bb.getString("drugname");
            String age = extras.getStringExtra("age");
            String gender = extras.getStringExtra("gender");
            String area = extras.getStringExtra("area");

            Cursor d = myDbHelper.query("main_fri", "Name = '" + drug + "' COLLATE NOCASE");

            try {
                if (d.moveToFirst()) {
                    drugInfo.setText(drug.toUpperCase());
                    do {
                        near.setText(d.getString(1));
                    } while (d.moveToNext());
                } else {
                    near.setText("No Information Have Found");
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "problem", Toast.LENGTH_LONG).show();
            }

            /*String type = "insert";
            ServerConnection backgroundWorker = new ServerConnection(this);
            backgroundWorker.execute(type, drug, age);*/

            // Save search drug information in to database for our analytics in future
            //myDbHelper.insertDrugInfoSearch(drug, age, gender, area);
        }


        // Load User Table. Only for Testing database content
        /*List<UserSearch> userSearchesTillNow = myDbHelper.getAllUserSearches();
        System.out.println("<<<<<<<<<<< START>>>>>>>>>>>>");
        for(UserSearch userSearch : userSearchesTillNow){
            System.out.println(userSearch.drug+"::"+userSearch.age+"::"+userSearch.gender+"::"+userSearch.area);
        }
        System.out.println("<<<<<<<<<<< END>>>>>>>>>>>>");*/
    }

    public void main(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    public void back(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);

        EditText etDrugName = findViewById(R.id.etDrugName);
        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);

        Bundle b = new Bundle();

        if (b.isEmpty()) {
            b.putString("drugname", etDrugName.getText().toString());
            myIntent.putExtra("age", spAge.getSelectedItem().toString());
            myIntent.putExtra("gender", spGender.getSelectedItem().toString());
            myIntent.putExtra("area", spArea.getSelectedItem().toString());

            myIntent.putExtras(b);
            startActivity(myIntent);
        } else {
            startActivity(myIntent);
        }
    }
}
