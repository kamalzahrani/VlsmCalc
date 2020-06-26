package com.aboujihad.vlsmcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showHotesActivity(View view) {
        Intent hotesActivity = new Intent(MainActivity.this, HotesActivity.class);
        startActivity(hotesActivity);
    }

    public void showSubnetsActivity(View view) {
        Intent subnetsActivity = new Intent(MainActivity.this, SubnetsActivity.class);
        startActivity(subnetsActivity);
    }

    public void showVlsmActivity(View view) {
        Intent vlsmActivity = new Intent(MainActivity.this, VlsmActivity.class);
        startActivity(vlsmActivity);
    }
}
