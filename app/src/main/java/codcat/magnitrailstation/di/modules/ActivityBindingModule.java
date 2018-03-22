package codcat.magnitrailstation.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import codcat.magnitrailstation.di.scopes.ActivityScope;
import codcat.magnitrailstation.feature.launch_screen.LaunchScreen;
import codcat.magnitrailstation.feature.launch_screen.LaunchScreenModule;
import codcat.magnitrailstation.feature.main_screen.MainScreen;
import codcat.magnitrailstation.feature.main_screen.MainScreenModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LaunchScreenModule.class)
    abstract LaunchScreen bindLaunchScreen();

    @ActivityScope
    @ContributesAndroidInjector(modules = MainScreenModule.class)
    abstract MainScreen bindMainScreenActivity();
}
