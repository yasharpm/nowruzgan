package com.nowruzgan.explorer.data.travelogue;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.nowruzgan.explorer.R;
import com.nowruzgan.explorer.Util;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

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

    public void apply(Marker marker, Context context) {
        int resourceId = 0;
        float u = 0, v = 0;

        switch (granularity) {
            case GRANULARITY_COUNTRY:
                resourceId = R.drawable.pin_country;
                u = 0;
                v = 1;
                break;
            case GRANULARITY_STATE:
                resourceId = R.drawable.pin_state;
                u = 0;
                v = 1;
                break;
            case GRANULARITY_ROAD:
                resourceId = R.drawable.pin_road;
                u = 0.5f;
                v = 0.5f;
                break;
            case GRANULARITY_CITY:
                resourceId = R.drawable.pin_city;
                u = 0.5f;
                v = 0.5f;
                break;
            case GRANULARITY_DISTRICT:
                resourceId = R.drawable.pin_district;
                u = 0.5f;
                v = 0.5f;
                break;
            case GRANULARITY_BUILDING:
                resourceId = R.drawable.pin_building;
                u = 0.5f;
                v = 0.5f;
                break;
            case GRANULARITY_GEO_STRUCTURE:
                resourceId = R.drawable.pin_geo_structure;
                u = 0.5f;
                v = 0.5f;
                break;
        }

        Drawable icon = AppCompatResources.getDrawable(context, resourceId);

        if (icon != null) {
            int color = context.getResources().getColor(isRuined ? R.color.location_ruined : R.color.location_default);
            icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);

            icon = Util.sizedDrawable(icon, (int) (20 * context.getResources().getDisplayMetrics().density));
        }

        marker.setIcon(icon);
        marker.setAnchor(u, v);

        if (isAmbiguous) {
            marker.setAlpha(0.5f);
        }

        marker.setPosition(new GeoPoint(latitude, longitude));
    }

}
