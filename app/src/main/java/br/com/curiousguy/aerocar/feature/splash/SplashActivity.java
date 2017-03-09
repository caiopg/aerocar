package br.com.curiousguy.aerocar.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import br.com.curiousguy.aerocar.BaseActivity;
import br.com.curiousguy.aerocar.R;
import br.com.curiousguy.aerocar.enums.RequestCode;
import br.com.curiousguy.aerocar.feature.main.MainActivity;
import br.com.curiousguy.aerocar.util.PermissionHelper;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(PermissionHelper.verifyStoragePermissions(SplashActivity.this)) {
                    goToMainActivity();
                } else {
                    PermissionHelper.requestPermissions(SplashActivity.this);
                }

            }
        }, 2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == RequestCode.REQUEST_PERMISSION.getRequestCode()) {

            boolean permissionsGranted = verifyIfPermissionsGranted(grantResults);

            if(permissionsGranted) {
                goToMainActivity();
            } else {
                // TODO: 09/03/17 show message
            }
        }
    }

    private boolean verifyIfPermissionsGranted(@NonNull int[] grantResults) {
        for(int grantResult : grantResults) {
            if (grantResult != getPackageManager().PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
