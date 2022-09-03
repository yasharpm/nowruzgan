package com.nowruzgan.explorer.data.travelogue;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DataUtil {

    public interface ModelReader<T> {

        T read(JSONArray jModel) throws JSONException;

    }

    public static<T> ModelReader<T> getReader(Class<T> clazz) {
        final Constructor<?> constructor = clazz.getConstructors()[0];

        final Class<?>[] parameterTypes = constructor.getParameterTypes();

        final TypeReader<?>[] readers = new TypeReader[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].equals(Long.class)) {
                readers[i] = LONG_READER;
            }
            else if (parameterTypes[i].equals(Integer.class)) {
                readers[i] = INT_READER;
            }
            else if (parameterTypes[i].equals(Double.class)) {
                readers[i] = DOUBLE_READER;
            }
            else if (parameterTypes[i].equals(String.class)) {
                readers[i] = STRING_READER;
            }
            else if (parameterTypes[i].equals(Boolean.class)) {
                readers[i] = BOOLEAN_READER;
            }
            else if (parameterTypes[i].isArray()) {
                readers[i] = STRING_ARRAY_READER;
            }
            else {
                throw new RuntimeException("Unacceptable input type " + parameterTypes[i].getName());
            }

        }

        return jModel -> {
            final Object[] values = new Object[readers.length];

            for (int i = 0; i < values.length; i++) {
                values[i] = readers[i].read(jModel, i);
            }

            try {
                return (T) constructor.newInstance(values);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException("Failed to create a new instance of " + clazz.getName(), e);
            }
        };
    }

    private static final TypeReader<Long> LONG_READER = JSONArray::getLong;
    private static final TypeReader<Integer> INT_READER = JSONArray::getInt;
    private static final TypeReader<Double> DOUBLE_READER = JSONArray::getDouble;
    private static final TypeReader<String> STRING_READER = JSONArray::getString;
    private static final TypeReader<Boolean> BOOLEAN_READER = JSONArray::getBoolean;
    private static final TypeReader<String[]> STRING_ARRAY_READER = (json, index) -> {
        JSONArray jArr = json.getJSONArray(index);
        String[] array = new String[jArr.length()];

        for (int i = 0; i < jArr.length(); i++) {
            array[i] = jArr.getString(i);
        }

        return array;
    };

    private interface TypeReader<T> {

        T read(JSONArray json, int index) throws JSONException;

    }

}
