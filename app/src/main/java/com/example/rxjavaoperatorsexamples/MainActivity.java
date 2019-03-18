package com.example.rxjavaoperatorsexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rxjavaoperatorsexamples.Ex1.JustOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex10.FilterOperatorEx2Activity;
import com.example.rxjavaoperatorsexamples.Ex11.SkipOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex12.TakeOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex13.DistinctOperatorEx1Activity;
import com.example.rxjavaoperatorsexamples.Ex14.DistinctOperatorEx2Activity;
import com.example.rxjavaoperatorsexamples.Ex15.MaxMinSumAverageOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex16.CountReduceOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex17.MathOperCustomDataTypesActivity;
import com.example.rxjavaoperatorsexamples.Ex18.ConcatOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex19.MergeOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex2.FromOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex3.JustAndFromDifferenceActivity;
import com.example.rxjavaoperatorsexamples.Ex4.RangeOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex5.RepeatOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex6.BufferOperatorEx1Activity;
import com.example.rxjavaoperatorsexamples.Ex7.BufferOperatorEx2Activity;
import com.example.rxjavaoperatorsexamples.Ex8.DebounceOperatorActivity;
import com.example.rxjavaoperatorsexamples.Ex9.FilterOperatorActivity;

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

    public void bufferOperatorExample1(View view) {
        startActivity(new Intent(getApplicationContext(), BufferOperatorEx1Activity.class));
    }

    public void bufferOperatorExample2(View view) {
        startActivity(new Intent(getApplicationContext(), BufferOperatorEx2Activity.class));

    }

    public void debounceOperator(View view) {
        startActivity(new Intent(getApplicationContext(), DebounceOperatorActivity.class));

    }

    public void filterOperatorEx1(View view) {
        startActivity(new Intent(getApplicationContext(), FilterOperatorActivity.class));
    }

    public void filterOperatorEx2(View view) {
        startActivity(new Intent(getApplicationContext(), FilterOperatorEx2Activity.class));
    }

    public void skipOperator(View view) {
        startActivity(new Intent(getApplicationContext(), SkipOperatorActivity.class));
    }

    public void takeOperator(View view) {
        startActivity(new Intent(getApplicationContext(), TakeOperatorActivity.class));
    }

    public void distinctOperatorEx1(View view) {
        startActivity(new Intent(getApplicationContext(), DistinctOperatorEx1Activity.class));
    }

    public void distinctOperatorEx2(View view) {
        startActivity(new Intent(getApplicationContext(), DistinctOperatorEx2Activity.class));
    }

    public void MaxMinSumAverageOperator(View view) {
        startActivity(new Intent(getApplicationContext(), MaxMinSumAverageOperatorActivity.class));
    }

    public void CountReduceOperator(View view) {
        startActivity(new Intent(getApplicationContext(), CountReduceOperatorActivity.class));
    }

    public void MathematicalOperationonCustomDataTypes(View view) {
        startActivity(new Intent(getApplicationContext(), MathOperCustomDataTypesActivity.class));
    }

    public void concatOperator(View view) {
        startActivity(new Intent(getApplicationContext(), ConcatOperatorActivity.class));
    }

    public void mergeOperator(View view) {
        startActivity(new Intent(getApplicationContext(), MergeOperatorActivity.class));
    }
}
