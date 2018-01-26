package com.android.dagger2.di;

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

    public void inject(MyApplication dragger2Application);

    // get application component
    MyApplication application();

}
