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

import org.qap.ctimelineview.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MissionFragment extends Fragment implements View.OnClickListener {
    ArrayList<com.example.imaginecup.missionPage.TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<com.example.imaginecup.missionPage.TimelineRow> latestMissionListAdapter;

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

        latestMissionListAdapter = new TimelineViewAdapter2(view.getContext(),0, timelineRowsList, true);
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

    ///// 어댑터 분리 못함

    public com.example.imaginecup.missionPage.TimelineRow createRandomTimelineRow(int id) {

        // Create new timeline row (pass your Id)
        com.example.imaginecup.missionPage.TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional) 시간대 기록
        myRow.setDate(getRandomDate());
        //to set the row Title (optional) 한 일 기록
        myRow.setTitle("Title " + id);
        //to set the row Description (optional) 설명.. 뭘 했나요 ?
        myRow.setDescription("Description " + id);
        //to set the row bitmap image (optional) 이미지 (동그라미에 들어갈 이미지)
        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.calendar_icon + getRandomNumber(0, 10)));
        //to set row Below Line Color (optional) 줄 색깔
        myRow.setBellowLineColor(getColor(89, 104, 244));
        //to set row Below Line Size in dp (optional) 줄 사이즈 (두께)
        myRow.setBellowLineSize(getRandomNumber(3, 3));
        //to set row Image Size in dp (optional) 이미지 사이즈
        myRow.setImageSize(getRandomNumber(25, 25));
        //to set background color of the row image (optional) 이미지 배경 색깔 (동그라미 색이라고 보자)
        myRow.setBackgroundColor(getColor(89, 104, 244));
        //to set the Background Size of the row image in dp (optional) (배경 사이즈)
        myRow.setBackgroundSize(getRandomNumber(15, 15));
        //to set row Date text color (optional)
        myRow.setDateColor(getColor(192, 192, 192));
        //to set row Title text color (optional)
        myRow.setTitleColor(getColor(0, 0, 0));
        //to set row Description text color (optional)
        myRow.setDescriptionColor(getColor(0, 0, 0));

        return myRow;
    }

    // 색상 얻기
    public int getColor(int red, int green, int blue) {
        // signature (89, 104, 244)
        // silver (192, 192, 192)
        // black (0, 0, 0)
        int color = Color.rgb(red, green, blue);

        return color;
    }

    // 랜덤 넘버 아님. 그냥 크기 설정
    public int getRandomNumber(int min, int max) {
        return min + max;
    }


    public String getRandomDate() {
        long now = System.currentTimeMillis();
        Date today = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String todayString = simpleDateFormat.format(today);
        Date startDate = null;
        Date date;

        try {
            startDate = simpleDateFormat.parse(todayString);
            today = startDate;
            System.out.println(todayString);
            System.out.println(today);
            System.out.println("hello!!");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return todayString;
    }

}