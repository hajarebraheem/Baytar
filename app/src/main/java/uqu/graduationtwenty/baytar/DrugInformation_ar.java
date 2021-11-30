package uqu.graduationtwenty.baytar;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

public class DrugInformation_ar extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_information_ar);

        database myDbHelper = new database(this);
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

        Cursor d = myDbHelper.query("drug",null);
        TextView near = findViewById(R.id.tv1);
        try {
            if (d.moveToFirst()) {
                do {
                    near.setText("الاسم : "+d.getString(1));
                } while (d.moveToNext());
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"problem",Toast.LENGTH_LONG).show();
        }

    }

    public void main_ar(View view) {
        Intent myintent = new Intent (this, MainActivity_ar.class);
        startActivity(myintent);
    }

    public void english_drug_info(View view) {
        Intent myintent = new Intent (this, DrugInformation_ar.class);
        startActivity(myintent);
    }
}