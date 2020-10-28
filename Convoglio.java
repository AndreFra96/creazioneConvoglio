package creazioneConvoglio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Convoglio {
    String name;
    ArrayList<Rotabile> convoglio;

    public Convoglio(String name, ArrayList<Rotabile> convoglio) {
        this.name = Objects.requireNonNull(name);
        this.convoglio = Objects.requireNonNull(convoglio);
    }

    public int peso() {
        int peso = 0;
        for (Rotabile actual : convoglio) {
            peso += actual.peso;
        }
        return peso;
    }

    public Locomotore testa() {
        return (Locomotore) convoglio.get(0);
    }

    public Locomotore coda() {
        if (!(convoglio.get(convoglio.size() - 1).isLocomotore()))
            return null;

        return (Locomotore) convoglio.get(convoglio.size() - 1);
    }

    public boolean isValid() {
        return this.potenza() > this.peso();
    }

    public TreeMap<String, Dotazione> dotazione() {
        TreeMap<String, Dotazione> returnMap = new TreeMap<>();
        for (int i = 1; i < convoglio.size(); i++) {
            if (convoglio.get(i) instanceof Vagone) {
                Vagone temp = (Vagone) convoglio.get(i);
                returnMap = sommaDotazione(returnMap, temp.dotazioni);
            }
        }
        return returnMap;

    }

    private TreeMap<String, Dotazione> sommaDotazione(TreeMap<String, Dotazione> dotazioniAttuali,
            TreeSet<Dotazione> nuoveDotazioni) {
        for (Dotazione actual : nuoveDotazioni) {
            if (dotazioniAttuali.containsKey(actual.getName())) {
                Dotazione temp = dotazioniAttuali.get(actual.getName());
                dotazioniAttuali.remove(temp.getName());
                dotazioniAttuali.put(temp.getName(),
                        new Dotazione(temp.getName(), temp.getQuantity() + actual.getQuantity()));
            } else {
                dotazioniAttuali.put(actual.getName(), actual);
            }
        }
        return dotazioniAttuali;
    }

    public int potenza() {
        int potenza = this.testa().potenza;
        if (this.coda() != null)
            potenza += this.coda().potenza / 2;
        return potenza;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        TreeMap<String, Dotazione> dotazione = dotazione();

        stringBuilder.append(
                "Convoglio: nome = " + this.name + ", peso = " + this.peso() + ", potenza = " + this.potenza() + " [");
        boolean first = true;
        Iterator<Entry<String, Dotazione>> dotIterator = dotazione.entrySet().iterator();
        while (dotIterator.hasNext()) {
            if (first) {
                stringBuilder.append(dotIterator.next().getValue());
                first = false;
            } else {
                stringBuilder.append(", " + dotIterator.next().getValue());
            }
        }

        if (this.isValid()) {
            stringBuilder.append("], valido");
        } else {
            stringBuilder.append(", non valido");
        }

        return stringBuilder.toString();
    }

}
