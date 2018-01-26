package com.android.dagger2.di;

import android.util.Log;

import com.android.dagger2.MyApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DPM on 26-01-2018.
 */

@Module
/**
 * modules like network module, data base module
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
