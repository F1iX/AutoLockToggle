package me.keppler.autolocktoggle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Current", String.valueOf(Settings.Secure.getLong(getContentResolver(), "lock_screen_lock_after_timeout", 5000)));
    }
}