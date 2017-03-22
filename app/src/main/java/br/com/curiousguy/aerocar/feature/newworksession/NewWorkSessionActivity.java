package br.com.curiousguy.aerocar.feature.newworksession;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.parceler.Parcels;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.databinding.ActivityNewWorkSessionBinding;
import br.com.curiousguy.aerocar.model.WorkSession;

public class NewWorkSessionActivity extends BaseActivity {

    public static final String EDIT_WORK_SESSION = "edit.work.session";

    ActivityNewWorkSessionBinding binding;
    NewWorkSessionViewModelImpl viewModel;

    public static Intent getStartIntent(Context context, WorkSession workSession) {
        Intent intent = new Intent(context, NewWorkSessionActivity.class);
        intent.putExtra(EDIT_WORK_SESSION, Parcels.wrap(workSession));

        return intent;
    }


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, NewWorkSessionActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_work_session);
        if(extras != null && extras.containsKey(EDIT_WORK_SESSION)) {
            WorkSession workSession = Parcels.unwrap(extras.getParcelable(EDIT_WORK_SESSION));
            viewModel = new NewWorkSessionViewModelImpl(this, workSession);
        } else {
            viewModel = new NewWorkSessionViewModelImpl(this);
        }
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

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.newWorkSessionToolbar.toolbar;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.new_work_session_toolbar_title));
    }

}
