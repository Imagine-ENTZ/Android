package com.example.imaginecup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class AnalysisFragment extends Fragment {

    private Context context;
    private ImageView ivCalender;
    private BarChart chartStressLevel;
    private CalendarActivity calendarActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_analysis, container, false);

        context = container.getContext();

        ivCalender = view.findViewById(R.id.imageview_calendar);

        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarActivity = new CalendarActivity(context, "test");
                calendarActivity.show();
            }
        });

        chartStressLevel = view.findViewById(R.id.chart_stress_index);
        initChart();
        setChartData();

        return view;
    }

    private void initChart(){
        chartStressLevel.setDrawGridBackground(false);
        chartStressLevel.setDrawBarShadow(false);
        chartStressLevel.setDrawBorders(false);

        Description description = new Description();
        chartStressLevel.setDescription(description);

        chartStressLevel.animateX(1000);
        chartStressLevel.animateY(1000);
    }

    private void setChartData(){
        chartStressLevel.setScaleEnabled(false);
        ArrayList levelList = new ArrayList<>();

        for(int index = 0; index < 5; index++){
            levelList.add(new BarEntry(index, index));
        }

        BarDataSet barDataSet = new BarDataSet(levelList, "stress level");
        BarData barData = new BarData(barDataSet);
        chartStressLevel.setData(barData);
    }

}
