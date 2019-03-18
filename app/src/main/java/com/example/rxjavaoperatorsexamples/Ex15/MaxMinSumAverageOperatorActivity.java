package com.example.rxjavaoperatorsexamples.Ex15;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import rx.Observable;
import rx.Subscriber;
import rx.observables.MathObservable;

public class MaxMinSumAverageOperatorActivity extends AppCompatActivity {

    private static final String TAG = MaxMinSumAverageOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_min_sum_average_operator);

        // Max() operator finds the maximum valued item in the Observable sequence and emits that value.
        //
        //The below example emits the max value of an integer series

        Integer[] numbers = {5, 101, 404, 22, 3, 1024, 65};

        Observable<Integer> observable = Observable.from(numbers);

        MathObservable
                .max(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Max value: " + integer);
                    }
                });



        // The same operation can be done on other primitive datatype such as Float, Double, Long.
        // Below examples emits the max value of float numbers.


        Observable<Float> floatObservable = Observable.just(10.5f, 14.5f, 11.5f, 5.6f);
        MathObservable.max(floatObservable)
                .subscribe(new Subscriber<Float>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Float aFloat) {
                        Log.d(TAG, "Max of 10.5f, 11.5f, 14.5f: " + aFloat);
                    }
                });


        // Min() operator emits the minimum valued item in the Observable data set.


        Observable<Integer> observableMin = Observable.from(numbers);

        MathObservable
                .min(observableMin)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Min value: " + integer);
                    }
                });


        // Calculates the sum of all the items emitted by an Observable and emits only the Sum value.
        // In the below example, sumInteger() is used to calculate the sum of Integers. Likewise, we have
        // sumFloat(), sumDouble() and sumLong() available to calculate sum of other primitive datatypes.

        Observable<Integer> observableSum = Observable.from(numbers);

        MathObservable
                .sumInteger(observableSum)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Sum value: " + integer);
                    }
                });

        // Calculates the average of all the items emitted by an Observable and emits only the Average value.
        //
        //The below example calculates the average value of integers using averageInteger() method.
        // To calculate average of other datatypes, averageFloat(), averageDouble() and averageLong() are available.


        MathObservable
                .averageInteger(observable)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Average: " + integer);
                    }
                });
    }
}
