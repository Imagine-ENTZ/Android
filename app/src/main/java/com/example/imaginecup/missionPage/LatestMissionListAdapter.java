package com.example.imaginecup.missionPage;

import android.view.View;
import android.widget.BaseAdapter;

import com.transferwise.sequencelayout.SequenceAdapter;
import com.transferwise.sequencelayout.SequenceStep;

import java.util.ArrayList;
import java.util.List;

public class LatestMissionListAdapter extends SequenceAdapter {
    private List<MyItem> items = new ArrayList<>();

    public void addItem(MyItem item) {
        items.add(item);
    }

    public int getCount() {
        return items.size();
    }

    public MyItem getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
