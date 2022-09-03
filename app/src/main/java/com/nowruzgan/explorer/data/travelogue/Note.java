package com.nowruzgan.explorer.data.travelogue;

public class Note {

    public final long id;
    public final Long fromDate;
    public final Long toDate;
    public final int dateAccuracy; // Error in days
    public final boolean isShort;
    public final boolean isQuote;
    public final boolean isTime;
    public final int order;
    public final long sourceId;
    public final String text;
    public final String description;

    public Note(Long id, String fromDate, String toDate, Integer dateAccuracy, Boolean isShort, Boolean isQuote, Boolean isTime, Integer order, Long sourceId, String text, String description) {
        this.id = id;
        this.fromDate = 0L; // TODO
        this.toDate = 0L; // TODO
        this.dateAccuracy = dateAccuracy;
        this.isShort = isShort;
        this.isQuote = isQuote;
        this.isTime = isTime;
        this.order = order;
        this.sourceId = sourceId;
        this.text = text;
        this.description = description;
    }

}
