package com.example.imaginecup.missionPage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.imaginecup.MainActivity;
import com.example.imaginecup.R;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.util.ArrayList;

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

        ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
        LatestMissionListAdapter latestMissionListAdapter = new LatestMissionListAdapter(this.getContext(), 0, timelineRowsList, true);
        // Add Random Rows to the List
        for (int i = 0; i < 15; i++) {
            //add the new row to the list
            timelineRowsList.add(latestMissionListAdapter.createRandomTimelineRow(i));
        }


        //Get the ListView and Bind it with the Timeline Adapter
        ListView listView = (ListView) view.findViewById(R.id.timeline_listView);
        listView.setAdapter(latestMissionListAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;


            }

            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {

                    ////on scrolling to end of the list, add new rows
                    for (int i = 0; i < 15; i++) {
                        latestMissionListAdapter.add(latestMissionListAdapter.createRandomTimelineRow(i));
                    }

                }
            }


        });

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
                MissionDialogFragment missionDialogFragment = MissionDialogFragment.getInstance();
                missionDialogFragment.show(getChildFragmentManager(), MissionDialogFragment.TAG_EVENT_DIALOG);
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