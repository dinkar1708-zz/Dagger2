package android.dragger2.netmodule.di.module;

import android.dragger2.netmodule.MyApplication;
import android.dragger2.netmodule.network.VolleyClient;
import android.util.Log;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DPM on 26-01-2018.
 */

@Module
/**
 * this module is for application scope related stuff
 * modules may be like network module, data base module
 */
public class ApplicationModule {
    private final MyApplication dagger2Application;

    //create constructor of module
    public ApplicationModule(MyApplication dragger2Application) {
        this.dagger2Application = dragger2Application;
        Log.i("", "ApplicationModule. initialized..." + dagger2Application);
    }

    @Provides
    public MyApplication provideApplication() {
        return dagger2Application;
    }

}
