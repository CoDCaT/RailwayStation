package codcat.magnitrailstation.di;


import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import codcat.magnitrailstation.data.repository.AppRepository;
import codcat.magnitrailstation.di.modules.AppRepositoryModule;
import codcat.magnitrailstation.di.modules.RealmModule;
import codcat.magnitrailstation.feature.settings.IPreferences;
import codcat.magnitrailstation.feature.settings.PreferenceModule;

@Singleton
@Component(modules = {AppRepositoryModule.class, RealmModule.class, PreferenceModule.class})
public interface AppRepositoryComponent {
    AppRepository getRepository();
    Realm getRealm();
    IPreferences getAppReferences();
}
