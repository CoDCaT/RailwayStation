package codcat.magnitrailstation.feature.main_screen.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.di.scopes.ActivityScope;
import codcat.magnitrailstation.feature.main_screen.MainScreenMvp;

@ActivityScope
public class FindTicketFragment extends DaggerFragment implements DatePickerDialog.OnDateSetListener{

    @Inject
    MainScreenMvp.Presenter presenter;
    @Inject ArrayAdapter<String> citiesFromArrayAdapter;
    @Inject ArrayAdapter<String> citiesToArrayAdapter;

    @BindView(R.id.linear_main) LinearLayout linearMain;
    @BindView(R.id.actv_station_from) AutoCompleteTextView autoTvFrom;
    @BindView(R.id.actv_station_to) AutoCompleteTextView autoTvTo;
    @BindView(R.id.tv_travel_date_choice) TextView tvTravelDateChoice;
    @BindView(R.id.btn_find_tickets) Button btnFindTickets;

    private DatePickerDialog datePickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_ticket_fragment, container, false);
        ButterKnife.bind(this, view);

        init();
        createDataPickerDialog(view.getContext(), this);

        presenter.onFindTicketViewInitialized();
        return view;
    }

    private void init() {
        tvTravelDateChoice.setOnClickListener(v -> presenter.onTvTravelDateClick());
        btnFindTickets.setOnClickListener(v -> presenter.onBtnFindTicketClick());
        autoTvFrom.setAdapter(citiesFromArrayAdapter);
        autoTvTo.setAdapter(citiesToArrayAdapter);
    }

    @Inject
    public FindTicketFragment() {
        // Requires empty public constructor for @ContributesAndroidInjector
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        tvTravelDateChoice.setText(String.format(Locale.getDefault(), "%d.%d.%d", dayOfMonth, month + 1, year));
    }

    public void showCitiesFrom(ArrayList<String> citiesFromArrayList) {
        citiesFromArrayAdapter.addAll(citiesFromArrayList);
        citiesFromArrayAdapter.notifyDataSetChanged();
    }

    public void showCitiesTo(ArrayList<String> citiesToArrayList) {
        citiesToArrayAdapter.addAll(citiesToArrayList);
        citiesToArrayAdapter.notifyDataSetChanged();
    }

    public void showDataPickerDialog() {
        datePickerDialog.show();
    }

    private void createDataPickerDialog(Context context, FindTicketFragment findTicketFragment) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(context, findTicketFragment, year, month, day);
    }
}
