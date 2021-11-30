package uqu.graduationtwenty.baytar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);
        Bundle b = getIntent().getExtras();

        if (b != null) {
            EditText drug = findViewById(R.id.etDrugName);
            EditText drug2 = findViewById(R.id.etDrugName2);
            EditText drug3 = findViewById(R.id.etDrugName3);
            EditText drug4 = findViewById(R.id.etDrugName4);

            String dn = b.getString("drugname");
            String dn1 = b.getString("drugname1");
            String dn2 = b.getString("drugname2");
            String dn3 = b.getString("drugname3");
            String dn4 = b.getString("drugname4");

            drug.setText(dn);
            drug.setText(dn1);
            drug2.setText(dn2);
            drug3.setText(dn3);
            drug4.setText(dn4);
        }

        ArrayAdapter<CharSequence> adAge = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_spinner_item);
        adAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAge.setAdapter(adAge);
        spAge.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adGender = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(adGender);
        spGender.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adArea = ArrayAdapter.createFromResource(this, R.array.area, android.R.layout.simple_spinner_item);
        adArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(adArea);
        spArea.setOnItemSelectedListener(this);

    }

    public void arabic_main(View view) {
        Intent myIntent = new Intent (this, MainActivity_ar.class);
        startActivity(myIntent);
    }

    public void onClickPlus (View view) {
        TextView b = findViewById(R.id.tvAddDrug);
        EditText [] etArray = new EditText[]{
                findViewById(R.id.etDrugName2),
                findViewById(R.id.etDrugName3),
                findViewById(R.id.etDrugName4),
        };

        for (int i = 0; i <= etArray.length; i++) {
            if (etArray[i].getVisibility() == View.GONE) {
                etArray[i].setVisibility(View.VISIBLE);
                break;
            }
        }

        if (etArray[2].getVisibility() == View.VISIBLE){
            b.setEnabled(false);
        }
    }

    public void drugInformation(View view) {
        TextView warning = findViewById(R.id.warning);
        EditText etDrugName = findViewById(R.id.etDrugName);
        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);
        String drug = etDrugName.getText().toString().trim();

        if(!drug.isEmpty()){
            Bundle bb = new Bundle();
            bb.putString("drugname", etDrugName.getText().toString());
            bb.putString("age", spAge.getSelectedItem().toString());
            bb.putString("gender", spGender.getSelectedItem().toString());
            bb.putString("area", spArea.getSelectedItem().toString());

            Intent myIntent = new Intent (this, DrugInformation.class);
            myIntent.putExtras(bb);

            /*String type = "insert";
            String age = spAge.getSelectedItem().toString();
            ServerConnection backgroundWorker = new ServerConnection(this);
            backgroundWorker.execute(type, drug, age);*/

            startActivity(myIntent);
        }
        else {
            warning.setVisibility(View.VISIBLE);
            warning.setText("Please Enter A Drug Name");
        }
    }

    public void drugInteraction(View view) {
        EditText etDrugName1 = findViewById(R.id.etDrugName);
        EditText etDrugName2 = findViewById(R.id.etDrugName2);
        EditText etDrugName3 = findViewById(R.id.etDrugName3);
        EditText etDrugName4 = findViewById(R.id.etDrugName4);
        TextView warning = findViewById(R.id.warning);
        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);

        String et1 = etDrugName1.getText().toString().trim();
        String et2 = etDrugName2.getText().toString().trim();
        String et3 = etDrugName2.getText().toString().trim();
        String et4 = etDrugName2.getText().toString().trim();

        Bundle b = new Bundle();

        if(!(et1.isEmpty() && et2.isEmpty()) || !(et1.isEmpty() && et3.isEmpty()) || !(et1.isEmpty() && et4.isEmpty())
                || !(et2.isEmpty() && et3.isEmpty()) || !(et2.isEmpty() && et4.isEmpty()) || !(et3.isEmpty() && et4.isEmpty())){
            Intent myIntent = new Intent(this, DrugInteraction.class);

            b.putString("drugname1", etDrugName1.getText().toString());
            b.putString("drugname2", etDrugName2.getText().toString());
            b.putString("drugname3", etDrugName3.getText().toString());
            b.putString("drugname4", etDrugName4.getText().toString());

            myIntent.putExtra("age", spAge.getSelectedItem().toString());
            myIntent.putExtra("gender", spGender.getSelectedItem().toString());
            myIntent.putExtra("area", spArea.getSelectedItem().toString());
            myIntent.putExtras(b);

            /*String type = "insert";
            ServerConnection backgroundWorker = new ServerConnection(this);
            backgroundWorker.execute(type, username, password);*/

            startActivity(myIntent);
        }
        else {
            warning.setVisibility(View.VISIBLE);
            warning.setText("Please Enter At Least Two Drugs");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}