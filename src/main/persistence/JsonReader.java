package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Closet;
import model.Pants;
import model.Person;
import model.Shirt;
import model.Shoes;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Closet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCloset(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Closet from JSON object and returns it
    private Closet parseCloset(JSONObject jsonObject) {
        Closet closet = new Closet();

        if (jsonObject.has("Person")) {
            JSONObject personJson = jsonObject.getJSONObject("Person");
            Shirt defaultShirt = addShirt(personJson.getJSONObject("Shirt"));
            Pants defaultPants = addPants(personJson.getJSONObject("Pants"));
            Shoes defaultShoes = parseShoes(personJson.getJSONObject("Shoes"));

            ImageIcon personImg = new ImageIcon(personJson.getString("Image Path"));
            Person person = new Person(
                    personJson.getString("Name"),
                    personImg,
                    defaultShirt,
                    defaultPants,
                    defaultShoes);
            closet.setPerson(person);
        }

        addClothes(closet, jsonObject);
        return closet;
    }

    private void addShirt() {

    }

    private void addShirts() {

    }

    private void addPants() {

    }

    private void addPantsCollection() {

    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addClothes(Closet closet, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(wr, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(WorkRoom wr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Category category = Category.valueOf(jsonObject.getString("category"));
        Thingy thingy = new Thingy(name, category);
        wr.addThingy(thingy);
    }
}
