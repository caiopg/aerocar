package br.com.curiousguy.aerocar.feature.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.BaseFragment;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.RequestCode;
import br.com.curiousguy.aerocar.feature.worksessionlist.WorkSessionListFragment;
import br.com.curiousguy.aerocar.feature.newcar.NewCarActivity;
import br.com.curiousguy.aerocar.databinding.ActivityMainBinding;
import br.com.curiousguy.aerocar.feature.report.ReportFragment;
import br.com.curiousguy.aerocar.feature.settings.SettingsFragment;


public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupToolbar();
        setupViewpager();
        setupBottomNavigationView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_car) {
            startAddNewCarActivity();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callChildOnActivityResult(requestCode, resultCode, data);
    }

    private void callChildOnActivityResult(int requestCode, int resultCode, Intent data) {
        ViewPager viewPager = binding.mainViewpager;
        MainPagerAdapter adapter = (MainPagerAdapter) viewPager.getAdapter();

        for(int i = 0; i < adapter.getCount(); i++) {
            Fragment fragment = adapter.getItem(i);
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = binding.mainBottomNavigation;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ViewPager viewPager = binding.mainViewpager;

                switch (item.getItemId()) {
                    case R.id.action_cars:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_config:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_report:
                        viewPager.setCurrentItem(2);
                        break;
                }

                return true;
            }
        });
    }

    private void setupViewpager() {
        List<BaseFragment> fragments = populateFragmentList();

        final ViewPager viewPager = binding.mainViewpager;
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BottomNavigationView bottomNavigationView = binding.mainBottomNavigation;
                bottomNavigationView.getMenu().getItem(position).setChecked(true);

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<BaseFragment> populateFragmentList() {
        List<BaseFragment> fragments = new ArrayList<>();

        fragments.add(WorkSessionListFragment.newInstance());
        fragments.add(SettingsFragment.newInstance());
        fragments.add(ReportFragment.newInstance());

        return fragments;
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.mainToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    private void startAddNewCarActivity() {
        Intent intent = NewCarActivity.getStartIntent(this);
        startActivityForResult(intent, RequestCode.NEW_CAR.getRequestCode());
    }

}
