package com.example.imaginecup.analysis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.imaginecup.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

public class CalendarDialog extends DialogFragment {

    private MaterialCalendarView materialCalendarView;
    public static final String TAG_EVENT_DIALOG = "dialog_event";
    private Context context;

    public static CalendarDialog newInstance(){

        CalendarDialog calendarDialog = new CalendarDialog();

        return calendarDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_calendar, container, false);

        context = view.getContext();
        initDialog(view);

        return view;
    }

    private void dismissDialog(){
        this.dismiss();
    }

    private void initDialog(View view){

        materialCalendarView = view.findViewById(R.id.calendar_view);

        materialCalendarView.setTitleFormatter(new MonthArrayTitleFormatter(context.getResources().getTextArray(R.array.months)));
        materialCalendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(context.getResources().getTextArray(R.array.weekdays)));

        materialCalendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);

        materialCalendarView.addDecorators(new CalendarDialog.DayDecorator(context));

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        if(window == null) return;

        WindowManager.LayoutParams params = window.getAttributes();

        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        window.setAttributes(params);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //fragment 기본 padding 삭제
        window.setGravity(Gravity.BOTTOM);
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
