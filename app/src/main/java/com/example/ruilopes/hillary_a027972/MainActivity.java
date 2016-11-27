package com.example.ruilopes.hillary_a027972;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    TextView tView;
    Spinner oSpin;
    JSONArray arraymail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tView = (TextView) findViewById(R.id.textView);
        final Button btn = (Button) findViewById(R.id.btnMail);
        oSpin = (Spinner) findViewById(R.id.spinnerGyro);
        final Context context = this;
        final EditText eText = (EditText)findViewById(R.id.editText);

        oSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int i, long l) {
                //tView.setText(str[i]);
                int id = (int) oSpin.getSelectedItemId();
                try {
                    String text = "Date: " + arraymail.getJSONObject(id).getString("docDate") + "\nFrom: " +
                            arraymail.getJSONObject(id).getString("from") + "\nSubject: " + arraymail.getJSONObject(id).getString("subject");
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //tView.setText("?");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject mails = new JSONObject(new HillaryEmails().execute(eText.getText().toString()).get());
                    arraymail = mails.getJSONArray("rows");
                    String[] strmail = new String[arraymail.length()];

                    for (int i = 0; i < arraymail.length(); i++) {
                        String strchars = arraymail.getJSONObject(i).getString("subject");

                        if (strchars.length() > 10) {
                            strchars = strchars.substring(0, 10);
                        }

                        strmail[i] = "Date: " + arraymail.getJSONObject(i).getString("docDate") + " From: " +
                                arraymail.getJSONObject(i).getString("from") + " Subject: " + strchars;
                    }

                    oSpin.setAdapter(null);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, strmail);
                    oSpin.setAdapter(adapter1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}