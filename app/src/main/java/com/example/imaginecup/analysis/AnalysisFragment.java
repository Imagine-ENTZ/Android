package com.example.imaginecup.analysis;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imaginecup.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class AnalysisFragment extends Fragment {

    private Context context;
    private ImageView ivCalender;
    private BarChart chartAngerLevel;
    private CalendarDialog calendarDialog;
    private HorizontalCalendarView horizontalCalendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        context = container.getContext();

        ivCalender = view.findViewById(R.id.imageview_calendar);
        chartAngerLevel = view.findViewById(R.id.chart_stress_index);
        horizontalCalendarView = view.findViewById(R.id.horizontal_calendar);

        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDialog = CalendarDialog.newInstance();
                calendarDialog.show(getChildFragmentManager(), CalendarDialog.TAG_EVENT_DIALOG);
            }
        });

        initChart();      //bar chart
        setChartData();

        initHorizontalCalendar(view);   //horizontal calendar

        return view;
    }

    private void setAxis(){
        XAxis xAxis = chartAngerLevel.getXAxis();          //차트 뒷배경 그리드 삭제
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);

        YAxis axisLeft = chartAngerLevel.getAxisLeft();
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(false);

        YAxis axisRight = chartAngerLevel.getAxisRight();
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);

        ArrayList<String> time = new ArrayList<>();

        for(int index = 0; index < 5; index++){
            time.add(Integer.toString(index));
        }

        xAxis.isEnabled();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);             //x축 데이터 아래에 표시
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(time));

//        ArrayList<String> level = new ArrayList<>();
//        level.add("Low");
//        level.add("High");

//        axisLeft.setValueFormatter(new IndexAxisValueFormatter(level));

        axisLeft.setDrawLabels(false);     //왼쪽 데이터 라벨 삭제
        axisRight.setDrawLabels(false);    //오른쪽 데이터 라벨 삭제

        chartAngerLevel.getLegend().setEnabled(false);  //차트 범례 삭제
    }

    private void initChart(){
        chartAngerLevel.setDrawGridBackground(false);
        chartAngerLevel.setDrawBarShadow(false);
        chartAngerLevel.setDrawBorders(false);

        chartAngerLevel.getDescription().setEnabled(false);
        chartAngerLevel.setTouchEnabled(false);
        chartAngerLevel.setBackgroundColor(Color.WHITE);

        setAxis();

        chartAngerLevel.animateX(1000);        //차트 애니메이션
        chartAngerLevel.animateY(1000);

        CustomBarChartRender barChartRender = new CustomBarChartRender(chartAngerLevel, chartAngerLevel.getAnimator(),
                chartAngerLevel.getViewPortHandler());

        barChartRender.setRadius(50);  //그래프 상단 round 효과
        chartAngerLevel.setRenderer(barChartRender);
    }

    private void setChartData(){
        chartAngerLevel.setScaleEnabled(false);
        ArrayList<BarEntry> levelList = new ArrayList<>();

        levelList.add(new BarEntry(0, 3));
        levelList.add(new BarEntry(1, 1));
        levelList.add(new BarEntry(2, 4));
        levelList.add(new BarEntry(3, 2));
        levelList.add(new BarEntry(4, 1));

        BarDataSet barDataSet = new BarDataSet(levelList, "stress level");
        barDataSet.setGradientColor(Color.parseColor("#00FF5722"), Color.parseColor("#FFE11313")); //그래프 색깔
        barDataSet.setDrawValues(false);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);   //그래프 너비

        chartAngerLevel.setData(barData);
    }

    private void initHorizontalCalendar(View view){
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);           //시작 날짜

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);             //종료 날짜

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view, horizontalCalendarView.getId())
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                int year, month, day;

                year = date.get(Calendar.YEAR);
                month = date.get(Calendar.MONTH);
                day = date.get(Calendar.DATE);

                Log.v("선택 날짜 : ", year + "년 " + month + "월 " + day + "일");
            }
        });

    }

}
