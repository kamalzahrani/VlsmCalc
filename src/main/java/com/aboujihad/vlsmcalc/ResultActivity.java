package com.aboujihad.vlsmcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ListView vlsmInfo;
    ArrayList vlsmInfoText =  new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        vlsmInfo = findViewById(R.id.vlsmInfo);
        Bundle bundle = getIntent().getExtras();
        vlsmInfoText = bundle.getStringArrayList("VLSMINFO");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,vlsmInfoText);
        vlsmInfo.setAdapter(adapter);


    }
}
