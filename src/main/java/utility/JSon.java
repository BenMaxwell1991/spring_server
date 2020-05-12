package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSon {


    public static Object readFromJson(Object obj, String filePath) {
        Gson gson = new Gson();
        Class<?> objClass = obj.getClass();
        Object readObj = new Object();

        try {
            FileReader reader = new FileReader(filePath);
            readObj = gson.fromJson(reader, objClass);
            if (objClass.isInstance(readObj.getClass())) {
                throw new Exception("The class [" + objClass.toString() + "] does not match [" + readObj.getClass().toString() + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readObj;
    }

    // Write group data/parameters to JSON file (For testing only)
    public static void writeToJson(Object obj, String filePath) {

        FileWriter writer;
        try {
            writer = new FileWriter(filePath);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(obj, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
