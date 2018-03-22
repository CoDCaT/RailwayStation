package codcat.magnitrailstation.feature.settings;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferenceModule {

    private Context appContext;

    public PreferenceModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    IPreferences getDefaultInstance(){
        return new AppPreferences(appContext);
    }
}
