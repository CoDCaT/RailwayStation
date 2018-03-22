package codcat.magnitrailstation.data.pojo;

import io.realm.RealmObject;

public class Point_ extends RealmObject {
    public float longitude;
    public float latitude;

    public Point_() {
    }

    public Point_(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}

