package dagger.android.mvp.network;


import android.util.Log;

import dagger.android.mvp.data.remote.UserProfile;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * publish subscribe model for fetching data from network
 */
public class RxJavaService {
    private final NetworkServiceInterface networkService;
    private static final String TAG = RxJavaService.class.getSimpleName();

    public RxJavaService(NetworkServiceInterface networkService) {
        this.networkService = networkService;
    }

    public Subscription fetchUserProfile(final NetworkResponseCallBack callback) {

        return networkService.getUserProfile()
                // do in background thread
                .subscribeOn(Schedulers.io())
                // get response on main thread
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends UserProfile>>() {
                    @Override
                    public Observable<? extends UserProfile> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<UserProfile>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError " + e);
                        callback.onError(e.getMessage());

                    }

                    @Override
                    public void onNext(UserProfile userProfile) {
                        Log.i(TAG, "onNext " + userProfile);
                        callback.onSuccess(userProfile);

                    }
                });
    }

    public interface NetworkResponseCallBack<T> {
        void onSuccess(T response);

        void onError(String networkError);
    }
}
