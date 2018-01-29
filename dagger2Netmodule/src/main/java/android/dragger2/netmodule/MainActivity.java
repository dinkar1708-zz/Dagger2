package android.dragger2.netmodule;

import android.dragger2.netmodule.network.TestClient;
import android.dragger2.netmodule.network.VolleyClient;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    //inject volley client from application component
    @Inject
    VolleyClient volleyClient;

    @Inject
    MyApplication myApplication;

    @Inject
    TestClient testClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i(TAG, "onCreate... ");

        MyApplication application = (MyApplication) getApplication();
        //injecting this activity in dependency graph
        application.getApplicationComponent().inject(this);

        String url = "http://www.example.com";

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.i(TAG, "onResponse " + response);
//                        Toast.makeText(MainActivity.this, "onResponse" + response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.i(TAG, "onErrorResponse " + error);
//                        Toast.makeText(MainActivity.this, "onErrorResponse", Toast.LENGTH_LONG).show();

                    }
                });

        volleyClient.addToRequestQueue(stringRequest);

        Log.i(TAG, "executed... myApplication "+myApplication +" testClient "+testClient);

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
}
