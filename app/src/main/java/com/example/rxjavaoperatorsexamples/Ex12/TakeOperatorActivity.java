package com.example.rxjavaoperatorsexamples.Ex12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TakeOperatorActivity extends AppCompatActivity {

    private static final String TAG = TakeOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_operator);

        // take(n) acts exactly opposite to skip. It takes first N emissions of an Observable.
        //In the below example, take(4) takes first 4 emissions i.e 1, 2, 3, 4 and skips the remaining.

        Observable
                .range(1, 10)
                .take(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "TakeOnNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed");
                    }
                });


        // takeLast(n) emits last N items from an Observable.
        //
        //In the same example, takeLast(4) takes last 4 emissions i.e 7, 8, 9, 10 and skips the remaining.


        Observable
                .range(1, 10)
                .takeLast(4)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Subscribed");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "TakeLastOnNext: " + integer);
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
