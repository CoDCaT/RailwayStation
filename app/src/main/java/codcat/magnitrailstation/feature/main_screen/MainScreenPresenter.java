package codcat.magnitrailstation.feature.main_screen;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import codcat.magnitrailstation.AppLoader;
import codcat.magnitrailstation.LogUtils;
import codcat.magnitrailstation.base.BasePresenter;
import codcat.magnitrailstation.data.pojo.Station;
import codcat.magnitrailstation.data.repository.IRepository;


public class MainScreenPresenter<V extends MainScreenMvp.View> extends BasePresenter<V> implements MainScreenMvp.Presenter<V> {

    private IRepository repository;

    @Inject
    MainScreenPresenter(V mMvpView) {
        super(mMvpView);
        repository = AppLoader.repositoryComponent.getRepository();
        LogUtils.E("Repository attached: " + (repository != null));
    }

    @Override
    public void onViewInitialized() {
        getMvpView().showFirstFragment();
    }

    @Override
    public void onFindTicketViewInitialized() {
        repository.getAllStationFromNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> getMvpView().showCitiesFrom(strings));

        repository.getAllStationToNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings -> getMvpView().showCitiesTo(strings));
    }

    @Override
    public void onTvTravelDateClick() {
        getMvpView().showDataPickerDialog();
    }

    @Override
    public void onBtnFindTicketClick() {
        getMvpView().hideKeyboard();
    }

    @Override
    public void onNavMenuFindTicketClicked() {
        getMvpView().closeDrawer();
        getMvpView().showFindTicketView();
    }

    @Override
    public void onItemRvClick(Station station) {
        getMvpView().showDetailStationView(station);
    }

    @Override
    public void onNavMenuAllStationClicked() {
        getMvpView().closeDrawer();
        getMvpView().showAllStationsView();
    }

    @Override
    public void onNavAboutClicked() {
        getMvpView().closeDrawer();
        getMvpView().showAboutView();
    }

    @Override
    public void onAllStationViewInitialized() {
        repository.getAllStations()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stations -> getMvpView().showAllStations(stations));
    }

    @Override
    public void onEditFindStationChange(String s) {
        repository.getFilteredStations(s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stations -> getMvpView().showAllStations(stations));
    }
}
