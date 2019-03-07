package com.example.rxjavaoperatorsexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rxjavaoperatorsexamples.Ex1.JustOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex2.FromOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex3.JustAndFromDifferenceActivity;
import com.example.rxjavaoperatorsexamples.Ex4.RangeOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex5.RepeatOperatorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void justOperatorExample(View view) {
        startActivity(new Intent(getApplicationContext(), JustOperatorActivity.class));
    }

    public void FromOperatorExample(View view) {
        startActivity(new Intent(getApplicationContext(), FromOperatorActivity.class));
    }

    public void justAndFromOperatorDiffExample(View view) {

        startActivity(new Intent(getApplicationContext(), JustAndFromDifferenceActivity.class));
    }

    public void rangeOperatorExample(View view) {
        startActivity(new Intent(getApplicationContext(), RangeOperatorActivity.class));
    }

    public void repeatOperatorExample(View view) {
        startActivity(new Intent(getApplicationContext(), RepeatOperatorActivity.class));
    }
}
