package com.example.rxjavaoperatorsexamples.Ex17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.observables.MathObservable;

public class MathOperCustomDataTypesActivity extends AppCompatActivity {

    private static final String TAG = MathOperCustomDataTypesActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_oper_custom_data_types);

        // Not only on primitive datatypes, we can also perform mathematical operators on custom datatypes too.
        // We are going to use Java8 stream API to perform the comparator operations. So, if you are using
        // Android Studio, make sure you enable Java8 support to use the APIs. This causes the android project
        // to target higher API devices i.e minSdkVersion to 24.
        //
        //Let’s consider an example of finding the elderly person in a list. For this we create a datatype
        // of Person with name and age attributes. Using the Comparator.comparing(), we can easily creates
        // an Observable that emits the max aged person in the list.


        List<Person> persons = new ArrayList<>();
        persons.addAll(getPersons());

        Observable<Person> personObservable = Observable.from(persons);

        MathObservable.from(personObservable)
                .max(Comparator.comparing(Person::getAge))
                .subscribe(new Observer<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Person person) {
                        Log.d(TAG, "Person with max age: " + person.getName() + ", " + person.getAge() + " yrs");
                    }
                });



    }

    private List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        Person p1 = new Person("Lucy", 24);
        persons.add(p1);

        Person p2 = new Person("John", 45);
        persons.add(p2);

        Person p3 = new Person("Obama", 51);
        persons.add(p3);

        return persons;
    }



}
