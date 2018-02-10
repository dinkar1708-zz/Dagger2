package dagger.android.mvp.mainscreen;


import dagger.Binds;
import dagger.Module;
import dagger.android.mvp.di.ActivityScoped;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link MainPresenter}.
 */
@Module
public abstract class MainModule {
    //    @FragmentScoped
//    @ContributesAndroidInjector
//    abstract TasksFragment tasksFragment();
//
    //without declaring method here task presenter can not be injected in to activity
    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
}
