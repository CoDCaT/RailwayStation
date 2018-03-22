package codcat.magnitrailstation.feature.launch_screen;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import codcat.magnitrailstation.AppLoader;
import codcat.magnitrailstation.LogUtils;
import codcat.magnitrailstation.base.BasePresenter;
import codcat.magnitrailstation.data.pojo.CitiesFrom;
import codcat.magnitrailstation.data.pojo.CitiesTo;
import codcat.magnitrailstation.data.repository.IRepository;
import codcat.magnitrailstation.feature.settings.IPreferences;


public class LaunchScreenPresenter<V extends LaunchScreenMvp.View> extends BasePresenter<V> implements LaunchScreenMvp.Presenter<V> {

    private IRepository repository;
    private IPreferences preferences;

    @Inject
    LaunchScreenPresenter(V mMvpView) {
        super(mMvpView);
        repository = AppLoader.repositoryComponent.getRepository();
        preferences = AppLoader.repositoryComponent.getAppReferences();
        LogUtils.E("IRepository attached: " + (repository != null));
    }

    @Override
    public void onViewInitialized() {
        getMvpView().startLaunchAnimation();
        int citiesTo = repository.getRealm().where(CitiesTo.class).findAll().size();
        int citiesFrom = repository.getRealm().where(CitiesFrom.class).findAll().size();
        LogUtils.D("Realm is contain: " + citiesTo + citiesFrom + " cities");
    }

    @Override
    public void onLaunchAnimEnd() {
        boolean isJsonParsed = preferences.getIsJsonParsed();

        if (isJsonParsed) getMvpView().navigateToMainScreen();
        else {
            repository
                    .parseJsonToRealm()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getMvpView().showLaunchLoading();
                        }

                        @Override
                        public void onComplete() {
                            getMvpView().stopLaunchLoading();
                            getMvpView().navigateToMainScreen();
                            preferences.setIsJsonParsed(true);
                            LogUtils.D("parseJsonToRealm Complete");
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.E(e.getMessage());
                        }
                    });
        }
    }
}
