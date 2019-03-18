package com.example.rxjavaoperatorsexamples.Ex13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DistinctOperatorEx1Activity extends AppCompatActivity {

    private static final String TAG = DistinctOperatorEx1Activity.class.getSimpleName();

    // Distinct operator filters out items emitted by an Observable by avoiding duplicate items in the list.
    //
    //Below, we have list of integers with duplicates. Using distinct(), emission of duplicates can be avoided.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distinct_operator_ex1);


        Observable<Integer> numbersObservable = Observable.just(10,10, 15, 20, 100, 200, 100, 300, 20, 100);

        numbersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
