package com.nowruzgan.explorer.data.travelogue;

public class Tag {

    public static final String TAXONOMY_PLACE = "travelogue-note-index-place";
    public static final String TAXONOMY_TAG = "travelogue-note-tag";
    public static final String TAXONOMY_PERSON = "travelogue-note-index-person";
    public static final String TAXONOMY_OTHER = "travelogue-note-index-other";

    public final long id;
    public final String title;
    public final String taxonomy;

    public Tag(Long id, String title, String taxonomy) {
        this.id = id;
        this.title = title;
        this.taxonomy = taxonomy;
    }

}
