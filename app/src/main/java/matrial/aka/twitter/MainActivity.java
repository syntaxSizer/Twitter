package matrial.aka.twitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
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

        //Initializing TwitterAuthConfig, these two line will also added automatically while configuration we did
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        //initialize twitter login button
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        // add callback to the button
        twitterLoginButton.setCallback(new Callback<TwitterSession>(){
            @Override
            public void success(Result<TwitterSession> result) {
            // if login success we pass the result objct to login method
             login(result);
            }

            @Override
            public void failure(TwitterException exception) {
                // in case the login failed this will print
                Log.d("TwitterKit","Login with twitter failure", exception);
            }

            }
        }
}
