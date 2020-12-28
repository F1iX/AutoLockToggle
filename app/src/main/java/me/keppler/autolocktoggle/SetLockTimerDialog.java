package me.keppler.autolocktoggle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class SetLockTimerDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Access shared preferences for stored settings value
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getText(R.string.app_name).toString(), Context.MODE_PRIVATE);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogView = inflater.inflate(R.layout.set_lock_timer_dialog, null);
        builder.setView(dialogView);

        // Display recent stored settings value
        EditText textEdit = (EditText)dialogView.findViewById(R.id.locktime);
        textEdit.setText(Integer.toString(sharedPref.getInt(getString(R.string.locktime_key), 6000)));

        builder.setMessage(R.string.dialog_configure_timer)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int locktime_s = Integer.parseInt(textEdit.getText().toString());
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(getString(R.string.locktime_key), locktime_s);
                        editor.apply();
                    }
                });

        AlertDialog dialog = builder.create();

        textEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0 && s.toString().matches("[0-9]+")) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });

        dialog.show();
        return dialog;
    }
}
