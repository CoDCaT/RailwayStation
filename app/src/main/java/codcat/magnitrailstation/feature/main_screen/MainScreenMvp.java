package codcat.magnitrailstation.feature.main_screen;

import java.util.ArrayList;

import codcat.magnitrailstation.base.MvpPresenter;
import codcat.magnitrailstation.base.MvpView;
import codcat.magnitrailstation.data.pojo.Station;

public interface MainScreenMvp {

    interface View extends MvpView {
        void hideKeyboard();
        void showFirstFragment();

        void showCitiesFrom(ArrayList<String> strings);
        void showCitiesTo(ArrayList<String> strings);
        void showDataPickerDialog();

        void closeDrawer();
        void showFindTicketView();
        void showAllStationsView();
        void showAboutView();

        void showAllStations(ArrayList<Station> stations);

        void showDetailStationView(Station station);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void onViewInitialized();
        void onFindTicketViewInitialized();
        void onTvTravelDateClick();
        void onBtnFindTicketClick();


        void onNavMenuFindTicketClicked();
        void onNavMenuAllStationClicked();
        void onNavAboutClicked();
        void onAllStationViewInitialized();

        void onEditFindStationChange(String s);

        void onItemRvClick(Station station);
    }
}
