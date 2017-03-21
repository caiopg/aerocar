package br.com.curiousguy.aerocar.feature.prices;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.curiousguy.aerocar.BaseFragment;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.FragmentPricesBinding;

public class PricesFragment extends BaseFragment {

    FragmentPricesBinding binding;

    public static PricesFragment newInstance() {
        return new PricesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prices, container, false);
        View rootView = binding.getRoot();

        return rootView;
    }
}
