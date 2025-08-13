package persistence;

import java.io.PrintWriter;

public class JsonWriter {
    //private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }
}
