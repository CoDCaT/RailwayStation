package codcat.magnitrailstation.feature.launch_screen;

import dagger.Binds;
import dagger.Module;
import codcat.magnitrailstation.di.scopes.ActivityScope;


@Module
public abstract class LaunchScreenModule {

    @Binds
    @ActivityScope
    abstract LaunchScreenMvp.View view(LaunchScreen mainScreen);

    @Binds
    @ActivityScope
    abstract LaunchScreenMvp.Presenter presenter(LaunchScreenPresenter<LaunchScreenMvp.View> mainScreenPresenter);
}
