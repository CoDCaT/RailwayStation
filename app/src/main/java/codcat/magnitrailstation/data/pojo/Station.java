package codcat.magnitrailstation.data.pojo;

import java.io.Serializable;

import io.realm.RealmObject;

public class Station extends RealmObject implements Serializable{

    public int cityId;
    public int stationId;
    public String countryTitle;
    public String districtTitle;
    public String cityTitle;
    public String regionTitle;
    public String stationTitle;
    public Point_ point;

    public Station() {
    }

    public Station(int cityId, int stationId, String countryTitle, String districtTitle, String cityTitle, String regionTitle, String stationTitle, Point_ point) {
        this.cityId = cityId;
        this.stationId = stationId;
        this.countryTitle = countryTitle;
        this.districtTitle = districtTitle;
        this.cityTitle = cityTitle;
        this.regionTitle = regionTitle;
        this.stationTitle = stationTitle;
        this.point = point;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
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

    public String getStationTitle() {
        return stationTitle;
    }

    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }

    public Point_ getPoint() {
        return point;
    }

    public void setPoint(Point_ point) {
        this.point = point;
    }
}
