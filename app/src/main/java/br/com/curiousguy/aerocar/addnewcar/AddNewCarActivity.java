package br.com.curiousguy.aerocar.addnewcar;

import android.content.Context;
import android.content.Intent;

import br.com.curiousguy.aerocar.BaseActivity;

public class AddNewCarActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AddNewCarActivity.class);

        return intent;
    }
}
