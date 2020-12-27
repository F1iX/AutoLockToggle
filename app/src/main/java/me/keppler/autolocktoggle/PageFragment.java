package me.keppler.autolocktoggle;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PageFragment extends Fragment {
    private int pos;

    PageFragment(int pos){
        this.pos = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.desc_text);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.desc_img);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.desc_img2);
        ImageView imageViewBg = (ImageView) rootView.findViewById(R.id.bg_img);
        Button button = (Button) rootView.findViewById(R.id.desc_button);

        switch (pos){
            case 0:
                textView.setText(getText(R.string.intro));
                imageViewBg.setImageDrawable(getResources().getDrawable(R.drawable.bike_teaser));
                button.setVisibility(View.GONE);
                break;
            case 1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.desc0_1_adb_grant_permission, null));
                textView.setText(getText(R.string.desc0));
                button.setVisibility(View.GONE);
                break;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.desc1_1_screen_lock_settings, null));
                imageView2.setImageDrawable(getResources().getDrawable(R.drawable.desc1_2_disable_power_instant_lock, null));
                textView.setText(getText(R.string.desc1));

                button.setText("Screen Lock Settings");
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
                    }
                });
                break;
            case 3: // TODO: Not needed with ADB command
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.desc2_1_select_modify_system_settings, null));
                imageView2.setImageDrawable(getResources().getDrawable(R.drawable.desc2_2_enable_modify_system_settings, null));
                textView.setText(getText(R.string.desc2));

                button.setText("Modify System Settings");
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS));
                    }
                });
                break;
            default:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.desc3_1_edit_drawer, null));
                imageView2.setImageDrawable(getResources().getDrawable(R.drawable.desc3_2_add_tile, null));
                textView.setText(getText(R.string.desc3));
                button.setVisibility(View.GONE);
                break;

        }
        return rootView;
    }
}
