package com.example.imaginecup.missionPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.imaginecup.R;

public class MissionDialogFragment extends DialogFragment implements View.OnClickListener {
    private Fragment fragment;

    public static final String TAG_EVENT_DIALOG = "dialog_event";

    public static MissionDialogFragment getInstance(){
        MissionDialogFragment missionDialogFragment = new MissionDialogFragment();
        return missionDialogFragment;
    }
/*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_start_mission)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // START THE MISSION!
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_dialog, container, false);

        Button startButton = (Button)view.findViewById(R.id.dialog_start_button);
        Button cancelButton = (Button)view.findViewById(R.id.dialog_cancel_button);
        startButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        setCancelable(false);

        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1000;
        params.height = 1000;
        window.setAttributes(params);
    }

 */
}