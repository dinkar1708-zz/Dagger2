package com.android.dagger2.di;

import com.android.dagger2.MainActivity;
import com.android.dagger2.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DPM on 26-01-2018.
 */

@Singleton
@Component(modules = ApplicationModule.class)
/**
 * intermediatier to access the module object
 */
public interface ApplicationComponent {

    void inject(MyApplication dragger2Application);

    //inject activity
    void inject(MainActivity mainActivity);

    // get application component
    MyApplication application();

}
