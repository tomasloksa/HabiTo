package com.example.habito;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.habito.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int CREATE_TASK = 1;
    static final int CREATE_HABIT = 2;

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton add_button = findViewById(R.id.add_button);

        // Toolbar
        Toolbar mainToolbar = findViewById(R.id.Main_toolbar);
        setSupportActionBar(mainToolbar);
    }

    public void addItem(View view) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        this.currentFragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        if (this.currentFragment != null) {
            if (this.currentFragment instanceof HabitsFragment) {
                Intent intent = new Intent(this, AddHabit.class);
                startActivityForResult(intent, this.CREATE_HABIT);
            }
            else if (this.currentFragment instanceof TasksFragment) {
                Intent intent = new Intent(this, AddTask.class);
                startActivityForResult(intent, this.CREATE_TASK);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.CREATE_TASK) {
            if (resultCode == RESULT_OK) {
                ((TasksFragment) this.currentFragment).addItem(data.getData().toString());
            }
        }
        else if (requestCode == this.CREATE_HABIT) {
            if (resultCode == RESULT_OK) {
                ((HabitsFragment) this.currentFragment).addItem(data.getData().toString());
            }
        }
    }
}