package codcat.magnitrailstation.feature.main_screen;

import android.support.v4.app.FragmentManager;
import android.widget.ArrayAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.di.scopes.ActivityScope;
import codcat.magnitrailstation.di.scopes.FragmentScope;
import codcat.magnitrailstation.feature.main_screen.fragments.AboutFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.AllStationsFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.DetailStationFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.FindTicketFragment;

@Module
public abstract class MainScreenModule {

    @Binds
    @ActivityScope
    abstract MainScreenMvp.View view(MainScreen mainScreen);

    @Binds
    @ActivityScope
    abstract MainScreenMvp.Presenter presenter(MainScreenPresenter<MainScreenMvp.View> mainScreenPresenter);

    @FragmentScope
    @ContributesAndroidInjector
    abstract FindTicketFragment findTicketFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract AllStationsFragment allStationsFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract AboutFragment aboutFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract DetailStationFragment detailStationFragment();


    @Provides
    @ActivityScope
    static FragmentManager provideFragmentManager(MainScreen mainScreen) {
        return mainScreen.getSupportFragmentManager();
    }

    @Provides
    static StationAdapter stationAdapter(){
        return new StationAdapter();
    }

    @Provides
    static ArrayAdapter<String> citiesFromArrayAdapter(MainScreen mainScreen){
        return new ArrayAdapter<>(mainScreen, R.layout.station_item, R.id.tv_city);
    }
}
