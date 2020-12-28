package me.keppler.autolocktoggle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;

    // The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps
    private ViewPager2 viewPager;

    // Tab layout for indicator dots
    private TabLayout tabLayout;

    // The pager adapter, which provides the pages to the view pager widget
    private FragmentStateAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AutoLockToggle", "CurrentLockTimer: " + String.valueOf(Settings.Secure.getLong(getContentResolver(), "lock_screen_lock_after_timeout", 0)));

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = findViewById(R.id.tabs);
        // Attach TabLayoutMediator automatically updating active tab dot indicator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
        }).attach();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_configure_timer:
                DialogFragment dialog = new SetLockTimerDialog();
                dialog.show(getSupportFragmentManager(), "SetLockTimerDialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}