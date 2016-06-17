package matrial.aka.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by geckozila on 17/06/16.
 */
public class ProfileActivity extends AppCompatActivity {

    // image loader object
    private ImageLoader imageLoader;
    //net work image view object
    private NetworkImageView profileImage;

    // text view object
    private TextView textviewusername;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        // initialize view
        profileImage = (NetworkImageView) findViewById(R.id.profileImage);
        textviewusername = (TextView) findViewById(R.id.textViewUsername);

        //getting intent
        Intent intent = getIntent();

        // getting values to the intent
        String username = intent.getStringExtra(MainActivity.KEY_USER);
        String profileImageUrl = intent.getStringExtra(MainActivity.profile_img_url);

        //loading image...
        imageLoader = CustomVolleyRequest.getInstance(this).getImageLoader();
        imageLoader.get(profileImageUrl,ImageLoader.getImageListener(profileImage,R.mipmap.ic_launcher,android.R.drawable.ic_dialog_alert));
        profileImage.setImageUrl(profileImageUrl,imageLoader);

        //setting the user name in the text view
        textviewusername.setText("@"+username);
    }
}
