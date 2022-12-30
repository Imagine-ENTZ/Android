package com.example.imaginecup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private HomeFragment fragmentHome = new HomeFragment();
    private MissionFragment fragmentMission = new MissionFragment();
    private AnalyzingFragment fragmentAnalyzing = new AnalyzingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentAnalyzing).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.menu_home:
                    System.out.println(menuItem);
                    transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
                    //transaction.addToBackStack(null); // 백스택에 추가를 해놔야, fragment 가 교체될 때 삭제되지 않음
                    //transaction.commit();
                    break;
                case R.id.menu_analyzing:
                    System.out.println(menuItem);
                    transaction.replace(R.id.menu_frame_layout, fragmentAnalyzing).commitAllowingStateLoss();
                    break;
                case R.id.menu_mission:
                    System.out.println(menuItem);
                    transaction.replace(R.id.menu_frame_layout, fragmentMission).commitAllowingStateLoss();
                    break;
            }

            return true;
        }
    }
}