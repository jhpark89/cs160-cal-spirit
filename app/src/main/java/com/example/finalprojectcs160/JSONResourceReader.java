/**
 * Code courtesy of @jwir3. Source: https://stackoverflow.com/questions/6349759/using-json-file-in-android-app-resources
 */
package com.example.finalprojectcs160;

import java.io.*;
import java.lang.reflect.Type;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;

/**
 * An object for reading from a JSON resource file and constructing an object from that resource file using Gson.
 */
public class JSONResourceReader {

    // === [ Private Data Members ] ============================================

    // Our JSON, in string form.
    private String jsonString;
    private static final String LOGTAG = JSONResourceReader.class.getSimpleName();

    // === [ Public API ] ======================================================

    /**
     * Read from a resources file and create a {@link JSONResourceReader} object that will allow the creation of other
     * objects from this resource.
     *
     * @param resources An application {@link Resources} object.
     * @param id The id for the resource to load, typically held in the raw/ folder.
     */
    public JSONResourceReader(Resources resources, int id) {
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
            }
        }

        jsonString = writer.toString();
    }

    /**
     * Build an object from the saved JSON string using Gson.
     *
     * @param type The type of the object to build.
     *
     * @return An object of type T.
     */
    public <T> T constructUsingGson(Type type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    public String getJsonString() {
        return jsonString;
    }
}
