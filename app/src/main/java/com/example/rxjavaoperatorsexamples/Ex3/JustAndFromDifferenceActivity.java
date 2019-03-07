package com.example.rxjavaoperatorsexamples.Ex3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class JustAndFromDifferenceActivity extends AppCompatActivity {

//    All though just() and from() appears to be doing the same work, it differs
//    in number of emissions.
//
//    just() – Makes only 1 emission. .just(new Integer[]{1, 2, 3}) makes one emission with Observer
//    callback as onNext(Integer[] integers)
//    fromArray() – Makes N emissions. .fromArray(new Integer[]{1, 2, 3}) makes three emission
//    with Observer callback as onNext(Integer integer)


    private static final String TAG = JustAndFromDifferenceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_and_from_difference);

        // Just Operator
        Observable.just(new Integer[] {1, 2, 3, 4, 5})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe called");
                    }

                    @Override
                    public void onNext(Integer[] integers) {
                        Log.d(TAG, "onNext :"+integers.length);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete() called");
                    }
                });


        // fromArray operator
        Observable.fromArray(new Integer[] {1,2,3,4,5,6})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe called");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext :"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete called");
                    }
                });


    }
}
