package matrial.aka.twitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "GqwOQXxIw66SOW6gltFgi8caU";
    private static final String TWITTER_SECRET = "mqr5G1ZwDHZt9nfGYZhKMbkwFlt6680hMuitsPISdsGPkhubKy";

// tags that will hold the retrived username and profile image after successful login
    public static final String KEY_USER="name";
    public static final String profile_img_url="image_url";

// login button
 TwitterLoginButton twitterLoginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
    }
}
