package me.keppler.autolocktoggle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LocktimeTileService extends TileService {
    @Override
    public void onClick() {
        super.onClick();

        Tile tile = getQsTile();
        switch (tile.getState()) {
            case Tile.STATE_INACTIVE:
                if (setLocktime(6000000)){
                    tile.setState(Tile.STATE_ACTIVE);
                };
                break;
            case Tile.STATE_ACTIVE:
                if (setLocktime(0)){
                    tile.setState(Tile.STATE_INACTIVE);
                };
            default:
                tile.setState(Tile.STATE_INACTIVE);
                break;
        }
        tile.updateTile();
    }

    boolean setLocktime(int value) {
        Context context = getApplicationContext();

        try{
            Settings.Secure.putLong(getContentResolver(), "lock_screen_lock_after_timeout", value);
            String new_setting = String.valueOf(Settings.Secure.getLong(getContentResolver(), "lock_screen_lock_after_timeout", 0)/1000);
            Log.d("Current", new_setting);
            Toast toast = Toast.makeText(context, "Screen locks after " + new_setting + " s", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        } catch (Exception e) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityAndCollapse(intent);
            Toast toast = Toast.makeText(context, "Setting auto lock timer failed, please check setup guide", Toast.LENGTH_LONG);
            TextView toastTextView = (TextView)toast.getView().findViewById(android.R.id.message);
            toastTextView.setTextColor(Color.RED);
            toast.show();
            return false;
        }
    }
}
