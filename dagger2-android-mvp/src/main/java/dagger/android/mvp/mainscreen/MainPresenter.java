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

package dagger.android.mvp.mainscreen;

import android.util.Log;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.android.mvp.data.TasksDataSource;
import dagger.android.mvp.data.TasksRepository;
import dagger.android.mvp.di.ActivityScoped;
import dagger.android.mvp.data.remote.UserProfile;


/**
 * Listens to user actions from the UI ({@link MainActivityFragment}), retrieves the data and updates the
 * UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the MainPresenter (if it fails, it emits a compiler error).  It uses
 * {@link MainModule} to do so.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 **/
@ActivityScoped

/**
 * bidirectional communicator for activity and other modules like network db etc.
 */
final class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private final TasksRepository mTasksRepository;

    @Nullable
    private MainContract.View mTasksView;

    //inject it, default constructor is needed to inject it
    @Inject
    MainPresenter(TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
        Log.i(TAG, "MainPresenter constructor ...mTasksRepository " + mTasksRepository);
    }

    @Override
    public void downloadUserProfile() {
        Log.i(TAG, "downloadUserProfile ...downloadUserProfile mTasksRepository " + mTasksRepository);

        mTasksView.setLoadingIndicator("Please wait... Downloading user profile.");

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(UserProfile userProfile) {
                //waiting willingly to show delay in progress bar
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mTasksView.onDownloadUserProfile(userProfile);
                mTasksView.cancelLoadingIndicator("Hey user profile downloaded successfully.");
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Override
    public void takeView(MainContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {
        this.mTasksView = null;
    }

}
