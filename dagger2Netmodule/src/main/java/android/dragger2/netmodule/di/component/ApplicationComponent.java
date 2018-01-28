package android.dragger2.netmodule.di.component;


import android.dragger2.netmodule.MyApplication;
import android.dragger2.netmodule.network.VolleyClient;
import android.dragger2.netmodule.di.module.ApplicationModule;

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

//    void inject(VolleyClient volleyClient);

    // get application component
    MyApplication application();

    VolleyClient volleyClient();

}
