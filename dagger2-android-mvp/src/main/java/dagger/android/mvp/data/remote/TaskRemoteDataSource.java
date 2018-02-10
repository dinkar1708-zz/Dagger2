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

package dagger.android.mvp.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.mvp.data.Task;
import dagger.android.mvp.data.TasksDataSource;
import dagger.android.mvp.network.RxJavaService;
import dagger.android.mvp.util.AppExecutors;

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
public class TaskRemoteDataSource implements TasksDataSource {
    private static final String TAG = TaskRemoteDataSource.class.getSimpleName();

    private static final Map<String, Task> TASKS_SERVICE_DATA = new LinkedHashMap<>();

    private final AppExecutors mAppExecutors;

    //
    @Inject
    public RxJavaService service;

    @Inject
    public TaskRemoteDataSource(@NonNull AppExecutors executors) {
        this.mAppExecutors = executors;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        Log.i(TAG, "getTasks......mAppExecutors " + mAppExecutors);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "getTasks......runnable ");
//                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        };
        mAppExecutors.networkIO().execute(runnable);

        service.fetchUserProfile(new RxJavaService.NetworkResponseCallBack<UserProfile>() {
            @Override
            public void onSuccess(UserProfile response) {
                Log.i(TAG, "onSuccess " + response);
                callback.onTasksLoaded(response);
            }

            @Override
            public void onError(String networkError) {
                Log.i(TAG, "onError " + networkError);

            }
        });

    }


}
