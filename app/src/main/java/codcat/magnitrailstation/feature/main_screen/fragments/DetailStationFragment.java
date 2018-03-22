package codcat.magnitrailstation.feature.main_screen.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import codcat.magnitrailstation.Helper;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.data.pojo.Station;
import codcat.magnitrailstation.di.scopes.ActivityScope;

@ActivityScope
public class DetailStationFragment extends Fragment {

    @BindView(R.id.tv_Country_Title) TextView tvCountryTitle;
    @BindView(R.id.tv_District_Title) TextView tvDistrictTitle;
    @BindView(R.id.tv_City_Title) TextView tvCityTitle;
    @BindView(R.id.tv_Region_Title) TextView tvRegionTitle;
    @BindView(R.id.tv_Station_Title) TextView tvStationTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_station, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            Station station = (Station) args
                    .getSerializable(Helper.DETAIL_FRAGMENT_TAG);

            tvCountryTitle.setText(String.format("%s %s", getString(R.string.country), station.countryTitle));
            tvDistrictTitle.setText(String.format("%s %s", getString(R.string.district), station.districtTitle));
            tvCityTitle.setText(String.format("%s %s", getString(R.string.city), station.cityTitle));
            tvRegionTitle.setText(String.format("%s %s", getString(R.string.region), station.regionTitle));
            tvStationTitle.setText(String.format("%s %s", getString(R.string.station), station.stationTitle));
        }
        return view;
    }

    @Inject
    public DetailStationFragment() {
        // Requires empty public constructor for @ContributesAndroidInjector
    }

}
