package matrial.aka.twitter;

import android.content.Intent;
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
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "GqwOQXxIw66SOW6gltFgi8caU";
    private static final String TWITTER_SECRET = "mqr5G1ZwDHZt9nfGYZhKMbkwFlt6680hMuitsPISdsGPkhubKy";

    // tags that will hold the retrived username and profile image after successful login
    public static final String KEY_USER = "name";
    public static final String profile_img_url = "image_url";

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
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // if login success we pass the result objct to login method
                login(result);
            }

            @Override
            public void failure(TwitterException exception) {
                // in case the login failed this will print
                Log.d("TwitterKit", "Login with twitter failure", exception);
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Adding the login result back to the button
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }




    //method to accept the result object
    public void login(Result<TwitterSession>result) {

        TwitterSession session = result.data;

        //getting the user from the session
        final String username = session.getUserName();

        //this code will fetch the profile image url
        //getting the account service of the user logged in
        Twitter.getApiClient(session).getAccountService().verifyCredentials(true, false, new Callback<User>() {
            @Override
            public void failure(TwitterException exception) {
// here we handle error
            }

            @Override
            public void success(Result<User> result) {
                // if succeed the create  a user object from user.Result.data
                User user = userResult.data;

                //getting the user profile image url
                String profileimage = user.profileImageUrl.replace("_normal", "");

                //creating an intent
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);

                // add ing the values to the intent
                intent.putExtra(KEY_USER, username);
                intent.putExtra(profile_img_url, profileimage);

                //start activity
                startActivity(intent);


            }


        });


    }
}
