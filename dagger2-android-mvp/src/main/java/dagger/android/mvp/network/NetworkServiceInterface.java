package dagger.android.mvp.network;


import dagger.android.mvp.data.remote.UserProfile;
import retrofit2.http.GET;
import rx.Observable;

/**
 * interface declaration for fetching data from network
 */
public interface NetworkServiceInterface {

    // i have define observable for user profile using rx java and retrofit
    // u can use any kind of interface here based on your use case
    @GET("one/two")
    Observable<UserProfile> getUserProfile();

    // TODO hey define api method here............
}
