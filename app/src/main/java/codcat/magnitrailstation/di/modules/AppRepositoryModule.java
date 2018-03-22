package codcat.magnitrailstation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import codcat.magnitrailstation.data.repository.AppRepository;
import codcat.magnitrailstation.data.repository.IRepository;

@Module
public class AppRepositoryModule {

    @Provides
    @Singleton
    IRepository movieRepository(){
        return new AppRepository();
    }
}
