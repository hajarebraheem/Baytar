package uqu.graduationtwenty.baytar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity_ar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ar);

        Spinner spAge = findViewById(R.id.spAge);
        Spinner spGender = findViewById(R.id.spGender);
        Spinner spArea = findViewById(R.id.spArea);

        ArrayAdapter<CharSequence> adAge = ArrayAdapter.createFromResource(this, R.array.age_ar, android.R.layout.simple_spinner_item);
        adAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAge.setAdapter(adAge);
        spAge.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adGender = ArrayAdapter.createFromResource(this, R.array.gender_ar, android.R.layout.simple_spinner_item);
        adGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(adGender);
        spGender.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adArea = ArrayAdapter.createFromResource(this, R.array.area_ar, android.R.layout.simple_spinner_item);
        adArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(adArea);
        spArea.setOnItemSelectedListener(this);

    }
    public void english_main(View view) {
        Intent myintent = new Intent (this, MainActivity.class);
        startActivity(myintent);
    }

    public void onClickPlus (View view){
        EditText [] etArray = new EditText[]{
                findViewById(R.id.etDrugName2),
                findViewById(R.id.etDrugName3),
                findViewById(R.id.etDrugName4),
                findViewById(R.id.etDrugName5),
                findViewById(R.id.etDrugName6),
                findViewById(R.id.etDrugName7),
                findViewById(R.id.etDrugName8)
        };

        for (int i = 0; i <= etArray.length; i++){
            if (etArray[i].getVisibility()==View.GONE){
                etArray[i].setVisibility(View.VISIBLE);
                break;
            }
            else {

            }
        }
    }

    public void drugInformation(View view) {
        //EditText txtusr = (EditText) findViewById(R.id.etusername);
        //EditText txtpass = (EditText) findViewById(R.id.etpassowrd);

        Intent myintent = new Intent (this, DrugInformation_ar.class);
        //Bundle b = new Bundle();
        //b.putString("username", txtusr.getText().toString());
        //b.putString("password", txtpass.getText().toString());
        //myintent.putExtras(b);
        startActivity(myintent);
    }

    public void drugInteraction(View view) {
        EditText etDrugName1 = findViewById(R.id.etDrugName);
        EditText etDrugName2 = findViewById(R.id.etDrugName2);

        Intent myintent = new Intent(this, DrugInteraction_ar.class);
        Bundle b = new Bundle();

        b.putString("drugname1", etDrugName1.getText().toString());
        b.putString("drugname2", etDrugName2.getText().toString());
        myintent.putExtras(b);
        startActivity(myintent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
