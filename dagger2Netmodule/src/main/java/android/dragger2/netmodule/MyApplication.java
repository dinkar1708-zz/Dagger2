package android.dragger2.netmodule;

import android.app.Application;
import android.dragger2.netmodule.di.component.ApplicationComponent;
import android.dragger2.netmodule.di.component.DaggerApplicationComponent;
import android.dragger2.netmodule.di.module.ApplicationModule;
import android.dragger2.netmodule.di.module.NetModule;
import android.util.Log;

/**
 * Created by DPM on 26-01-2018.
 * application class, decadency graph is initialized, other components can also be initialized.
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //Instantiating the component
//        We should do all this work within a specialization of the Application class
//        since these instances should be declared only once throughout the entire lifespan of the application:
        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule(this))
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        Log.i("", "onCreate...app component initialized..." + applicationComponent);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
