package codcat.magnitrailstation;

import android.content.Context;


import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import codcat.magnitrailstation.di.AppRepositoryComponent;
import codcat.magnitrailstation.di.DaggerAppComponent;
import codcat.magnitrailstation.di.DaggerAppRepositoryComponent;
import codcat.magnitrailstation.di.modules.RealmModule;
import codcat.magnitrailstation.feature.settings.PreferenceModule;


public class AppLoader extends DaggerApplication {

    public static volatile AppRepositoryComponent repositoryComponent;
    public static volatile Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        buildDaggerComponents();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }

    private void buildDaggerComponents() {
        repositoryComponent = DaggerAppRepositoryComponent.builder()
                .realmModule(new RealmModule(this))
                .preferenceModule(new PreferenceModule(this))
                .build();
    }
}
