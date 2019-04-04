package talitha_koum.milipade.com.app.vhuka;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Timer;
import java.util.TimerTask;

import talitha_koum.milipade.com.app.vhuka.fragments.Login_fragment;
import talitha_koum.milipade.com.app.vhuka.fragments.Signup_fragment;
import talitha_koum.milipade.com.app.vhuka.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);

        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Signup_fragment(), Utils.Login_fragment).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                });

    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login_fragment(), Utils.Login_fragment).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment Signup_fragment = fragmentManager.findFragmentByTag(Utils.Signup_fragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_fragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (Signup_fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied ", Toast.LENGTH_SHORT).show();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 5000);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
