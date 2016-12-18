package br.com.curiousguy.aerocar.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import br.com.curiousguy.aerocar.main.MainActivity;

public class ViewModel implements SplashViewModel {

    Context context;

    public ViewModel(Context context) {
        this.context = context;
    }

    @Override
    public void goToMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        }, 2000);
    }
}
