package creazioneConvoglio;

import java.util.TreeSet;
import java.util.Objects;

public class Vagone extends Rotabile {
    final TreeSet<Dotazione> dotazioni;

    public Vagone(String modello, int peso, TreeSet<Dotazione> dotazioni) {
        super(modello, peso);
        this.dotazioni = Objects.requireNonNull(dotazioni);
    }

    public void addDotazione(Dotazione newDotazione) {
        if (dotazioni.contains(newDotazione)) {
            for (Dotazione search : dotazioni) {
                if (search.equals(newDotazione)) {
                    dotazioni.remove(search);
                    newDotazione = new Dotazione(search.getName(), search.getQuantity() + newDotazione.getQuantity());
                    dotazioni.add(newDotazione);
                    return;
                }
            }
        }
        dotazioni.add(newDotazione);
    }

    /**
     * <p>
     * Post-condizioni: restituisce un nuovo Vagone corrispondente alla stringa in
     * input (formato stringa: Nome Peso Dotazione Dotazione ...)
     * <p>
     * throws IllegalArgumentException se la stringa non corrisponde ad un vagone
     * valido
     * <p>
     * throws NumberFormatException se il peso è nel formato sbagliato
     */
    public static Vagone parseVagone(String s) {

        String[] vagone = s.split(" ");
        Vagone returnVagone;
        TreeSet<Dotazione> dotazione = new TreeSet<Dotazione>();

        if (vagone.length < 3 || (vagone.length % 2) == 0)
            throw new IllegalArgumentException("La stringa non è nel formato corretto");

        try {
            returnVagone = new Vagone(vagone[1], Integer.parseInt(vagone[2]), dotazione);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Il peso non è nel formato corretto");
        }

        for (int i = 3; i < vagone.length - 1; i += 2) {
            Dotazione newDotazione = Dotazione.parseDotazione(vagone[i] + " " + vagone[i + 1]);
            returnVagone.addDotazione(newDotazione);
        }

        return returnVagone;

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vagone: modello = " + this.modello + ", peso = " + this.peso + ", dotazioni: [");
        boolean first = true;
        for (Dotazione actual : dotazioni) {
            if (first) {
                stringBuilder.append(actual.toString());
                first = false;
            } else {
                stringBuilder.append(", " + actual.toString());
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean isLocomotore() {
        return false;
    }

    @Override
    public boolean isRotabile() {
        return true;
    }
}
