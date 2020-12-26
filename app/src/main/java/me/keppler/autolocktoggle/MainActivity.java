package me.keppler.autolocktoggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;

    // The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps
    private ViewPager2 viewPager;

    // The pager adapter, which provides the pages to the view pager widget
    private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Current", String.valueOf(Settings.Secure.getLong(getContentResolver(), "lock_screen_lock_after_timeout", 5000)));

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * Pager Adapter loading individual fragments
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new PageFragment(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}