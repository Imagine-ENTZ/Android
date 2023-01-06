package com.example.imaginecup.missionPage;

import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MissionFragment extends Fragment implements View.OnClickListener {
    ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> latestMissionListAdapter;

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

        for (int i = 0; i < 15; i++) {
            //add the new row to the list
            timelineRowsList.add(createRandomTimelineRow(i));
        }

        latestMissionListAdapter = new TimelineViewAdapter(view.getContext(),0, timelineRowsList, true);
        System.out.println(view.getContext() + "hi!");
        // Add Random Rows to the List



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
                        latestMissionListAdapter.add(createRandomTimelineRow(i));
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

    public TimelineRow createRandomTimelineRow(int id) {

        // Create new timeline row (pass your Id)
        TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional)
        myRow.setDate(getRandomDate());
        //to set the row Title (optional)
        myRow.setTitle("Title " + id);
        //to set the row Description (optional)
        myRow.setDescription("Description " + id);
        //to set the row bitmap image (optional)
        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.calendar_icon + getRandomNumber(0, 10)));
        //to set row Below Line Color (optional)
        myRow.setBellowLineColor(getRandomColor());
        //to set row Below Line Size in dp (optional)
        myRow.setBellowLineSize(getRandomNumber(2, 25));
        //to set row Image Size in dp (optional)
        myRow.setImageSize(getRandomNumber(25, 40));
        //to set background color of the row image (optional)
        myRow.setBackgroundColor(getRandomColor());
        //to set the Background Size of the row image in dp (optional)
        myRow.setBackgroundSize(getRandomNumber(25, 40));
        //to set row Date text color (optional)
        myRow.setDateColor(getRandomColor());
        //to set row Title text color (optional)
        myRow.setTitleColor(getRandomColor());
        //to set row Description text color (optional)
        myRow.setDescriptionColor(getRandomColor());

        return myRow;
    }

    //Random Methods
    public int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ;
        return color;
    }

    public int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * max);
    }


    public Date getRandomDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("02/09/2015");
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            endDate = new Date(random);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

}