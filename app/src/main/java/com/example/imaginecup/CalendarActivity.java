package com.example.imaginecup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

public class CalendarActivity extends Dialog {

    private MaterialCalendarView materialCalendarView;

    public CalendarActivity(@NonNull Context context, String content) {
        super(context);
        setContentView(R.layout.activity_calendar);

        materialCalendarView = findViewById(R.id.calendar_view);

        materialCalendarView.setTitleFormatter(new MonthArrayTitleFormatter(context.getResources().getTextArray(R.array.months)));
        materialCalendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(context.getResources().getTextArray(R.array.weekdays)));

        materialCalendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);

        materialCalendarView.addDecorators(new CalendarActivity.DayDecorator(context));

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                dismiss();
            }
        });
    }

    private static class DayDecorator implements DayViewDecorator {

        private final Drawable drawable;

        private DayDecorator(Context context) {
            drawable = ContextCompat.getDrawable(context, R.drawable.calendar_selector);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return true;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setSelectionDrawable(drawable);
        }
    }
}
