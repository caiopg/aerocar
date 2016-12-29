package br.com.curiousguy.aerocar.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
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
    }
}
