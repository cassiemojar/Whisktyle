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
import model.Outfit;
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
            JSONObject personObject = jsonObject.getJSONObject("Person");
            if (personObject.has("Name") && !personObject.getString("Name").isEmpty()) {
                closet.setPerson(parsePerson(personObject));
            }
        }

        addShirts(closet, jsonObject);
        addPants(closet, jsonObject);
        addShoes(closet, jsonObject);
        addSavedOutfits(closet, jsonObject);
        return closet;
    }

    // EFFECTS: parses Person from JSON object and returns it
    private Person parsePerson(JSONObject jsonObject) {
        Shirt shirt = null;
        Pants pants = null;
        Shoes shoes = null;

        JSONObject shirtJson = jsonObject.optJSONObject("Shirt");
        shirt = parseShirt(shirtJson);

        JSONObject pantsJson = jsonObject.optJSONObject("Pants");
        pants = parsePants(pantsJson);

        JSONObject shoesJson = jsonObject.optJSONObject("Shoes");
        shoes = parseShoes(shoesJson);

        String name = jsonObject.optString("Name", "");
        String imgPath = jsonObject.optString("Image Path", "");

        return new Person(name, new ImageIcon(imgPath), shirt, pants, shoes);
    }

    // MODIFIES: closet
    // EFFECTS: parses shirts from JSON, adding to closet
    private void addShirts(Closet closet, JSONObject jsonObject) {
        JSONArray shirtsArray = jsonObject.optJSONArray("Saved Shirts");
        if (shirtsArray != null) {
            for (Object shirtObj : shirtsArray) {
                JSONObject shirtJson = (JSONObject) shirtObj;

                closet.addShirt(parseShirt(shirtJson));

            }
        }
    }

    // MODIFIES: closet
    // EFFECTS: parses pants from JSON, adding to closet
    private void addPants(Closet closet, JSONObject jsonObject) {
        JSONArray pantsArray = jsonObject.optJSONArray("Saved Pants");

        if (pantsArray != null) {
            for (Object pantsObj : pantsArray) {
                JSONObject pantsJson = (JSONObject) pantsObj;

                closet.addPants(parsePants(pantsJson));

            }
        }
    }

    // MODIFIES: closet
    // EFFECTS: parses saved shoes from JSON, adding to closet
    private void addShoes(Closet closet, JSONObject jsonObject) {
        JSONArray shoesArray = jsonObject.optJSONArray("Saved Shoes");

    }

    // MODIFIES: closet
    // EFFECTS: parses saved outfits from JSON, adding to closet
    private void addSavedOutfits(Closet closet, JSONObject jsonObject) {
        JSONObject outfitsObject = jsonObject.optJSONObject("Saved Outfits");

        for (String category : outfitsObject.keySet()) {
            JSONArray outfitsArray = outfitsObject.getJSONArray(category);
            for (Object outfit : outfitsArray) {
                closet.addOutfit(category, parseOutfit((JSONObject) outfit));
            }
        }
    }

    // EFFECTS: parses Shirt from JSON object and returns
    private Shirt parseShirt(JSONObject jsonObject) {
        return new Shirt(jsonObject.getString("Name"), new ImageIcon(jsonObject.getString("Image Path")));
    }

    // EFFECTS: parses Pants from JSON object and returns
    private Pants parsePants(JSONObject jsonObject) {
        return new Pants(jsonObject.getString("Name"), new ImageIcon(jsonObject.getString("Image Path")));
    }

    private Shoes parseShoes(JSONObject jsonObject) {
        return new Shoes("");
    }

    // EFFECTS: parses Outfit from JSON object and returns
    private Outfit parseOutfit(JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        Shirt shirt = parseShirt(jsonObject.getJSONObject("Shirt"));
        Pants pants = parsePants(jsonObject.getJSONObject("Pants"));
        Shoes shoes = parseShoes(jsonObject.getJSONObject("Shoes"));
        return new Outfit(name, shirt, pants, shoes);
    }

}
