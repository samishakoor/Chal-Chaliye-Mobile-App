package com.example.smd_project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Utility class to work with JSON content stored locally.
 */
public class Json {

    /**
     * Loads a resource and creates a {@link JSONArray} object with the contents of the binary.
     *
     * @param context  where the execution is taking place.
     * @param fileName path that points to the target binary.
     * @return a {@link JSONArray} object with the contents of the stream.
     */
    public static JSONArray readFromFile(Context context, String fileName) {
        try {
            final InputStream inputStream = context.getAssets().open(fileName);
            return readFromInputStream(inputStream);

        } catch (Exception e) {
            return new JSONArray();
        }
    }

    /**
     * Loads a resource and creates a {@link JSONArray} object with the contents of the binary.
     *
     * @param context  where the execution is taking place.
     * @param resource identifier of the binary in the resource folders.
     * @return a {@link JSONArray} object with the contents of the stream.
     */
    public static JSONArray readFromResources(Context context, int resource) {
        try {
            final InputStream inputStream = context.getResources().openRawResource(resource);
            return readFromInputStream(inputStream);

        } catch (Exception e) {
            return new JSONArray();
        }
    }

    /**
     * Create a {@link JSONArray} with the contents of an {@link InputStream} holding
     * JSON information.
     *
     * @param inputStream containing the JSON bytes.
     * @return a {@link JSONArray} object with the contents of the stream.
     * @throws JSONException if the content could not be parsed.
     */
    private static JSONArray readFromInputStream(InputStream inputStream) throws JSONException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final String inputString = reader.lines().collect(Collectors.joining());
        return new JSONArray(inputString);
    }
}
