package com.userregistrationsystem.model;

public class GeoLocResponse {     // API server response POSTMAN to me
    String city;
    String country;
    String query;
    String status;
    String countryCode;
    String region;
    String regionName;
    String zip;
    String lat;
    String lon;
    String timezone;
    String isp;
    String org;
    String as;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

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
