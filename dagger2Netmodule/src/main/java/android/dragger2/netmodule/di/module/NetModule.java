package android.dragger2.netmodule.di.module;

import android.dragger2.netmodule.MyApplication;
import android.dragger2.netmodule.network.TestClient;
import android.dragger2.netmodule.network.VolleyClient;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 */

@Module
public class NetModule {

    private MyApplication dagger2Application;

    //create constructor of module
    public NetModule(MyApplication dragger2Application) {
        this.dagger2Application = dragger2Application;
    }

    // providing singlton instance of volley client
    @Singleton
    @Provides
    public VolleyClient provideVolleyClient() {
        VolleyClient mInstance = new VolleyClient(dagger2Application);
        return mInstance;
    }

    // providing singlton instance of volley client
    @Singleton
    @Provides
    public TestClient provideTestClient() {
        TestClient mInstance = new TestClient();
        return mInstance;
    }
}
