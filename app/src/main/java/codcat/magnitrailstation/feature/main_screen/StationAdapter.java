package codcat.magnitrailstation.feature.main_screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import codcat.magnitrailstation.R;
import codcat.magnitrailstation.data.pojo.Station;
import codcat.magnitrailstation.di.scopes.ActivityScope;

@ActivityScope
public class StationAdapter extends RecyclerView.Adapter<StationAdapter.VH>{

    private ArrayList<Station> stationsFromArrayList = new ArrayList<>();
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(Station station);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_and_station_item, parent, false);
        return new VH(rootView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind();
        holder.itemView.setOnClickListener(v -> onClick.onItemClick(stationsFromArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
       return stationsFromArrayList.size();
    }

    public void setStations(ArrayList<Station> stationsFromArrayList) {
        this.stationsFromArrayList = stationsFromArrayList;
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_city) TextView tvCity;
        @BindView(R.id.tv_station) TextView tvStation;

        VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind() {
            Station station = stationsFromArrayList.get(getAdapterPosition());
            tvCity.setText(station.getCityTitle());
            tvStation.setText(station.getStationTitle());
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick=onClick;
    }
}
