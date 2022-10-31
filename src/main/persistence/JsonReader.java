package persistence;

import model.Expense;
import model.ListOfExpense;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfExpense read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfExpense(jsonObject);
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
    private ListOfExpense parseListOfExpense(JSONObject jsonObject) {
//      String name = jsonObject.getString("name");
        ListOfExpense listOfExpense = new ListOfExpense();
        addExpenses(listOfExpense, jsonObject);
        return listOfExpense;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addExpenses(ListOfExpense listOfExpense, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allExpense");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addExpense(listOfExpense, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addExpense(ListOfExpense listOfExpense, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String name = jsonObject.getString("name");
        int day = jsonObject.getInt("day");
        int month = jsonObject.getInt("month");
        int year = jsonObject.getInt("year");

        Expense expense = new Expense(amount, name, day, month, year);

        listOfExpense.addExpense(expense);
    }




}
