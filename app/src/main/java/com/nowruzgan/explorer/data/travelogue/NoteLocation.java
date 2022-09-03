package com.nowruzgan.explorer.data.travelogue;

public class NoteLocation {

    public final long noteId;
    public final long locationId;
    public final int order;
    public final boolean isMilestone; // Meaning this is one of the places that the writer has just passed by
    public final boolean isNoway; // Meaning the writer didn't actually go there

    public NoteLocation(Long noteId, Long locationId, Integer order, Boolean isMilestone, Boolean isNoway) {
        this.noteId = noteId;
        this.locationId = locationId;
        this.order = order;
        this.isMilestone = isMilestone;
        this.isNoway = isNoway;
    }

}
