package codcat.magnitrailstation.data.pojo;

import java.util.List;

public class JsonPojo {

    public List<CitiesTo> citiesTo = null;
    public List<CitiesFrom> citiesFrom = null;

    public JsonPojo(List<CitiesTo> citiesTo, List<CitiesFrom> citiesFroms) {
        this.citiesTo = citiesTo;
        this.citiesFrom = citiesFroms;
    }

    public List<CitiesTo> getCitiesTo() {
        return citiesTo;
    }

    public void setCitiesTo(List<CitiesTo> citiesTo) {
        this.citiesTo = citiesTo;
    }

    public List<CitiesFrom> getCitiesFrom() {
        return citiesFrom;
    }

    public void setCitiesFrom(List<CitiesFrom> citiesFrom) {
        this.citiesFrom = citiesFrom;
    }
}