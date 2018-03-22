package codcat.magnitrailstation.data.pojo;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CitiesFrom extends RealmObject {

    @PrimaryKey
    public int cityId;
    public String cityTitle;
    public String countryTitle;
    public String districtTitle;
    public String regionTitle;

    public Point point;
    public RealmList<Station> stations = new RealmList<>();

    public CitiesFrom() {
    }

    public CitiesFrom(int cityId, String countryTitle, Point point, String districtTitle, String cityTitle, String regionTitle, RealmList<Station> stations) {
        this.cityId = cityId;
        this.countryTitle = countryTitle;
        this.point = point;
        this.districtTitle = districtTitle;
        this.cityTitle = cityTitle;
        this.regionTitle = regionTitle;
        this.stations = stations;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public void setDistrictTitle(String districtTitle) {
        this.districtTitle = districtTitle;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getRegionTitle() {
        return regionTitle;
    }

    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }

    public RealmList<Station> getStations() {
        return stations;
    }

    public void setStations(RealmList<Station> stations) {
        this.stations = stations;
    }
}
