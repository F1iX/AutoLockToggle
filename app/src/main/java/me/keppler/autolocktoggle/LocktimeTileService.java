package me.keppler.autolocktoggle;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

public class LocktimeTileService extends TileService {
    @Override
    public void onClick() {
        super.onClick();

        Tile tile = getQsTile();
        switch (tile.getState()) {
            case Tile.STATE_INACTIVE:
                setLocktime(6000000);
                tile.setState(Tile.STATE_ACTIVE);
                break;
            case Tile.STATE_ACTIVE:
                setLocktime(0);
            default:
                tile.setState(Tile.STATE_INACTIVE);
                break;
        }
        tile.updateTile();
    }

    void setLocktime(int value) {
        Context context = getApplicationContext();

        // Check whether has the write settings permission or not
        boolean settingsCanWrite = Settings.System.canWrite(context);

        if(!settingsCanWrite) {
            // If do not have write settings permission then open the Can modify system settings panel.
            Log.d("Current", "Can't write - opening dialog!");
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Log.d("Current", "Can write!");
            Settings.Secure.putLong(getContentResolver(), "lock_screen_lock_after_timeout", value);
        }
        Log.d("Current", String.valueOf(Settings.Secure.getLong(getContentResolver(), "lock_screen_lock_after_timeout", 5000)));

    }
}
