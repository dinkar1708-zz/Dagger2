package dagger.android.mvp.data.remote;

/**
 * dummy user profile class
 * // define ur own user profile variables/ methods here
 */
public class UserProfile {
    private String one;

    private String key;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "UserProfile [one = " + one + ", key = " + key + "]";
    }
}