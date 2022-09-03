package com.nowruzgan.explorer.data.travelogue;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Travelogue {

    private static final String TAG = "Travelogue";

    public interface Callback {

        void onTravelogueReady(Travelogue travelogue);

    }

    public static void get(final Context context, final Callback callback) {
        new Thread() {

            @Override
            public void run() {
                Travelogue data = null;

                try {
                    InputStream input = context.getAssets().open("data.json");
                    ByteArrayOutputStream output = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int size = 0;

                    while (size != -1) {
                        size = input.read(buffer);

                        if (size > 0) {
                            output.write(buffer, 0, size);
                        }
                    }

                    input.close();

                    String sData = new String(output.toByteArray(), StandardCharsets.UTF_8);
                    JSONObject jData = new JSONObject(sData);
                    data = new Travelogue(jData);
                } catch (IOException | JSONException e) {
                    Log.e(TAG, "Failed to read the data", e);
                }

                final Travelogue result = data;

                new Handler(Looper.getMainLooper()).post(() -> callback.onTravelogueReady(result));
            }

        }.start();
    }

    private final Map<Long, Source> mSources = new HashMap<>(10);
    private final Map<Long, Note> mNotes = new HashMap<>(100);
    private final Map<Long, Location> mLocations = new HashMap<>(100);
    private final Map<Long, NoteLocation> mNoteToLocationMap = new HashMap<>(100);
    private final Map<Long, NoteLocation> mLocationToNoteMap = new HashMap<>(100);
    private final Map<Long, Tag> mTags = new HashMap<>(100);
    private final Map<String, List<Tag>> mTaxonomies = new HashMap<>(10);
    private final Map<Long, List<Long>> mTagToNotesMap = new HashMap<>(100);
    private final Map<Long, List<Long>> mNoteToTagsMap = new HashMap<>(100);

    private Travelogue(JSONObject jData) throws JSONException {
        readFields(jData.getJSONArray("sources"), mSources, Source.class, model -> model.id);
        readFields(jData.getJSONArray("notes"), mNotes, Note.class, model -> model.id);
        readFields(jData.getJSONArray("pois"), mLocations, Location.class, model -> model.id);
        readFields(jData.getJSONArray("notePois"), NoteLocation.class, model -> {
            mNoteToLocationMap.put(model.noteId, model);
            mLocationToNoteMap.put(model.locationId, model);
        });
        readFields(jData.getJSONArray("tags"), Tag.class, model -> {
            mTags.put(model.id, model);

            List<Tag> tags = mTaxonomies.get(model.taxonomy);

            if (tags == null) {
                tags = new ArrayList<>(5);
                mTaxonomies.put(model.taxonomy, tags);
            }

            tags.add(model);
        });
        readFields(jData.getJSONArray("noteTags"), NoteTag.class, model -> {
            List<Long> ids = mTagToNotesMap.get(model.tagId);

            if (ids == null) {
                ids = new ArrayList<>();
                mTagToNotesMap.put(model.tagId, ids);
            }

            ids.add(model.noteId);

            ids = mNoteToTagsMap.get(model.noteId);

            if (ids == null) {
                ids = new ArrayList<>();
                mNoteToTagsMap.put(model.noteId, ids);
            }

            ids.add(model.tagId);
        });
    }

    public Collection<Location> getAllLocations() {
        return mLocations.values();
    }

    private static<T> void readFields(JSONArray array, Map<Long, T> map, Class<T> clazz, IdProvider<T> idProvider) throws JSONException {
        readFields(array, clazz, model -> map.put(idProvider.getId(model), model));
    }

    private static<T> void readFields(JSONArray array, Class<T> clazz, ModelHandler<T> modelHandler) throws JSONException {
        DataUtil.ModelReader<T> reader = DataUtil.getReader(clazz);

        for (int i = 1; i < array.length(); i++) {
            T model = reader.read(array.getJSONArray(i));
            modelHandler.handleModel(model);
        }
    }

    private interface IdProvider<T> {

        long getId(T model);

    }

    private interface ModelHandler<T> {

        void handleModel(T model);

    }

}
