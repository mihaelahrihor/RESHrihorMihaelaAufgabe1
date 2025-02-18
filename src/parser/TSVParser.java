package parser;

import model.Ereignis;
import model.Kategorie;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public class TSVParser {


    private static TSVParser instance;

    private TSVParser() {
    }

    public static TSVParser getInstance() {
        if (instance == null) {
            instance = new TSVParser();
        }
        return instance;
    }

    /**
     * Parse the TSV file to a list of Results.
     *
     * @param path the path of the TSV file to be parsed
     * @return List of Result objects
     * @throws IOException if an I/O error occurs
     */
    public List<Ereignis> parse(String path) throws IOException {
        Path filePath = Path.of(path);

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String header = reader.readLine();  // Read header
            String[] fields = header.split("\t");  // Split header by tab
            return reader.lines().map(parseTSVLine(fields)).toList();  // Process each line of TSV
        }
    }

    /**
     * Parses a single line from the TSV file into a {@link Ereignis} object.
     *
     * @param fields the fields previously read from the header
     * @return a function that parses each line and returns a Result object
     */
    private static Function<String, Ereignis> parseTSVLine(String[] fields) {
        return line -> {
            String[] values = line.split("\t");

            Ereignis data = new Ereignis();

            for (int i = 0; i < fields.length; i++) {
                String value = values[i].replace("\"", "").trim();  // Clean up value

                switch (fields[i]) {
                    case "Id": {
                        data.setId(Integer.parseInt(value));  // Convert ID to integer
                        break;
                    }
                    case "Held": {
                        data.setHeld(value);  // Set studierende (student name)
                        break;
                    }
                    case "Antagonist": {
                        data.setAntagonist(value);  // Convert haus (house) to Haus enum
                        break;
                    }
                    case "Konfrontationstyp": {
                        data.setKonfrontationstyp(Kategorie.valueOf(value));  // Set autoritat (authority)
                        break;
                    }
                    case "Ort": {
                        data.setOrt(value);  // Convert punkte (points) to integer
                        break;
                    }
                    case "Datum": {
                        data.setDatum(LocalDate.parse(value));  // Convert punkte (points) to integer
                        break;
                    }
                    case "GlobalerEinfluss": {
                        data.setGlobalerEinfluss(Double.parseDouble(value));  // Convert punkte (points) to integer
                        break;
                    }
                }
            }
            return data;  // Return the populated Result object
        };
    }
}
