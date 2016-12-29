package br.com.curiousguy.aerocar.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.addnewcar.AddNewCarActivity;
import br.com.curiousguy.aerocar.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModelImpl();
        binding.setMainViewModel(viewModel);

        setupToolbar();
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

    private void setupToolbar() {
        Toolbar toolbar = binding.mainToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    private void startAddNewCarActivity() {
        Intent intent = AddNewCarActivity.getStartIntent(this);
        startActivity(intent);
    }

}
