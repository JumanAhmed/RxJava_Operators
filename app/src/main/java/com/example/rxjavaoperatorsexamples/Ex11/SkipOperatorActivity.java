package com.example.rxjavaoperatorsexamples.Ex11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SkipOperatorActivity extends AppCompatActivity {

    private static final String TAG = SkipOperatorActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_operator);
//
//        Skip(n) operator skips the emission of first N items emitted by an Observable.
//        Let’s say you have an Observable that emits integers from 1-10 and if skip(4)
//         is operator is used, it skips 1-4 and emits the numbers 5, 6, 7, 8, 9, 10.


        Observable
                .range(1, 10)
                .skip(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "SkipOnNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });


        // skipLast(n) skips the last N emissions from an Observable. In the same example,
        // skipLast(4) skips the emission of 7-10 and emits only 1, 2, 3, 4, 5, 6


        Observable
                .range(1, 10)
                .skipLast(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "SkipLastOnNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });
    }
}
