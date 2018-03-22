package codcat.magnitrailstation.feature.main_screen.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.data.pojo.Station;
import codcat.magnitrailstation.di.scopes.ActivityScope;
import codcat.magnitrailstation.feature.main_screen.MainScreenMvp;
import codcat.magnitrailstation.feature.main_screen.StationAdapter;

@ActivityScope
public class AllStationsFragment extends DaggerFragment implements StationAdapter.OnItemClicked {

    @Inject
    MainScreenMvp.Presenter presenter;
    @Inject StationAdapter stationAdapter;

    @BindView(R.id.rv_all_stations) RecyclerView rvAllStations;
    @BindView(R.id.edit_find_station) EditText editFindStation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_stations_fragment, container, false);
        ButterKnife.bind(this, view);

        setRvAllStations();
        addRxTextWatcher();

        presenter.onAllStationViewInitialized();
        return view;
    }

    @Inject
    public AllStationsFragment() {
        // Requires empty public constructor for @ContributesAndroidInjector
    }

    private void addRxTextWatcher() {
        RxTextView.textChanges(editFindStation)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> presenter.onEditFindStationChange(s.toString()));
    }

    private void setRvAllStations() {
        rvAllStations.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAllStations.setAdapter(stationAdapter);
        stationAdapter.setOnClick(this);
    }

    public void showAllStations(ArrayList<Station> stations) {
        stationAdapter.setStations(stations);
        stationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Station station) {
        presenter.onItemRvClick(station);
    }
}
