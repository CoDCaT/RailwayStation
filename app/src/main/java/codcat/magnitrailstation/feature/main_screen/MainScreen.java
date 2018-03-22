package codcat.magnitrailstation.feature.main_screen;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import codcat.magnitrailstation.AppUtils;
import codcat.magnitrailstation.Helper;
import codcat.magnitrailstation.LogUtils;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.data.pojo.Station;
import codcat.magnitrailstation.feature.main_screen.fragments.AboutFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.AllStationsFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.DetailStationFragment;
import codcat.magnitrailstation.feature.main_screen.fragments.FindTicketFragment;

public class MainScreen extends DaggerAppCompatActivity implements MainScreenMvp.View {

    @Inject MainScreenMvp.Presenter presenter;
    @Inject FragmentManager fragmentManager;
    @Inject Lazy<FindTicketFragment> lazyFindTicketFragment;
    @Inject Lazy<AllStationsFragment> lazyAllStationsFragment;
    @Inject Lazy<AboutFragment> lazyAboutFragment;
    @Inject Lazy<DetailStationFragment> lazyDetailFragment;

    @BindView(R.id.drawer_layout) FlowingDrawer flowingDrawer;
    @BindView(R.id.fragment_frame) FrameLayout frameFragment;
    @BindView(R.id.tv_nav_find_ticket) TextView tvFindTickets;
    @BindView(R.id.tv_nav_all_stations) TextView tvNavAllStations;
    @BindView(R.id.tv_nav_about) TextView tvNavAbout;


    private FindTicketFragment findTicketFragment;
    private AllStationsFragment allStationsFragment;
    private AboutFragment aboutFragment;
    private DetailStationFragment detailFragment;

    private void init() {
        ButterKnife.bind(this);
        
        setFlowDrawer();
        tvFindTickets.setOnClickListener(v -> presenter.onNavMenuFindTicketClicked());
        tvNavAllStations.setOnClickListener(v -> presenter.onNavMenuAllStationClicked());
        tvNavAbout.setOnClickListener(v -> presenter.onNavAboutClicked());

        presenter.onViewInitialized();
    }

    private void setFlowDrawer() {
        flowingDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        flowingDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    LogUtils.E("Drawer STATE_CLOSED");
                }
            }
            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                frameFragment.setTranslationX(96 * openRatio);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        init();
    }


    @Override
    public void onBackPressed() {
        if (flowingDrawer.isMenuVisible()) flowingDrawer.closeMenu(true);
        else super.onBackPressed();
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {
        AppUtils.hideKeyboard(this);
    }

    @Override
    public void showFirstFragment() {
        if (findTicketFragment == null) findTicketFragment = lazyFindTicketFragment.get();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, findTicketFragment, Helper.TICKETS_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void showCitiesFrom(ArrayList<String> strings) {
        if (findTicketFragment != null) findTicketFragment.showCitiesFrom(strings);
    }

    @Override
    public void showCitiesTo(ArrayList<String> strings) {
        if (findTicketFragment != null) findTicketFragment.showCitiesTo(strings);
    }

    @Override
    public void showDataPickerDialog() {
        hideKeyboard();
        findTicketFragment.showDataPickerDialog();
    }

    @Override
    public void closeDrawer() {
        flowingDrawer.closeMenu(true);
    }

    @Override
    public void showFindTicketView() {
        if (findTicketFragment == null) findTicketFragment = lazyFindTicketFragment.get();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, findTicketFragment, Helper.TICKETS_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showAllStationsView() {
        if (allStationsFragment == null) allStationsFragment = lazyAllStationsFragment.get();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, allStationsFragment, Helper.All_STATION_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void showAllStations(ArrayList<Station> stations) {
        if (findTicketFragment != null) allStationsFragment.showAllStations(stations);
    }

    @Override
    public void showDetailStationView(Station station) {
        if (detailFragment == null) detailFragment = lazyDetailFragment.get();

        Bundle args = new Bundle();
        args.putSerializable(Helper.DETAIL_FRAGMENT_TAG, station);
        detailFragment.setArguments(args);

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, detailFragment, Helper.DETAIL_FRAGMENT_TAG)
                .addToBackStack(Helper.DETAIL_FRAGMENT_TAG)
                .commit();

    }

    @Override
    public void showAboutView() {
        if (aboutFragment == null) aboutFragment = lazyAboutFragment.get();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_frame, aboutFragment, Helper.ABOUT_FRAGMENT_TAG)
                .addToBackStack(Helper.ABOUT_FRAGMENT_TAG)
                .commit();
    }

}
