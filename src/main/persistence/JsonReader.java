package persistence;

import model.Expense;
import model.ListOfExpense;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfExpense read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ListOfExpense parseWorkRoom(JSONObject jsonObject) {
        // searches for key: "name" in workroom.json
        String name = jsonObject.getString("name");

        // creates a new listOfExpense
        ListOfExpense listOfExpense = new ListOfExpense(name);

        // adds expenses in workroom.json to listOfExpense
        addExpenses(listOfExpense, jsonObject);

        return listOfExpense;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addExpenses(ListOfExpense listOfExpense, JSONObject jsonObject) {
        // searches for key: "expenses" in workroom.json
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");

        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExpense(listOfExpense, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addExpense(ListOfExpense listOfExpense, JSONObject jsonObject) {
        // creates a new Expense from JSON object

        // searches => key: "name"
        String name = jsonObject.getString("name");
        // searches => key: "amount"
        Double amount = jsonObject.getDouble("amount");
        // searches => key: "day"
        int day = jsonObject.getInt("day");
        // searches => key: "month"
        int month = jsonObject.getInt("month");
        // searches => key: "year"
        int year = jsonObject.getInt("year");

        // instantiates a new Expense
        Expense expense = new Expense(amount, name, day, month, year);

        listOfExpense.addExpense(expense);
    }
}
