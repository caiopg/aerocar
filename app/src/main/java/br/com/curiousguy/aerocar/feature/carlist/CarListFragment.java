package br.com.curiousguy.aerocar.feature.carlist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.curiousguy.aerocar.BaseFragment;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.FragmentCarListBinding;

public class CarListFragment extends BaseFragment {

    FragmentCarListBinding binding;

    public static CarListFragment newInstance() {
        return new CarListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_car_list, container, false);
        View rootView = binding.getRoot();

        return rootView;
    }
}
