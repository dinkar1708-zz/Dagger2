/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.android.mvp.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.mvp.data.remote.UserProfile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 * <p/>
 * By marking the constructor with {@code @Inject} and the class with {@code @Singleton}, Dagger
 * injects the dependencies required to create an instance of the TasksRespository (if it fails, it
 * emits a compiler error). It uses {@link TasksRepositoryModule} to do so, and the constructed
 * instance is available in {@link }.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 */
@Singleton
public class TasksRepository implements TasksDataSource {

    private static final String TAG = TasksRepository.class.getSimpleName();
    private TasksDataSource mTasksRemoteDataSource = null;

    private TasksDataSource mTasksLocalDataSource = null;

    /**
     * By marking the constructor with {@code @Inject}, Dagger will try to inject the dependencies
     * required to create an instance of the TasksRepository. Because {@link TasksDataSource} is an
     * interface, we must provide to Dagger a way to build those arguments, this is done in
     * {@link TasksRepositoryModule}.
     * <p>
     * When two arguments or more have the same type, we must provide to Dagger a way to
     * differentiate them. This is done using a qualifier.
     * <p>
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */

    /*@Inject
    TasksRepository() {
    }*/
    @Inject
    TasksRepository(@Remote TasksDataSource tasksRemoteDataSource,
                    @Local TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
        Log.i(TAG, "TasksRepository multiple constructor local and remote both");
    }

    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadTasksCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        Log.i(TAG, "getTasks from repository....INTERNET");

        checkNotNull(callback);

        // Query the local storage if available. If not, query the network.
        mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {

            @Override
            public void onTasksLoaded(UserProfile userProfile) {
                Log.i(TAG, "onTasksLoaded..INTERNET");

                callback.onTasksLoaded(userProfile);
            }

            @Override
            public void onDataNotAvailable() {
                getTasksFromRemoteDataSource(callback);
            }
        });

       /* mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(UserProfile userProfile) {
                callback.onTasksLoaded(userProfile);
            }

            @Override
            public void onDataNotAvailable() {
                getTasksFromRemoteDataSource(callback);
            }
        });*/
    }


    private void getTasksFromRemoteDataSource(@NonNull final LoadTasksCallback callback) {
        /*mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                callback.onTasksLoaded(null);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });*/
    }


    @Nullable
    private Task getTaskWithId(@NonNull String id) {
        checkNotNull(id);
        return null;
    }

}
