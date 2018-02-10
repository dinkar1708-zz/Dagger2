package dagger.android.mvp.mainscreen;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.mvp.R;
import dagger.android.mvp.data.remote.UserProfile;
import dagger.android.mvp.util.MyProgressDialog;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();

    // hey lets inject presenter in activity
    @Inject
    MainContract.Presenter mTasksPresenter;

    private TextView textViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewProfile = findViewById(R.id.fetchProfileTxt);

        findViewById(R.id.fetchProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTasksPresenter.downloadUserProfile();
            }
        });

        Log.i("", "mTasksPresenter " + mTasksPresenter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mTasksPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTasksPresenter.dropView();
        //prevent leaking activity in
        // case presenter is orchestrating a long running task
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoadingIndicator(String message) {
        Log.i(TAG, "setLoadingIndicator .....");
        MyProgressDialog.showProgressDialog(this, message);
    }

    @Override
    public void cancelLoadingIndicator(String message) {
        Log.i(TAG, "cancelLoadingIndicator .....");
        MyProgressDialog.cancelProgressDialog();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDownloadUserProfile(UserProfile userProfile) {
        Log.i(TAG, "onDownloadUserProfile ...get call back in activity");
        textViewProfile.setText(userProfile + "");
    }

}
