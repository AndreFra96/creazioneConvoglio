package creazioneConvoglio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestRunner {
    public static void main(String[] args) {
        ArrayList<Rotabile> rotabili = new ArrayList<>();
        HashMap<String, Rotabile> memory = new HashMap<>();

        try (Scanner s = new Scanner(new FileInputStream(new File("inventario.txt")))) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                Rotabile newRotabile;
                if (line.split(" ")[0].equals("V")) {
                    newRotabile = Vagone.parseVagone(line);
                    System.out.println(newRotabile);
                    memory.put(newRotabile.modello, newRotabile);
                } else if (line.split(" ")[0].equals("L")) {
                    newRotabile = Locomotore.parseLocomotore(line);
                    System.out.println(newRotabile);
                    memory.put(newRotabile.modello, newRotabile);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        for (int i = 1; i < args.length; i++) {
            rotabili.add(memory.get(args[i]));
        }

        Convoglio c = new Convoglio(args[0], rotabili);

        System.out.println(c);

    }
}
