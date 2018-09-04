package floowuk.floowrx.model;

import rx.Observable;
import rx.subjects.PublishSubject;
/*** Created by igorfrankiv on 05/03/2018.*/

public class MyObservable<T> {

    protected final PublishSubject<T> onAdd;

    public MyObservable() {
        this.onAdd = PublishSubject.create();
    }
    public void add(T value) {
        onAdd.onNext(value);
    }
    public Observable<T> getObservable() {
        return onAdd;
    }
}