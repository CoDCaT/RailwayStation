package codcat.magnitrailstation.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.Realm;
import codcat.magnitrailstation.AppLoader;
import codcat.magnitrailstation.AppUtils;
import codcat.magnitrailstation.Helper;
import codcat.magnitrailstation.LogUtils;
import codcat.magnitrailstation.data.pojo.CitiesFrom;
import codcat.magnitrailstation.data.pojo.CitiesTo;
import codcat.magnitrailstation.data.pojo.JsonPojo;
import codcat.magnitrailstation.data.pojo.Station;

@Singleton
public class AppRepository implements IRepository {

    private Realm realm;

    @Inject
    public AppRepository() {
        realm = AppLoader.repositoryComponent.getRealm();
        LogUtils.E("IS Realm attached to AppRepository: " + (realm != null));
    }

    @Override
    public Realm getRealm() {
        return realm;
    }

    @Override
    public Observable<List<CitiesTo>> parseJsonCitiesTo() {
        return Observable.create(emitter -> {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            InputStream inputStream = AppLoader.appContext.getAssets().open(Helper.ASSETS_PATH_STATIONS_TO);
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            JsonPojo jsonPojo = gson.fromJson(reader, JsonPojo.class);
            int countCities = jsonPojo.citiesTo.size();
            if (countCities == 0) emitter.onError(new JsonIOException("WRONG JSON!"));
            else emitter.onNext(jsonPojo.citiesTo);
            inputStream.close();
            reader.close();
            emitter.onComplete();
        });
    }
    @Override
    public Observable<List<CitiesFrom>> parseJsonCitiesFrom() {
        return Observable.create(emitter -> {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            InputStream inputStream = AppLoader.appContext.getAssets().open(Helper.ASSETS_PATH_STATIONS_FROM);

            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            JsonPojo jsonPojo = gson.fromJson(reader, JsonPojo.class);
            int countCities = jsonPojo.citiesFrom.size();
            if (countCities == 0) emitter.onError(new JsonIOException("WRONG JSON!"));
            else emitter.onNext(jsonPojo.citiesFrom);
            inputStream.close();
            reader.close();
            emitter.onComplete();
        });
    }
    @Override
    public Completable parseJsonToRealm() {
        return Completable.create(emitter ->
                Observable.zip(
                        parseJsonCitiesTo(),
                        parseJsonCitiesFrom(),
                        ((citiesTo, citiesFrom) -> {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            realm.copyToRealmOrUpdate(citiesTo);
                            realm.copyToRealmOrUpdate(citiesFrom);
                            realm.commitTransaction();
                            emitter.onComplete();
                            return true;
                        }))
        .subscribe());
    }
    @Override
    public Single<ArrayList<String>> getAllStationFromNames() {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            ArrayList<CitiesFrom> citiesFromArrayList = new ArrayList<>(realm.where(CitiesFrom.class).findAll());
            ArrayList<List<Station>> stationsFrom = new ArrayList<>();
            ArrayList<String> allStationNameStrings = new ArrayList<>();
            for (CitiesFrom citiesFrom : citiesFromArrayList) stationsFrom.add(citiesFrom.getStations());
            for (List<Station> listStation : stationsFrom) for (Station station : listStation) allStationNameStrings.add(station.getStationTitle());
            emitter.onSuccess(allStationNameStrings);
        });
    }
    @Override
    public Single<ArrayList<String>> getAllStationToNames() {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            ArrayList<CitiesTo> citiesToArrayList = new ArrayList<>(realm.where(CitiesTo.class).findAll());
            ArrayList<List<Station>> stationsTo = new ArrayList<>();
            ArrayList<String> allStationNameStrings = new ArrayList<>();
            for (CitiesTo citiesTo : citiesToArrayList) stationsTo.add(citiesTo.getStations());
            for (List<Station> listStation : stationsTo) for (Station station : listStation) allStationNameStrings.add(station.getStationTitle());
            emitter.onSuccess(allStationNameStrings);
        });
    }

    @Override
    public Single<ArrayList<Station>> getAllStations() {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            ArrayList<CitiesFrom> citiesFromArrayList = new ArrayList<>(realm.where(CitiesFrom.class).findAll());
            ArrayList<CitiesTo> citiesToArrayList = new ArrayList<>(realm.where(CitiesTo.class).findAll());
            ArrayList<Station> allStation = new ArrayList<>();
            for (CitiesFrom citiesFrom : citiesFromArrayList) allStation.addAll(citiesFrom.getStations());
            for (CitiesTo citiesTo : citiesToArrayList) allStation.addAll(citiesTo.getStations());
            emitter.onSuccess(allStation);
        });
    }

    @Override
    public Single<ArrayList<Station>> getFilteredStations(String s) {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            ArrayList<CitiesFrom> citiesFromArrayList = new ArrayList<>(realm.where(CitiesFrom.class).findAll());
            ArrayList<CitiesTo> citiesToArrayList = new ArrayList<>(realm.where(CitiesTo.class).findAll());
            ArrayList<Station> allStation = new ArrayList<>();
            ArrayList<Station> filteredStations = new ArrayList<>();
            for (CitiesFrom citiesFrom : citiesFromArrayList) allStation.addAll(citiesFrom.getStations());
            for (CitiesTo citiesTo : citiesToArrayList) allStation.addAll(citiesTo.getStations());
            for (Station station : allStation) if (AppUtils.containsIgnoreCase(station.getStationTitle(), s) ||
                    AppUtils.containsIgnoreCase(station.getStationTitle(), s))
                    filteredStations.add(station);
            emitter.onSuccess(filteredStations);
        });
    }
}
