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

import dagger.android.mvp.BasePresenter;
import dagger.android.mvp.BaseView;
import dagger.android.mvp.data.remote.UserProfile;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {

    /* invoke from presenter to notify about happening in presenter like -
     presenter says - hey activity do following for me
    1. show loading indicator
    2. my data is downloaded display it
    3. now stop loading indicator*/

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(String message);

        void cancelLoadingIndicator(String message);

        void onDownloadUserProfile(UserProfile userProfile);

    }

    // invoke from activity/fragments
    interface Presenter extends BasePresenter<View> {

        void downloadUserProfile();

        void takeView(MainContract.View view);

        void dropView();
    }
}
