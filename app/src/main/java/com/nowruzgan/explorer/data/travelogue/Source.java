package com.nowruzgan.explorer.data.travelogue;

public class Source {

    public final long id;
    public final String title;
    public final String author;
    public final String authorDescription;
    public final String citation;
    public final String defaultCalendar;
    public final int era;
    public final String sourceDescription;

    public Source(Long id, String title, String author, String authorDescription, String citation, String defaultCalendar, Integer era, String sourceDescription) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.authorDescription = authorDescription;
        this.citation = citation;
        this.defaultCalendar = defaultCalendar;
        this.era = era;
        this.sourceDescription = sourceDescription;
    }

}
