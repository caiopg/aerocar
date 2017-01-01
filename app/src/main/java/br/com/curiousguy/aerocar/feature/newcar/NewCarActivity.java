package br.com.curiousguy.aerocar.feature.newcar;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.ActivityNewCarBinding;

public class NewCarActivity extends BaseActivity {

    ActivityNewCarBinding binding;
    NewCarViewModelImpl viewModel;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, NewCarActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_car);
        viewModel = new NewCarViewModelImpl();
        binding.setViewModel(viewModel);

        setupToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.newCarToolbar.toolbar;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.new_car_toolbar_title));
    }

}
