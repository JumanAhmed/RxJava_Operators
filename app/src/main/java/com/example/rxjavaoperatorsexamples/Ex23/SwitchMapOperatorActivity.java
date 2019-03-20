package com.example.rxjavaoperatorsexamples.Ex23;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SwitchMapOperatorActivity extends AppCompatActivity {

    private static final String TAG = SwitchMapOperatorActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_map_operator);


        // SwithMap() on the other hand is completely a different operator from FlatMap and ConcatMap.
        // SwitchMap always return the latest Observable and emits the items from it.

        // some use case scenario of SwithchMap

        // SwitchMap is best suited when you want to discard the response and consider the latest one. Let’s
        // say you are writing an Instant Search app which sends search query to server each time user types
        // something. In this case multiple requests will be sent to server with multiple queries, but we
        // want to show the result of latest typed query only. For this case, SwitchMap is best operator to use.

        //Another use case of SwitchMap is, you have a feed screen in which feed is refreshed each time user
        // perform pulldown to refresh. In this scenario, SwitchMap is best suited as it can ignores the older
        // feed response and consider only the latest request.

        // some use case scenario of SwithchMap

        Observable<Integer> integerObservable =
                Observable.fromArray(new Integer[]{1, 2, 3, 4, 5, 6});


        // it always emits 6 as it un-subscribes the before observer
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return Observable.just(integer).delay(1, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                        disposable = d;
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
                        Log.d(TAG, "All users emitted!");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
