package controller;

import model.Ereignis;
import model.Kategorie;
import parser.TSVParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Aufgabe1Controller {


    private final TSVParser parser;
    private List<Ereignis> data;

    private List<String> result;


    public Aufgabe1Controller( TSVParser parser) {
        this.parser = parser;
        this.data = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    public void parseResults(String filePath) throws IOException {
        this.data =  parser.parse(filePath);
    }

    public List<Ereignis> getData() {
        return this.data;
    }


    public void printData() {
        for (Ereignis result : this.data) {
            System.out.println(result.toString());
        }
    }


    public List<String> filterHeldenNachEinfluss(double schwellenwert) {
        Set<String> einzigartigeHelden = new LinkedHashSet<>();

        for (Ereignis e : this.data) {
            if (e.getGlobalerEinfluss() > schwellenwert) {
                einzigartigeHelden.add(e.getHeld());
            }
        }

        return new ArrayList<>(einzigartigeHelden);
    }


    public List<String> filterGalaktischeKonfrontationen() {
        return this.data.stream()
                .filter(e -> e.getKonfrontationstyp().toString().equals("Galaktisch")) // Filtere nur "Galaktisch"
                .sorted(Comparator.comparing(Ereignis::getDatum).reversed()) // Sortiere nach Datum absteigend
                .map(e -> String.format("%s: %s vs. %s - %s", e.getDatum(), e.getHeld(), e.getAntagonist(), e.getOrt())) // Format
                .collect(Collectors.toList());
    }


    public void speichernBericht(String dateiName) {
        // Gruppierung nach Konfrontationstyp
        Map<String, List<Ereignis>> gruppiert = this.data.stream()
                .collect(Collectors.groupingBy(Ereignis::getKonfrontationstyp));

        List<String> ergebnis = gruppiert.entrySet().stream()
                .map(entry -> {
                    String typ = entry.getKey();
                    int anzahl = entry.getValue().size();
                    double gesamtImpact = entry.getValue().stream()
                            .mapToDouble(Ereignis::getGlobalerEinfluss)
                            .sum();
                    return String.format("%s&%d$%.2f", typ, anzahl, gesamtImpact);
                })
                // Sortierung: Absteigend nach Anzahl der Konfrontationen
                // Falls Gleichstand, aufsteigend nach GesamtImpact
                .sorted(Comparator.comparing((String s) -> Integer.parseInt(s.split("&")[1].split("\\$")[0])).reversed()
                        .thenComparing(s -> Double.parseDouble(s.split("\\$")[1].replace(",", "."))))
                .toList();

        // Schreiben in die Datei
        try (FileWriter writer = new FileWriter(dateiName)) {
            for (String line : ergebnis) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
