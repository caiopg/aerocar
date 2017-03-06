package br.com.curiousguy.aerocar.feature.worksessionlist;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.curiousguy.aerocar.BaseFragment;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.FragmentWorkSessionListBinding;
import br.com.curiousguy.aerocar.db.DbFacade;
import br.com.curiousguy.aerocar.db.RealmFacade;
import br.com.curiousguy.aerocar.enums.RequestCode;

public class WorkSessionListFragment extends BaseFragment {

    FragmentWorkSessionListBinding binding;

    private DbFacade dbFacade;

    public static WorkSessionListFragment newInstance() {
        return new WorkSessionListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_session_list, container, false);
        View rootView = binding.getRoot();
        dbFacade = new RealmFacade();

        setupListView(binding.workSessionListview);

        return rootView;
    }

    private void setupListView(ListView carListView) {
        WorkSessionListAdapter adapter = new WorkSessionListAdapter(getActivity(), dbFacade.fetchActiveWorkSessions());
        carListView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK && requestCode == RequestCode.NEW_WORK_SESSION.getRequestCode()) {
            setupListView(binding.workSessionListview);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
