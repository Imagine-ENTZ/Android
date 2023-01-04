package com.example.imaginecup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.imaginecup.missionPage.MissionFragment;
import com.github.kwasow.bottomnavigationcircles.BottomNavigationCircles;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private MissionFragment fragmentMission = new MissionFragment();
    private AnalysisFragment fragmentAnalyzing = new AnalysisFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentHome).commit();

        BottomNavigationCircles bottomNavigationCircles = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationCircles.setOnItemSelectedListener(new ItemSelectedListener());
        //BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        //bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationCircles.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    //System.out.println(menuItem);
                    transaction.replace(R.id.menu_frame_layout, fragmentHome).commit();
                    //transaction.addToBackStack(null); // 백스택에 추가를 해놔야, fragment 가 교체될 때 삭제되지 않음
                    //transaction.commit();
                    return true;
                case R.id.menu_analyzing:
                    transaction.replace(R.id.menu_frame_layout, fragmentAnalyzing).commit();
                    return true;
                case R.id.menu_mission:
                    //System.out.println(menuItem);
                    transaction.replace(R.id.menu_frame_layout, fragmentMission).commit();
                    return true;
            }

            return false;
        }
    }
}

