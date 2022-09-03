package com.nowruzgan.explorer.data.travelogue;

public class Location {

    public static final int GRANULARITY_COUNTRY = 10;
    public static final int GRANULARITY_STATE = 20;
    public static final int GRANULARITY_ROAD = 30;
    public static final int GRANULARITY_CITY = 40;
    public static final int GRANULARITY_DISTRICT = 50;
    public static final int GRANULARITY_BUILDING = 60;
    public static final int GRANULARITY_GEO_STRUCTURE = 70;

    public final String[] alternativeNames;
    public final long id;
    public final String title;
    public final String latinTitle;
    public final double latitude;
    public final double longitude;
    public final int granularity;
    public final boolean isAmbiguous;
    public final boolean isRuined;
    public final String description;

    public Location(String[] alternativeNames, Long id, String title, String latinTitle, Double latitude, Double longitude, Integer granularity, Boolean isAmbiguous, Boolean isRuined, String description) {
        this.alternativeNames = alternativeNames;
        this.id = id;
        this.title = title;
        this.latinTitle = latinTitle;
        this.latitude = latitude;
        this.longitude = longitude;
        this.granularity = granularity;
        this.isAmbiguous = isAmbiguous;
        this.isRuined = isRuined;
        this.description = description;
    }

}
