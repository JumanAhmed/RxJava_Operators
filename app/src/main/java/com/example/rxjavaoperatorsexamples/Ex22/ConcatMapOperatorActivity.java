package com.example.rxjavaoperatorsexamples.Ex22;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.Ex18.User;
import com.example.rxjavaoperatorsexamples.Ex21.Address;
import com.example.rxjavaoperatorsexamples.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ConcatMapOperatorActivity extends AppCompatActivity {

    private static final String TAG = ConcatMapOperatorActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat_map_operator);

        // Now consider the same example of FlatMap but replacing the operator with ConcatMap. Technically
        // the both operators produces the same output but the sequence the data emitted changes.
        //
        //ConcatMap() maintains the order of items and waits for the current Observable to complete its job before emitting the next one.
        //ConcatMap is more suitable when you want to maintain the order of execution.

        // some use case scenario of ConcathMap

        // Choose FlatMap when the order is not important. Let’s say you are building a Airline Ticket
        // Fair app that fetches the prices of each airline separately and display on the screen. For this
        // both FlatMap and ConcatMap can be used. But if the order is not important and want to send all
        // the network calls simultaneously, I would consider FlatMap over ConcatMap. If you consider
        // ConcatMap in this scenario, the time takes to fetch the prices takes very longer time as the
        // ConcatMap won’t make simultaneous calls in order to maintain item order.


        // If you run the example, you can see the order is maintained as source
        // observable i.e Mark, John, Trump, Obama and it always maintains the same order.

        getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Function<User, Observable<User>>() {

                    @Override
                    public Observable<User> apply(User user) throws Exception {

                        // getting each user address by making another network call
                        return getAddressObservable(user);
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                        disposable = d;
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e(TAG, "onNext: " + user.getName() + ", " + user.getGender() + ", " + user.getAddress().getAddress());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "All users emitted!");
                    }
                });
    }



    /**
     * Assume this as a network call
     * returns Users with address filed added
     */
    private Observable<User> getAddressObservable(final User user) {

        final String[] addresses = new String[]{
                "1600 Amphitheatre Parkway, Mountain View, CA 94043",
                "2300 Traverwood Dr. Ann Arbor, MI 48105",
                "500 W 2nd St Suite 2900 Austin, TX 78701",
                "355 Main Street Cambridge, MA 02142"
        };

        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        Address address = new Address();
                        address.setAddress(addresses[new Random().nextInt(2) + 0]);
                        if (!emitter.isDisposed()) {
                            user.setAddress(address);


                            // Generate network latency of random duration
                            int sleepTime = new Random().nextInt(1000) + 500;

                            Thread.sleep(sleepTime);
                            emitter.onNext(user);
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    /**
     * Assume this is a network call to fetch users
     * returns Users with name and gender but missing address
     */
    private Observable<User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};

        final List<User> users = new ArrayList<>();

        for (String name : maleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }

        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user : users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }

                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
