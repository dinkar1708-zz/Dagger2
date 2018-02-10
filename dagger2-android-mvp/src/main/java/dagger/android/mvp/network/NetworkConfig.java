package dagger.android.mvp.network;

import java.util.concurrent.TimeUnit;

public final class NetworkConfig {
    // Fields from default config.
    public static final String BASEURL = "http://echo.jsontest.com/key/value/";

    public static final int CACHETIME = 432000;
    public static final int LIMIT = 100;
    public static final int READ_TIMEOUT = 60;
    public static final int CONNECT_TIMEOUT = 60;
}
