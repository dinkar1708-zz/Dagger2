package dagger.android.mvp;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.mvp.data.TasksRepository;
import dagger.android.mvp.di.DaggerAppComponent;
import dagger.android.mvp.network.NetworkModule;

/**
 * We create a custom {@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
public class MyApplication extends DaggerApplication {

    @Inject
    TasksRepository tasksRepository;

    @Inject
    NetworkModule netModule;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().
                application(this).build();
    }
}
