package codcat.magnitrailstation.feature.launch_screen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.feature.main_screen.MainScreen;

public class LaunchScreen extends DaggerAppCompatActivity implements LaunchScreenMvp.View {

    @Inject LaunchScreenMvp.Presenter presenter;

    @BindView(R.id.iv_app_icon) ImageView ivAppIcon;
    @BindView(R.id.pb_loading) ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        ButterKnife.bind(this);

        setProgressBar();
        setAppIcon();
        presenter.onViewInitialized();
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void startLaunchAnimation() {
        ivAppIcon.animate().alpha(1f).setDuration(1500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                presenter.onLaunchAnimEnd();
            }
        });
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, MainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showLaunchLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLaunchLoading() {
        pbLoading.setVisibility(View.INVISIBLE);
    }

    private void setProgressBar() {
        pbLoading.setVisibility(View.INVISIBLE);
        pbLoading.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.white),
                android.graphics.PorterDuff.Mode.SRC_IN);
    }
    private void setAppIcon() {
        ivAppIcon.setAlpha(0f);
    }
}
