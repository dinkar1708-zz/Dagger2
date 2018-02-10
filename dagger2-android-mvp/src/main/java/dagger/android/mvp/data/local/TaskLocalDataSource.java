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

package dagger.android.mvp.data.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.mvp.data.Task;
import dagger.android.mvp.data.TasksDataSource;
import dagger.android.mvp.util.AppExecutors;

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
public class TaskLocalDataSource implements TasksDataSource {
    private static final String TAG = TaskLocalDataSource.class.getSimpleName();

    private static final Map<String, Task> TASKS_SERVICE_DATA = new LinkedHashMap<>();

    private final AppExecutors mAppExecutors;

    @Inject
    public TaskLocalDataSource(@NonNull AppExecutors executors) {
        this.mAppExecutors = executors;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        Log.i(TAG, "getTasks..LOCAL....mAppExecutors "+mAppExecutors);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "getTasks...LOCAL...runnable ");
//            callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        };
        mAppExecutors.networkIO().execute(runnable);

    }


}
