package com.example.imaginecup.missionPage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.imaginecup.R;
import com.transferwise.sequencelayout.SequenceAdapter;
import com.transferwise.sequencelayout.SequenceStep;

import org.qap.ctimelineview.TimelineRow;
import org.qap.ctimelineview.TimelineViewAdapter;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LatestMissionListAdapter extends ArrayAdapter<TimelineRow> {
    Context context;
    int id;
    ArrayList<TimelineRow> data;
    Boolean isSort;

    public LatestMissionListAdapter(@NonNull Context context, int id, ArrayList<TimelineRow> data, Boolean isSort) {
        super(context, id);
        this.id = id;
        this.data = data;
        this.isSort = isSort;
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
        //myRow.setImage(BitmapFactory.decodeResource(getResources, R.drawable.calendar_icon + getRandomNumber(0, 10)));
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
