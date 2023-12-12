package com.userregistrationsystem.model;

public class GeoLocResponse {     // API server response POSTMAN to me
    String city;
    String uuid;
    String message;
    String country;

    String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


// "status": "success",
//    "country": "Canada",
//    "countryCode": "CA",
//    "region": "QC",
//    "regionName": "Quebec",
//    "city": "Laval",
//    "zip": "H7W",
//    "lat": 45.5355,
//    "lon": -73.761,
//    "timezone": "America/Toronto",
//    "isp": "ViaNetTV Inc",
//    "org": "VMedia Inc",
//    "as": "AS54198 ViaNetTV Inc",
//    "query": "45.44.245.7"
//}
