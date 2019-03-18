package com.example.rxjavaoperatorsexamples.Ex14;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavaoperatorsexamples.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DistinctOperatorEx2Activity extends AppCompatActivity {

    private static final String TAG = DistinctOperatorEx2Activity.class.getSimpleName();

    // The distinct operator works very well with primitive datatypes. But if you want to use it with a
    // custom datatype, you need to override the equals() and hashCode() methods.


    // In Note model, equals() and hashCode() methods are overridden to make comparison between objects work.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distinct_operator_ex2);

        Observable<Note> notesObservable = getNotesObservable();

        DisposableObserver<Note> notesObserver = getNotesObserver();

        notesObservable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribeWith(notesObserver);

    }




    private DisposableObserver<Note> getNotesObserver() {
        return new DisposableObserver<Note>() {

            @Override
            public void onNext(Note note) {
                Log.e(TAG, "onNext: " + note.getNote());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };
    }

    private Observable<Note> getNotesObservable() {
        final List<Note> notes = prepareNotes();

        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) throws Exception {
                for (Note note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    // Preparing notes including duplicates
    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "Buy tooth paste!"));
        notes.add(new Note(2, "Call brother!"));
        notes.add(new Note(3, "Call brother!"));
        notes.add(new Note(4, "Pay power bill!"));
        notes.add(new Note(5, "Watch Narcos tonight!"));
        notes.add(new Note(6, "Buy tooth paste!"));
        notes.add(new Note(7, "Pay power bill!"));

        return notes;
    }

    public class Note {
        int id;
        String note;

        public Note(int id, String note) {
            this.id = id;
            this.note = note;
        }

        public int getId() {
            return id;
        }

        public String getNote() {
            return note;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Note)) {
                return false;
            }

            return note.equalsIgnoreCase(((Note) obj).getNote());
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + (this.note != null ? this.note.hashCode() : 0);
            return hash;
        }
    }
}
