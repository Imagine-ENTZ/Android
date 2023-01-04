package com.example.imaginecup.missionPage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.imaginecup.R;

public class MissionFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission, container, false);

        FrameLayout recommendedMissionButton1 = view.findViewById(R.id.mission_box_1);
        FrameLayout recommendedMissionButton2 = view.findViewById(R.id.mission_box_2);
        Button missionButton1 = view.findViewById(R.id.mission_button_1);
        Button missionButton2 = view.findViewById(R.id.mission_button_2);
        Button missionButton3 = view.findViewById(R.id.mission_button_3);
        Button missionButton4 = view.findViewById(R.id.mission_button_4);
        Button missionButton5 = view.findViewById(R.id.mission_button_5);


        recommendedMissionButton1.setOnClickListener(this);
        recommendedMissionButton2.setOnClickListener(this);
        missionButton1.setOnClickListener(this);
        missionButton2.setOnClickListener(this);
        missionButton3.setOnClickListener(this);
        missionButton4.setOnClickListener(this);
        missionButton5.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.mission_box_1:
                // 데이터를 다이얼로그로 보내기
                Bundle args = new Bundle();
                args.putString("key", "value");

                MissionDialogFragment dialog = new MissionDialogFragment();
                dialog.setArguments(args); // 데이터 전달
                dialog.show(getActivity().getSupportFragmentManager(),"tag");

                break;

            case R.id.mission_box_2:
                break;
            case R.id.mission_button_1:
                break;
            case R.id.mission_button_2:
                break;
            case R.id.mission_button_3:
                break;
            case R.id.mission_button_4:
                break;
            case R.id.mission_button_5:
                break;
        }
    }
}