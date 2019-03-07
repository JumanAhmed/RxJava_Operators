package com.example.rxjavaoperatorsexamples.Ex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustOperatorActivity extends AppCompatActivity {

    private static final String TAG = JustOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_operator);

        /*Just() operator takes a list of arguments and converts the items into Observable items.
         It takes arguments between one to ten , No more then 9 items.

         Let’s consider the below example. The limitation of just() is,
         you can’t pass more than 10 arguments.*/

        // Example 1

        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Example 1 onSubscribe() Called");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "Example 1 onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Example 1 onComplete() Called");
                    }
                });










        // The Observer emits the array onNext(Integer[] integers)
        // so you will always have 1 emission irrespective of length of the array.

        // Example 2

        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        Observable.just(numbers,numbers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Example 2 onSubscribe() Called");
                    }

                    @Override
                    public void onNext(Integer[] integers) {
                        Log.d(TAG, "Example 2 onNext: " + integers.length);

                        // you might have to loop through the array
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Example 2 onComplete() Called");
                    }
                });
    }
}
