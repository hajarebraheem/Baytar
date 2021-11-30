package uqu.graduationtwenty.baytar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DrugInteraction_ar extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_interaction_ar);

        TextView tvResult = findViewById(R.id.tvResult);
        Bundle b = getIntent().getExtras();
        String dn1 = b.getString("drugname1");
        String dn2 = b.getString("drugname2");

        int res = areEqual(dn1, dn2);
        if (res == 1)
            tvResult.setText("مساوية");
        else
            tvResult.setText("ليست مساوية");
        //String pass = b.getString("password");
    }

    public void main_ar(View view) {
        Intent myintent = new Intent (this, MainActivity_ar.class);
        startActivity(myintent);
    }

    public static int areEqual(String d1, String d2)
    {
        String l1 = d1.toLowerCase();
        String l2 = d2.toLowerCase();
        int n = l1.length();
        int m = l2.length();
        if (l1.charAt(0) == l2.charAt(0))
            return 1;
        else
            return 0;
    }
    public void english_drug_inter(View view) {
        Intent myintent = new Intent (this, DrugInteraction_ar.class);
        startActivity(myintent);
    }
}
