package com.jlkj.newsofmonday.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.core.app.ApplicationProvider;

import com.example.newsofmonday.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.P}) // Configure for a specific SDK version for Robolectric
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Before
    public void setUp() throws Exception {
        // Build and create the activity
        loginActivity = Robolectric.buildActivity(LoginActivity.class)
                                .create()
                                .resume()
                                .get();
        etUsername = loginActivity.findViewById(R.id.et_username);
        etPassword = loginActivity.findViewById(R.id.et_password);
        btnLogin = loginActivity.findViewById(R.id.btn_login);
    }

    @Test
    public void loginButton_withValidCredentials_startsMainActivity() {
        etUsername.setText("testuser");
        etPassword.setText("testpass");
        btnLogin.performClick();

        // Get the ShadowActivity to inspect the started intent
        ShadowActivity shadowActivity = shadowOf(loginActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();

        // Assert that an intent was started
        assertNotNull("Intent should have been started", startedIntent);
        // Assert that the intent is for MainActivity
        assertEquals("Intent should be for MainActivity",
                     MainActivity.class.getCanonicalName(),
                     startedIntent.getComponent().getClassName());
        // Assert that LoginActivity finished
        // assertTrue("LoginActivity should be finishing", loginActivity.isFinishing());
        // isFinishing might not be immediately true with Robolectric, alternative check:
         assertEquals(true, shadowActivity.isFinishing());


    }

    @Test
    public void loginButton_withEmptyUsername_showsErrorAndDoesNotStartMainActivity() {
        // etUsername.setText(""); // Already empty by default
        etPassword.setText("testpass");
        btnLogin.performClick();

        ShadowActivity shadowActivity = shadowOf(loginActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();

        assertNull("Intent should not have been started", startedIntent);
        // Further assertions could check for a Toast message if Robolectric is configured for that.
    }

    @Test
    public void loginButton_withEmptyPassword_showsErrorAndDoesNotStartMainActivity() {
        etUsername.setText("testuser");
        // etPassword.setText(""); // Already empty by default
        btnLogin.performClick();

        ShadowActivity shadowActivity = shadowOf(loginActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();

        assertNull("Intent should not have been started", startedIntent);
    }
}
