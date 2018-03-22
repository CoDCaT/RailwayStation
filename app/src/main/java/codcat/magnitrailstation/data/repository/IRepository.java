package codcat.magnitrailstation.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.Realm;
import codcat.magnitrailstation.data.pojo.CitiesFrom;
import codcat.magnitrailstation.data.pojo.CitiesTo;
import codcat.magnitrailstation.data.pojo.Station;

public interface IRepository {
    Realm getRealm();

    Observable<List<CitiesTo>> parseJsonCitiesTo();
    Observable<List<CitiesFrom>> parseJsonCitiesFrom();
    Completable parseJsonToRealm();

    Single<ArrayList<String>> getAllStationFromNames();
    Single<ArrayList<String>> getAllStationToNames();
    Single<ArrayList<Station>> getAllStations();
    Single<ArrayList<Station>> getFilteredStations(String s);
}
