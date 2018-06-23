package bbaum;

import common.SchluesselWertPaar;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentation eines Knotens in einem B-Baum. Die Elemente, die im Knoten als Schlüssel repräsentiert werden,
 * müssen sortierbar sein.
 */
public class BBaumKnoten<T extends Comparable<T>, U> {

    /**
     * Array mit den Schlüsseln (t-1 bis 2t-1)
     */
    private List<SchluesselWertPaar<T, U>> schluesseWertPaare = new ArrayList<>();

    /**
     * Array mit den Referenzen auf Kindknoten (t bis 2*t)
     */
    private List<BBaumKnoten<T, U>> kinder;

    /**
     * Referenz auf den Elternknoten, null beim Wurzelknoten
     */
    private BBaumKnoten<T, U> elternknoten = null;


    public BBaumKnoten(SchluesselWertPaar<T, U> element) {
        schluesseWertPaare = new ArrayList<SchluesselWertPaar<T, U>>();
        schluesseWertPaare.add(element);
        kinder = new ArrayList<>();
        kinder.add(null);
        kinder.add(null);
    }

    /**
     * Liefert wahr, wenn der Knoten mindestens einen nicht-null Kindknoten hat, sonst wahr.
     */
    public boolean istBlattknoten() {
        for (BBaumKnoten<T, U> kind : kinder) {
            if (kind != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Liefert die Anzahl der Schlüssel im Knoten.
     */
    public int getAnzahlSchluesselWertPaare() {
        return schluesseWertPaare.size();
    }

    /**
     * Fügt ein Element als Schlüssel an dem gegebenen Index ein (vor dem Schlüssel, der bisher an der Stelle
     * stand). Fügt außerdem automatisch eine null-Kindknoten-Referenz ein.
     */
    public void addSchluesselWertPaar(int index, SchluesselWertPaar<T, U> element) {
        if (schluesseWertPaare.size() == 0) {
            kinder.add(null);
        }
        schluesseWertPaare.add(index, element);
        kinder.add(index, null);
    }

    /**
     * Liefert den Schlüssel an dem gegebenen Index.
     */
    public SchluesselWertPaar<T, U> getSchluesselWertPaar(int index) {
        return schluesseWertPaare.get(index);
    }

    /**
     * Liefert den Elternknoten des Knotens.
     */
    public BBaumKnoten<T, U> getElternknoten() {
        return elternknoten;
    }

    /**
     * Liefert das Kind am gegebenen Index.
     */
    public BBaumKnoten<T, U> getKind(int index) {
        return kinder.get(index);
    }

    /**
     * Fügt einen neuen Schlüssel ein, setzt den Kindknoten links des Schlüssels und fügt einen neuen Kindknoten
     * rechts des Schlüssels ein.
     */
    public void schluesselUndKindEinfuegen(BBaumKnoten<T, U> linkerKnoten, SchluesselWertPaar<T, U> teiler,
                                           BBaumKnoten<T, U> rechterKnoten) {
        // Finde index des Teiler
        int index = -1;
        if (teiler.getSchluessel().compareTo(getSchluesselWertPaar(0).getSchluessel()) < 0) {
            index = 0;
        } else if (teiler.getSchluessel().compareTo(getSchluesselWertPaar(getAnzahlSchluesselWertPaare() - 1).
                getSchluessel()) > 0) {
            index = getAnzahlSchluesselWertPaare() - 1;
        } else {
            for (int i = 0; i < getAnzahlSchluesselWertPaare() - 1; i++) {
                if (teiler.getSchluessel().compareTo(getSchluesselWertPaar(i + 1).getSchluessel()) < 0) {
                    index = i + 1;
                    break;
                }
            }
        }
        addSchluesselWertPaar(index, teiler);
        setKind(index, linkerKnoten);
        setKind(index + 1, rechterKnoten);
    }

    /**
     * Ersetzt den Kindknoten am gegebenen Index. Index muss bereits gültig sein, kein Hinzufügen.
     */
    public void setKind(int index, BBaumKnoten<T, U> knoten) {
        kinder.set(index, knoten);
        if (knoten != null) {
            knoten.setElternKnoten(this);
        }
    }

    /**
     * Setzt den Elternknoten.
     */
    private void setElternKnoten(BBaumKnoten<T, U> knoten) {
        elternknoten = knoten;
    }

    /**
     * Liefert die Anzahl der Kindknoten
     */
    public int getAnzahlKinder() {
        return kinder.size();
    }

    /**
     * Validiert den Knoten (Konsistenzprüfung). Liefert wahr, wenn der Knoten valide ist.
     */
    public boolean validieren(int ordnung) {
        // Schlüssel
        if (schluesseWertPaare.size() < ordnung - 1 || schluesseWertPaare.size() > ordnung * 2 - 1) {
            System.out.println("Validierung fehlgeschlagen: ungültige Schlüsselanzahl");
            return false;
        }

        // Kinder
        if (kinder.size() != schluesseWertPaare.size() + 1) {
            System.out.println("Validierung fehlgeschlagen: ungültige Kindknotenanzahl");
            return false;
        }

        // Schlüsselreihenfolge
        for (int i = 0; i < schluesseWertPaare.size() - 1; i++) {
            if (schluesseWertPaare.get(i).getSchluessel().compareTo(schluesseWertPaare.get(i + 1).getSchluessel()) > 0) {
                System.out.println("Validierung fehlgeschlagen: Schlüsselreihenfolge falsch");
                return false;
            }
        }

        // Position der Kinder
        if (schluesseWertPaare.size() > 0) {
            if (kinder.get(0) != null) {
                // Größter Schlüssel des ersten Kindes muss kleiner als erster Schlüssel sein.
                if (kinder.get(0).getSchluesselWertPaar(kinder.get(0).getAnzahlSchluesselWertPaare() - 1).getSchluessel().
                        compareTo(schluesseWertPaare.get(0).getSchluessel()) > 0) {
                    System.out.println("Kind " + 0 + " passt nicht in den Schlüsselbereich.");
                    return false;
                }
            }
            if (kinder.get(kinder.size() - 1) != null) {
                // Kleinster Schlüssel des letzten Kindes muss größer sein als letzter Schlüssely
                if (kinder.get(kinder.size() - 1).getSchluesselWertPaar(0).getSchluessel().
                        compareTo(schluesseWertPaare.get(schluesseWertPaare.size() - 1).getSchluessel()) < 0) {
                    System.out.println("Kind " + (kinder.size() - 1) + " passt nicht in den Schlüsselbereich.");
                    return false;
                }
            }
            for (int i = 0; i < schluesseWertPaare.size() - 1; i++) {
                T schluesselLinks = schluesseWertPaare.get(i).getSchluessel();
                T schluesselRechts = schluesseWertPaare.get(i + 1).getSchluessel();
                if (kinder.get(i) != null) {
                    T kindSchluesselMin = kinder.get(i + 1).getSchluesselWertPaar(0).getSchluessel();
                    T kindSchluesselMax = kinder.get(i + 1).getSchluesselWertPaar(kinder.get(i).getAnzahlSchluesselWertPaare() - 1).
                            getSchluessel();
                    if (kindSchluesselMin.compareTo(schluesselLinks) < 0) {
                        System.out.println("Kind " + i + " passt nicht in den Schlüsselbereich.");
                        return false;
                    }
                    if (kindSchluesselMax.compareTo(schluesselRechts) > 0) {
                        System.out.println("Kind " + i + " passt nicht in den Schlüsselbereich.");
                        return false;
                    }
                }
            }
        }

        // Prüfe Kindknoten
        for (BBaumKnoten<T, U> kind : kinder) {
            if (kind != null) {
                if (!kind.validieren(ordnung)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        String ergebnis = "(" + ((kinder.get(0) != null) ? " " + kinder.get(0) + " " : "") + ")";
        for (int i = 0; i < schluesseWertPaare.size(); i++) {
            ergebnis += schluesseWertPaare.get(i).getSchluessel() + "";
            ergebnis += "(" + ((kinder.get(i + 1) != null) ? " " + kinder.get(i + 1) + " " : "") + ")";
        }
        ergebnis += "";
        return ergebnis;
    }
}

