import controller.Aufgabe1Controller;

import parser.TSVParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        aufgabe1();


    }


    public static TSVParser tsvParser = TSVParser.getInstance();
    public static Aufgabe1Controller controller = new Aufgabe1Controller(tsvParser);

    public static void punktA() {
        //a) lesen von der Datei und auf dem Bildschirm zeigen

        System.out.println("\nPunkt a.\n");
        String filePath = "marvel_konfrontationen.tsv";

        try {
            controller.parseResults(filePath);
        } catch (IOException e) {
            System.out.println("A apărut o eroare la citirea fișierului  " + e.getMessage());
        }

        controller.printData();
    }


    public static void punktB() {
        //b)
        System.out.println("\nPunkt b.\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gib eine Wert: ");
        Double num = Double.parseDouble(scanner.nextLine());
        List<String> result = controller.filterHeldenNachEinfluss(num);

        for (String str : result) {
            System.out.println(str);
        }
    }

    public static void punktC() {
        //c)
        System.out.println("\nPunkt c.\n");

        List<String> result = controller.filterGalaktischeKonfrontationen();

        for (String str : result) {
            System.out.println(str);
        }

    }

    public static void punktD() {
        //d)
        System.out.println("\nPunkt d.\n");
        String fileName = "ergebnis.txt";
        controller.speichernBericht(fileName);
    }

    public static void aufgabe1() {
        punktA();
        punktB();
        punktC();
        punktD();
    }
}
