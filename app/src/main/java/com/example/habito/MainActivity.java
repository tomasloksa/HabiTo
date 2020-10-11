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
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.Main_toolbar);
        setSupportActionBar(mainToolbar);
    }

    public void addItem(View view) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:

        if (page != null) {
            if (page instanceof HabitsFragment)
                ((HabitsFragment) page).addItem("rudud");
            else if (page instanceof TasksFragment) {
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
                ViewPager viewPager = findViewById(R.id.view_pager);
                Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
                if (page != null)
                    ((TasksFragment) page).addItem(data.getData().toString());
            }
        });
        }
    }
}