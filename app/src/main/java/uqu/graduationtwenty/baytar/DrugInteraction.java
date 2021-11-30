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

import java.io.IOException;

public class DrugInteraction extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_interaction);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        database myDbHelper = new database(this);
        TextView deduction = findViewById(R.id.tvDIDeduction);
        TextView tvReasonView = findViewById(R.id.tvReason);
        TextView tvReasonView2 = findViewById(R.id.tvReason2);
        TextView tvReasonView3 = findViewById(R.id.tvReason3);
        TextView tvReasonView4 = findViewById(R.id.tvReason4);
        TextView tvReasonView5 = findViewById(R.id.tvReason5);
        TextView tvReasonView6 = findViewById(R.id.tvReason6);
        TextView tvReason = findViewById(R.id.tvReasonResult);
        TextView tvReason2 = findViewById(R.id.tvReasonResult2);
        TextView tvReason3 = findViewById(R.id.tvReasonResult3);
        TextView tvReason4 = findViewById(R.id.tvReasonResult4);
        TextView tvReason5 = findViewById(R.id.tvReasonResult5);
        TextView tvReason6 = findViewById(R.id.tvReasonResult6);

        Bundle bb = getIntent().getExtras();

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
            String dn1 = bb.getString("drugname1");
            String dn2 = bb.getString("drugname2");
            String dn3 = bb.getString("drugname3");
            String dn4 = bb.getString("drugname4");

            Cursor a = myDbHelper.query("drug_interaction", "Name = '" + dn1 + "' COLLATE NOCASE AND DrugInteractionName = '" + dn2 + "'" +
                    "OR Name = '" + dn2 + "' AND DrugInteractionName = '" + dn1 + "'");
            Cursor b = myDbHelper.query("drug_interaction", "Name = '" + dn1 + "' AND DrugInteractionName = '" + dn3 + "' " +
                    "OR Name = '" + dn3 + "' AND DrugInteractionName = '" + dn1 + "'");
            Cursor c = myDbHelper.query("drug_interaction", "Name = '" + dn1 + "' AND DrugInteractionName = '" + dn4 + "' " +
                    "OR Name = '" + dn4 + "' AND DrugInteractionName = '" + dn1 + "'");

            Cursor a_ = myDbHelper.query("drug_interaction", "Name = '" + dn2 + "' AND DrugInteractionName = '" + dn3 + "' " +
                    "OR Name = '" + dn3 + "' AND DrugInteractionName = '" + dn2 + "'");
            Cursor b_ = myDbHelper.query("drug_interaction", "Name = '" + dn2 + "' AND DrugInteractionName = '" + dn4 + "' " +
                    "OR Name = '" + dn4 + "' AND DrugInteractionName = '" + dn2 + "'");

            Cursor a_3 = myDbHelper.query("drug_interaction", "Name = '" + dn3 + "' AND DrugInteractionName = '" + dn4 + "' " +
                    "OR Name = '" + dn4 + "' AND DrugInteractionName = '" + dn3 + "'");

            try {
                if (a.moveToFirst() && b.moveToFirst() && c.moveToFirst() && a_.moveToFirst() && b_.moveToFirst() && a_3.moveToFirst()) {
                    do {
                        tvReason.setText(a.getString(3));
                        tvReason2.setText(b.getString(3));
                        tvReason3.setText(c.getString(3));
                        tvReason4.setText(a_.getString(3));
                        tvReason5.setText(b_.getString(3));
                        tvReason6.setText(a_3.getString(3));
                    } while (a.moveToNext() && b.moveToNext() && c.moveToNext() && a_.moveToNext() && b_.moveToNext() && a_3.moveToNext());
                }
                else {
                    if (a.moveToFirst() && b.moveToFirst() && a_.moveToFirst()) {
                        do {
                            tvReason.setText(a.getString(3));
                            tvReason2.setText(b.getString(3));
                            tvReason3.setText(a_.getString(3));
                            tvReasonView4.setVisibility(View.GONE);
                            tvReason4.setVisibility(View.GONE);
                            tvReasonView5.setVisibility(View.GONE);
                            tvReason5.setVisibility(View.GONE);
                            tvReasonView6.setVisibility(View.GONE);
                            tvReason6.setVisibility(View.GONE);
                        } while (a.moveToNext() && b.moveToNext() && a_.moveToNext());
                    } else {
                        if (a_.moveToFirst() && b_.moveToFirst() && a_3.moveToFirst()) {
                            do {
                                tvReason.setText(a_.getString(3));
                                tvReason2.setText(b_.getString(3));
                                tvReason3.setText(a_3.getString(3));
                                tvReasonView4.setVisibility(View.GONE);
                                tvReason4.setVisibility(View.GONE);
                                tvReasonView5.setVisibility(View.GONE);
                                tvReason5.setVisibility(View.GONE);
                                tvReasonView6.setVisibility(View.GONE);
                                tvReason6.setVisibility(View.GONE);
                            } while (a_.moveToNext() && b_.moveToNext() && a_3.moveToNext());
                        } else {
                            if (b.moveToFirst() && c.moveToFirst() && a_3.moveToFirst()) {
                                do {
                                    tvReason.setText(b.getString(3));
                                    tvReason2.setText(c.getString(3));
                                    tvReason3.setText(a_3.getString(3));
                                    tvReasonView4.setVisibility(View.GONE);
                                    tvReason4.setVisibility(View.GONE);
                                    tvReasonView5.setVisibility(View.GONE);
                                    tvReason5.setVisibility(View.GONE);
                                    tvReasonView6.setVisibility(View.GONE);
                                    tvReason6.setVisibility(View.GONE);
                                } while (b.moveToNext() && c.moveToNext() && a_3.moveToNext());
                            } else {
                                if (b.moveToFirst() && a_.moveToFirst()) {
                                    do {
                                        tvReason.setText(b.getString(3));
                                        tvReason2.setText(a_.getString(3));
                                        tvReasonView3.setVisibility(View.GONE);
                                        tvReason3.setVisibility(View.GONE);
                                        tvReasonView4.setVisibility(View.GONE);
                                        tvReason4.setVisibility(View.GONE);
                                        tvReasonView5.setVisibility(View.GONE);
                                        tvReason5.setVisibility(View.GONE);
                                        tvReasonView6.setVisibility(View.GONE);
                                        tvReason6.setVisibility(View.GONE);
                                    } while (b.moveToNext() && a_.moveToNext());
                                } else {
                                    if (a.moveToFirst() && b.moveToFirst()) {
                                        do {
                                            tvReason.setText(a.getString(3));
                                            tvReason2.setText(b.getString(3));
                                            tvReasonView3.setVisibility(View.GONE);
                                            tvReason3.setVisibility(View.GONE);
                                            tvReasonView4.setVisibility(View.GONE);
                                            tvReason4.setVisibility(View.GONE);
                                            tvReasonView5.setVisibility(View.GONE);
                                            tvReason5.setVisibility(View.GONE);
                                            tvReasonView6.setVisibility(View.GONE);
                                            tvReason6.setVisibility(View.GONE);
                                        } while (a.moveToNext() && b.moveToNext());
                                    } else {
                                        if (a.moveToFirst() && a_.moveToFirst()) {
                                            do {
                                                tvReason.setText(a.getString(3));
                                                tvReason2.setText(a_.getString(3));
                                                tvReasonView3.setVisibility(View.GONE);
                                                tvReason3.setVisibility(View.GONE);
                                                tvReasonView4.setVisibility(View.GONE);
                                                tvReason4.setVisibility(View.GONE);
                                                tvReasonView5.setVisibility(View.GONE);
                                                tvReason5.setVisibility(View.GONE);
                                                tvReasonView6.setVisibility(View.GONE);
                                                tvReason6.setVisibility(View.GONE);
                                            } while (a.moveToNext() && a_.moveToNext());
                                        } else {
                                            if (a_.moveToFirst() && a_3.moveToFirst()) {
                                                do {
                                                    tvReason.setText(a_.getString(3));
                                                    tvReason2.setText(a_3.getString(3));
                                                    tvReasonView3.setVisibility(View.GONE);
                                                    tvReason3.setVisibility(View.GONE);
                                                    tvReasonView4.setVisibility(View.GONE);
                                                    tvReason4.setVisibility(View.GONE);
                                                    tvReasonView5.setVisibility(View.GONE);
                                                    tvReason5.setVisibility(View.GONE);
                                                    tvReasonView6.setVisibility(View.GONE);
                                                    tvReason6.setVisibility(View.GONE);
                                                } while (a_.moveToNext() && a_3.moveToNext());
                                            } else {
                                                if (a_.moveToFirst() && b_.moveToFirst()) {
                                                    do {
                                                        tvReason.setText(a_.getString(3));
                                                        tvReason2.setText(b_.getString(3));
                                                        tvReasonView3.setVisibility(View.GONE);
                                                        tvReason3.setVisibility(View.GONE);
                                                        tvReasonView4.setVisibility(View.GONE);
                                                        tvReason4.setVisibility(View.GONE);
                                                        tvReasonView5.setVisibility(View.GONE);
                                                        tvReason5.setVisibility(View.GONE);
                                                        tvReasonView6.setVisibility(View.GONE);
                                                        tvReason6.setVisibility(View.GONE);
                                                    } while (a_.moveToNext() && b_.moveToNext());
                                                } else {
                                                    if (a_3.moveToFirst() && b_.moveToFirst()) {
                                                        do {
                                                            tvReason.setText(a_3.getString(3));
                                                            tvReason2.setText(b_.getString(3));
                                                            tvReasonView3.setVisibility(View.GONE);
                                                            tvReason3.setVisibility(View.GONE);
                                                            tvReasonView4.setVisibility(View.GONE);
                                                            tvReason4.setVisibility(View.GONE);
                                                            tvReasonView5.setVisibility(View.GONE);
                                                            tvReason5.setVisibility(View.GONE);
                                                            tvReasonView6.setVisibility(View.GONE);
                                                            tvReason6.setVisibility(View.GONE);
                                                        }
                                                        while (a_3.moveToNext() && b_.moveToNext());
                                                    } else {
                                                        if (a_3.moveToFirst() && b.moveToFirst()) {
                                                            do {
                                                                tvReason.setText(b.getString(3));
                                                                tvReason2.setText(a_3.getString(3));
                                                                tvReasonView3.setVisibility(View.GONE);
                                                                tvReason3.setVisibility(View.GONE);
                                                                tvReasonView4.setVisibility(View.GONE);
                                                                tvReason4.setVisibility(View.GONE);
                                                                tvReasonView5.setVisibility(View.GONE);
                                                                tvReason5.setVisibility(View.GONE);
                                                                tvReasonView6.setVisibility(View.GONE);
                                                                tvReason6.setVisibility(View.GONE);
                                                            }
                                                            while (a_3.moveToNext() && b.moveToNext());
                                                        } else {
                                                            if (c.moveToFirst() && b.moveToFirst()) {
                                                                do {
                                                                    tvReason.setText(b.getString(3));
                                                                    tvReason2.setText(c.getString(3));
                                                                    tvReasonView3.setVisibility(View.GONE);
                                                                    tvReason3.setVisibility(View.GONE);
                                                                    tvReasonView4.setVisibility(View.GONE);
                                                                    tvReason4.setVisibility(View.GONE);
                                                                    tvReasonView5.setVisibility(View.GONE);
                                                                    tvReason5.setVisibility(View.GONE);
                                                                    tvReasonView6.setVisibility(View.GONE);
                                                                    tvReason6.setVisibility(View.GONE);
                                                                }
                                                                while (c.moveToNext() && b.moveToNext());
                                                            } else {
                                                                if (c.moveToFirst() && a_3.moveToFirst()) {
                                                                    do {
                                                                        tvReason.setText(c.getString(3));
                                                                        tvReason2.setText(a_3.getString(3));
                                                                        tvReasonView3.setVisibility(View.GONE);
                                                                        tvReason3.setVisibility(View.GONE);
                                                                        tvReasonView4.setVisibility(View.GONE);
                                                                        tvReason4.setVisibility(View.GONE);
                                                                        tvReasonView5.setVisibility(View.GONE);
                                                                        tvReason5.setVisibility(View.GONE);
                                                                        tvReasonView6.setVisibility(View.GONE);
                                                                        tvReason6.setVisibility(View.GONE);
                                                                    }
                                                                    while (c.moveToNext() && a_3.moveToNext());
                                                                } else {
                                                                    if (a.moveToFirst()) {
                                                                        do {
                                                                            tvReason.setText(a.getString(3));
                                                                            tvReasonView2.setVisibility(View.GONE);
                                                                            tvReason2.setVisibility(View.GONE);
                                                                            tvReasonView3.setVisibility(View.GONE);
                                                                            tvReason3.setVisibility(View.GONE);
                                                                            tvReasonView4.setVisibility(View.GONE);
                                                                            tvReason4.setVisibility(View.GONE);
                                                                            tvReasonView5.setVisibility(View.GONE);
                                                                            tvReason5.setVisibility(View.GONE);
                                                                            tvReasonView6.setVisibility(View.GONE);
                                                                            tvReason6.setVisibility(View.GONE);
                                                                        } while (a.moveToNext());
                                                                    } else {
                                                                        if (b.moveToFirst()) {
                                                                            do {
                                                                                tvReason.setText(b.getString(3));
                                                                                tvReasonView2.setVisibility(View.GONE);
                                                                                tvReason2.setVisibility(View.GONE);
                                                                                tvReasonView3.setVisibility(View.GONE);
                                                                                tvReason3.setVisibility(View.GONE);
                                                                                tvReasonView4.setVisibility(View.GONE);
                                                                                tvReason4.setVisibility(View.GONE);
                                                                                tvReasonView5.setVisibility(View.GONE);
                                                                                tvReason5.setVisibility(View.GONE);
                                                                                tvReasonView6.setVisibility(View.GONE);
                                                                                tvReason6.setVisibility(View.GONE);
                                                                            }
                                                                            while (b.moveToNext());
                                                                        } else {
                                                                            if (c.moveToFirst()) {
                                                                                do {
                                                                                    tvReason.setText(c.getString(3));
                                                                                    tvReasonView2.setVisibility(View.GONE);
                                                                                    tvReason2.setVisibility(View.GONE);
                                                                                    tvReasonView3.setVisibility(View.GONE);
                                                                                    tvReason3.setVisibility(View.GONE);
                                                                                    tvReasonView4.setVisibility(View.GONE);
                                                                                    tvReason4.setVisibility(View.GONE);
                                                                                    tvReasonView5.setVisibility(View.GONE);
                                                                                    tvReason5.setVisibility(View.GONE);
                                                                                    tvReasonView6.setVisibility(View.GONE);
                                                                                    tvReason6.setVisibility(View.GONE);
                                                                                }
                                                                                while (c.moveToNext());
                                                                            } else {
                                                                                if (b_.moveToFirst()) {
                                                                                    do {
                                                                                        tvReason.setText(b_.getString(3));
                                                                                        tvReasonView2.setVisibility(View.GONE);
                                                                                        tvReason2.setVisibility(View.GONE);
                                                                                        tvReasonView3.setVisibility(View.GONE);
                                                                                        tvReason3.setVisibility(View.GONE);
                                                                                        tvReasonView4.setVisibility(View.GONE);
                                                                                        tvReason4.setVisibility(View.GONE);
                                                                                        tvReasonView5.setVisibility(View.GONE);
                                                                                        tvReason5.setVisibility(View.GONE);
                                                                                        tvReasonView6.setVisibility(View.GONE);
                                                                                        tvReason6.setVisibility(View.GONE);
                                                                                    }
                                                                                    while (b_.moveToNext());
                                                                                } else{
                                                                                    if (a_3.moveToFirst()) {
                                                                                        do {
                                                                                            tvReason.setText(a_3.getString(3));
                                                                                            tvReasonView2.setVisibility(View.GONE);
                                                                                            tvReason2.setVisibility(View.GONE);
                                                                                            tvReasonView3.setVisibility(View.GONE);
                                                                                            tvReason3.setVisibility(View.GONE);
                                                                                            tvReasonView4.setVisibility(View.GONE);
                                                                                            tvReason4.setVisibility(View.GONE);
                                                                                            tvReasonView5.setVisibility(View.GONE);
                                                                                            tvReason5.setVisibility(View.GONE);
                                                                                            tvReasonView6.setVisibility(View.GONE);
                                                                                            tvReason6.setVisibility(View.GONE);
                                                                                        }
                                                                                        while (a_3.moveToNext());
                                                                                    }
                                                                                    else {
                                                                                        deduction.setText("No Interaction Has Found");
                                                                                        tvReasonView.setVisibility(View.GONE);
                                                                                        tvReason.setVisibility(View.GONE);
                                                                                        tvReasonView2.setVisibility(View.GONE);
                                                                                        tvReason2.setVisibility(View.GONE);
                                                                                        tvReasonView3.setVisibility(View.GONE);
                                                                                        tvReason3.setVisibility(View.GONE);
                                                                                        tvReasonView4.setVisibility(View.GONE);
                                                                                        tvReason4.setVisibility(View.GONE);
                                                                                        tvReasonView5.setVisibility(View.GONE);
                                                                                        tvReason5.setVisibility(View.GONE);
                                                                                        tvReasonView6.setVisibility(View.GONE);
                                                                                        tvReason6.setVisibility(View.GONE);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "problem", Toast.LENGTH_LONG).show();
            }

        }
        else {
            deduction.setText("PROBLEM");
            tvReasonView.setVisibility(View.GONE);
            tvReason.setVisibility(View.GONE);
            tvReasonView2.setVisibility(View.GONE);
            tvReason2.setVisibility(View.GONE);
            tvReasonView3.setVisibility(View.GONE);
            tvReason3.setVisibility(View.GONE);
            tvReasonView4.setVisibility(View.GONE);
            tvReason4.setVisibility(View.GONE);
            tvReasonView5.setVisibility(View.GONE);
            tvReason5.setVisibility(View.GONE);
            tvReasonView6.setVisibility(View.GONE);
            tvReason6.setVisibility(View.GONE);
        }
    }

    public void main(View view) {
        Intent myIntent = new Intent (this, MainActivity.class);
        startActivity(myIntent);
    }
    public void back(View view) {
        Intent myIntent = new Intent (this, MainActivity.class);

        EditText etDrugName1 = findViewById(R.id.etDrugName);
        EditText etDrugName2 = findViewById(R.id.etDrugName2);
        EditText etDrugName3 = findViewById(R.id.etDrugName3);
        EditText etDrugName4 = findViewById(R.id.etDrugName4);
        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);

        Bundle b = getIntent().getExtras();

        if(b != null){
            b.putString("drugname1", etDrugName1.getText().toString());
            b.putString("drugname2", etDrugName2.getText().toString());
            b.putString("drugname3", etDrugName3.getText().toString());
            b.putString("drugname4", etDrugName4.getText().toString());
            b.putString("age", spAge.getSelectedItem().toString());
            b.putString("gender", spGender.getSelectedItem().toString());
            b.putString("area", spArea.getSelectedItem().toString());

            myIntent.putExtras(b);
            startActivity(myIntent);
        }
        else {
            startActivity(myIntent);
        }

    }
}
