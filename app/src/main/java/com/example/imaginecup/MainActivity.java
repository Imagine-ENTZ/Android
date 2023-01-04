package com.example.imaginecup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivCalender;
    private BarChart chartStressLevel;
    private CalendarActivity calendarActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        context = this;

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        ivCalender = findViewById(R.id.imageview_calendar);

        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarActivity = new CalendarActivity(context, "test");
                calendarActivity.show();
            }
        });

        chartStressLevel = findViewById(R.id.chart_stress_index);
        initChart();
        setChartData();
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

