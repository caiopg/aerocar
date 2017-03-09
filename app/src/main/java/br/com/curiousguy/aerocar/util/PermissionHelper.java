package br.com.curiousguy.aerocar.util;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

import br.com.curiousguy.aerocar.enums.RequestCode;

public class PermissionHelper {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static boolean verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return permission == activity.getPackageManager().PERMISSION_GRANTED;
    }

    public static void requestPermissions(Activity activity) {
        if (!verifyStoragePermissions(activity)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RequestCode.REQUEST_PERMISSION.getRequestCode()
            );
        }
    }

}
