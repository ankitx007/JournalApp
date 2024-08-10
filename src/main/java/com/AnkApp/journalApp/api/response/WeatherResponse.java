package com.AnkApp.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WeatherResponse {
    private int count;
    private List<Datum> data;
    private List<Minutely> minutely;

    @Data
    public static class Datum {
        @JsonProperty("app_temp")
        private int appTemp;

        @JsonProperty("aqi")
        private int aqi;

        @JsonProperty("city_name")
        private String cityName;

        @JsonProperty("clouds")
        private int clouds;

        @JsonProperty("country_code")
        private String countryCode;

        @JsonProperty("datetime")
        private String datetime;

        @JsonProperty("dewpt")
        private double dewpt;

        @JsonProperty("dhi")
        private int dhi;

        @JsonProperty("dni")
        private int dni;

        @JsonProperty("elev_angle")
        private double elevAngle;

        @JsonProperty("ghi")
        private int ghi;

        @JsonProperty("gust")
        private Object gust;

        @JsonProperty("h_angle")
        private int hAngle;

        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lon")
        private double lon;

        @JsonProperty("ob_time")
        private String obTime;

        @JsonProperty("pod")
        private String pod;

        @JsonProperty("precip")
        private int precip;

        @JsonProperty("pres")
        private double pres;

        @JsonProperty("rh")
        private int rh;

        @JsonProperty("slp")
        private double slp;

        @JsonProperty("snow")
        private int snow;

        @JsonProperty("solar_rad")
        private double solarRad;

        @JsonProperty("sources")
        private List<String> sources;

        @JsonProperty("state_code")
        private String stateCode;

        @JsonProperty("station")
        private String station;

        @JsonProperty("sunrise")
        private String sunrise;

        @JsonProperty("sunset")
        private String sunset;

        @JsonProperty("temp")
        private double temp;

        @JsonProperty("timezone")
        private String timezone;

        @JsonProperty("ts")
        private int ts;

        @JsonProperty("uv")
        private int uv;

        @JsonProperty("vis")
        private int vis;

        @JsonProperty("weather")
        private Weather weather;

        @JsonProperty("wind_cdir")
        private String windCdir;

        @JsonProperty("wind_cdir_full")
        private String windCdirFull;

        @JsonProperty("wind_dir")
        private int windDir;

        @JsonProperty("wind_spd")
        private int windSpd;
    }

    @Data
    public static class Minutely {
        @JsonProperty("precip")
        private int precip;

        @JsonProperty("snow")
        private int snow;

        @JsonProperty("temp")
        private double temp;

        @JsonProperty("ts")
        private int ts;

        @JsonProperty("timestamp_local")
        private Date timestampLocal;

        @JsonProperty("timestamp_utc")
        private Date timestampUtc;
    }

    @Data
    public static class Weather {
        @JsonProperty("code")
        private int code;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("description")
        private String description;
    }
}
