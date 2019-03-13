package com.example.rxjavaoperatorsexamples.Ex6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BufferOperatorEx1Activity extends AppCompatActivity {

//    Buffer gathers items emitted by an Observable into batches and emit the batch instead of
//      emitting one item at a time.
//
//    Below, we have an Observable that emits integers from 1-9. When buffer(3) is used, it
//  emits 3 integers at a time.

    private static final String TAG = BufferOperatorEx1Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_operator_ex1);

        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);

        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        for(Integer integer : integers){
                            Log.d(TAG, "Item: " + integer);
                        }

                        Log.d(TAG, "onNext");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "All items emitted!");
                    }
                });


    }
}
