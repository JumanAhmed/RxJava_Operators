package com.example.rxjavaoperatorsexamples.Ex21;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.Ex18.User;
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

public class FlatMapOperatorActivity extends AppCompatActivity {

    private static final String TAG = FlatMapOperatorActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map_operator);

        // some use case scenario of FlatMap

        // Choose FlatMap when the order is not important. Let’s say you are building a Airline Ticket
        // Fair app that fetches the prices of each airline separately and display on the screen. For this
        // both FlatMap and ConcatMap can be used. But if the order is not important and want to send all
        // the network calls simultaneously, I would consider FlatMap over ConcatMap. If you consider
        // ConcatMap in this scenario, the time takes to fetch the prices takes very longer time as the
        // ConcatMap won’t make simultaneous calls in order to maintain item order.


        // To better understand FlatMap, consider a scenario where you have a network call to fetch
        // Users with name and gender. Then you have another network that gives you address of each user.
        // Now the requirement is to create an Observable that emits Users with name, gender and address
        // properties. To achieve this, you need to get the users first, then make separate network
        // call for each user to fetch his address. This can be done easily using FlatMap operator.


        // getUsersObservable() : assume it makes a network call and returns an Observable that emits User (name and gender) objects.
        //getAddressObservable() : assume it makes another network call just to fetch user address. This also returns an Observable that emits User by adding address node to existing name and gender.
        //flatMap() operator makes getAddressObservable() call each time a User is emitted and returns an Observable that emits User including the address filed.
        //Finally flatMap() returns an Observable by merging two Observables together.
        //Thread.sleep(sleepTime); added here to simulate network latency.


        // If you run this example you can see the output like below. Here, name and gender are fetched
        // from one observable and address is fetched from another observable. Also notice that the order
        // of items is not maintained as source observable. You can see the order changed each time you
        // run this example.


        getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<User, Observable<User>>() {

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
