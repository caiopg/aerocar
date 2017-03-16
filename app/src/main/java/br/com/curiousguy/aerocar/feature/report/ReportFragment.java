package br.com.curiousguy.aerocar.feature.report;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.curiousguy.aerocar.BaseFragment;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.FragmentReportBinding;
import br.com.curiousguy.aerocar.enums.RequestCode;

public class ReportFragment extends BaseFragment {

    FragmentReportBinding binding;

    private ReportViewModelImpl reportViewModel;

    public static ReportFragment newInstance() {
        return new ReportFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        reportViewModel = new ReportViewModelImpl(getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false);
        binding.setViewModel(reportViewModel);
        View rootView = binding.getRoot();

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        reportViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
