package br.com.curiousguy.aerocar.feature.splash;

import android.content.Context;
import android.databinding.ObservableField;

import br.com.curiousguy.aerocar.BuildConfig;
import br.com.curiousguy.aerocar.R;

public class SplashActivityViewModelImpl implements SplashActivityViewModel {

    public final ObservableField<String> version = new ObservableField<>();

    public SplashActivityViewModelImpl(Context context) {
        String unformattedVersion = context.getString(R.string.splash_version);
        version.set(String.format(unformattedVersion, BuildConfig.VERSION_NAME));
    }
}
