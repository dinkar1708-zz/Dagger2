package dagger.android.mvp.data;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.mvp.data.local.TaskLocalDataSource;
import dagger.android.mvp.data.remote.TaskRemoteDataSource;
import dagger.android.mvp.util.AppExecutors;
import dagger.android.mvp.util.DiskIOThreadExecutor;

/**
 * This is used by Dagger to inject the required arguments into the {@link}.
 */
@Module
abstract public class TasksRepositoryModule {

    private static final int THREAD_COUNT = 3;



    /*defining two data source one for local and one for remote ie. two constructor is required in implementaiton*/

    @Singleton
    @Binds
    @Local
    abstract TasksDataSource provideTasksLocalDataSource(TaskLocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract TasksDataSource provideTasksRemoteDataSource(TaskRemoteDataSource dataSource);


   /*@Singleton
    @Provides
    static ToDoDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), ToDoDatabase.class, "Tasks.db")
                .build();
    }*/

    /*@Singleton
    @Provides
    static TasksDao provideTasksDao(ToDoDatabase db) {
        return db.taskDao();
    }*/

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
}
