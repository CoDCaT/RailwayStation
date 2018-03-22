package codcat.magnitrailstation.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import codcat.magnitrailstation.BuildConfig;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.di.scopes.ActivityScope;
import codcat.magnitrailstation.feature.main_screen.MainScreenMvp;

@ActivityScope
public class AboutFragment extends DaggerFragment{

    @Inject
    MainScreenMvp.Presenter presenter;

    @BindView(R.id.tv_app_version) TextView tvAppVersion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        ButterKnife.bind(this, view);
        tvAppVersion.setText(String.format("%s %s", getString(R.string.appVersion), BuildConfig.VERSION_NAME));
        return view;
    }

    @Inject
    public AboutFragment() {
        // Requires empty public constructor for @ContributesAndroidInjector
    }
}
