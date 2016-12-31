package br.com.curiousguy.aerocar.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.feature.main.MainActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
