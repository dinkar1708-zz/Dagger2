package com.android.dagger2;

import android.app.Application;
import android.util.Log;

import com.android.dagger2.di.ApplicationComponent;
import com.android.dagger2.di.ApplicationModule;
import com.android.dagger2.di.DaggerApplicationComponent;

/**
 * Created by DPM on 26-01-2018.
 * application class, decadency graph is initialized, other components can also be initialized.
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        // inject application class in component
        applicationComponent.inject(this);
        Log.i("", "onCreate...app component initialized..." + applicationComponent);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
