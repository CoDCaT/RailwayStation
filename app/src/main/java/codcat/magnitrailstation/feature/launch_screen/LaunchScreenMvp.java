package codcat.magnitrailstation.feature.launch_screen;

import codcat.magnitrailstation.base.MvpPresenter;
import codcat.magnitrailstation.base.MvpView;

public interface LaunchScreenMvp {

    interface View extends MvpView {
        void startLaunchAnimation();
        void navigateToMainScreen();

        void showLaunchLoading();
        void stopLaunchLoading();
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void onViewInitialized();
        void onLaunchAnimEnd();
    }
}
