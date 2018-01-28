package android.dragger2.netmodule;

import android.app.Application;
import android.dragger2.netmodule.di.component.ApplicationComponent;
import android.dragger2.netmodule.di.component.DaggerApplicationComponent;
import android.dragger2.netmodule.di.module.ApplicationModule;
import android.util.Log;

/**
 * Created by DPM on 26-01-2018.
 * application class, decadency graph is initialized, other components can also be initialized.
 */
public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;
//    private NetComponent netComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
//                .netModule(new NetModule(this))
                .build();
        applicationComponent.inject(this);

        Log.i("", "onCreate...app component initialized..." + applicationComponent);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

//    public NetComponent getNetComponent() {
//        return netComponent;
//    }

//    public void setNetComponent(NetComponent netComponent) {
//        this.netComponent = netComponent;
//    }
}
