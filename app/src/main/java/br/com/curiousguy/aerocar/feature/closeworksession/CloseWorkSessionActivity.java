package br.com.curiousguy.aerocar.feature.closeworksession;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import org.parceler.Parcels;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.ActivityCloseWorkSessionBinding;
import br.com.curiousguy.aerocar.model.WorkSession;

public class CloseWorkSessionActivity extends BaseActivity {

    public static final String CURRENT_WORK_SESSION = "current.work.session";

    ActivityCloseWorkSessionBinding binding;
    CloseWorkSessionViewModelImpl viewModel;

    public static Intent getStartIntent(Context context, WorkSession workSession) {
        Intent intent = new Intent(context, CloseWorkSessionActivity.class);

        intent.putExtra(CURRENT_WORK_SESSION, Parcels.wrap(workSession));

        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null || !bundle.containsKey(CURRENT_WORK_SESSION)) {
            throw new IllegalStateException("Missing work session.");
        }

        WorkSession workSession = Parcels.unwrap(bundle.getParcelable(CURRENT_WORK_SESSION));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_close_work_session);
        viewModel = new CloseWorkSessionViewModelImpl(this, workSession);
        binding.setViewModel(viewModel);
    }
}

