package com.example.rxjavaoperatorsexamples.Ex16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class CountReduceOperatorActivity extends AppCompatActivity {

    private static final String TAG = CountReduceOperatorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_reduce_operator);

        // Counts number of items emitted by an Observable and emits only the count value.
        //
        //Below, we have an Observable that emits both Male and Female users. We can count number of
        // Male users using count() operator as shown.

        // filter() filters the items by gender by applying user.getGender().equalsIgnoreCase(“male”) on each emitted item.



        getUsersObservable()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGender().equalsIgnoreCase("male");
                    }
                })
                .count()
                .subscribeWith(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long count) {
                        Log.d(TAG, "Male users count: " + count);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });



        // find even number using filter , count operator
          Observable
                  .range(1,20)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .filter(new Predicate<Integer>() {
                      @Override
                      public boolean test(Integer integer) throws Exception {
                          return  integer % 2 == 0;
                      }
                  })
                  .count()
                  .subscribeWith(new SingleObserver<Long>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onSuccess(Long aLong) {
                          Log.d(TAG, "Enven 1 to 20: " + aLong);
                      }

                      @Override
                      public void onError(Throwable e) {

                      }
                  });




          // Reduce applies a function on each item and emits the final result. First, it applies a function to
        // first item, takes the result and feeds back to same function on second item. This process
        // continuous until the last emission. Once all the items are over, it emits the final result.
        //
        //Below we have an Observable that emits numbers from 1 to 10. The reduce() operator calculates
        // the sum of all the numbers and emits the final result.


        Observable
                .range(1, 10)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer number, Integer sum) throws Exception {
                        return sum + number;
                    }
                })
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(TAG, "Sum of numbers from 1 - 10 is: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });

    }




    private Observable<User> getUsersObservable() {
        String[] maleUsers = new String[]{"Mark", "John", "Trump", "Obama"};
        String[] femaleUsers = new String[]{"Lucy", "Scarlett", "April"};

        final List<User> users = new ArrayList<>();

        for (String name : maleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("male");

            users.add(user);
        }

        for (String name : femaleUsers) {
            User user = new User();
            user.setName(name);
            user.setGender("female");

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


    public class User {
        String name;
        String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

    }



}
