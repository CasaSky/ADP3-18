package bbaum;

import common.SchluesselWertPaar;

import java.util.ArrayList;
import java.util.List;


/**
 * Basisklasse für einen B-Baum von (sortierbaren) Elementen vom Typ T.
 */
public abstract class BBaum<T extends Comparable<T>, U> {
    /**
     * Wurzelknoten des BBaum
     */
    protected BBaumKnoten<T, U> wurzel = null;

    /**
     * Ordnung des Baumens: Anzahl der Schlüssel liegt zwischen ordnung-1 und 2*ordnung-1.
     */
    protected int ordnung;

    public BBaum(int ordnung) {
        this.ordnung = ordnung;
    }

    /**
     * Validiert den Baum (und alle) seine Knoten. Liefert wahr, wenn die Datenstruktur valide ist ansonsten falsch.
     */
    public boolean validieren() {
        return wurzel.validieren(ordnung);
    }

    @Override
    public String toString() {
        return wurzel.toString();
    }

    /**
     * Liefert den Wert zu einem Schlüssel im Baum. Liefert null, falls der Schlüssel nicht gefunden werden kann.
     *
     * @param schluessel Schlüssel nach dem gesucht wird
     */
    public U findeWertZuSchluessel(T schluessel) {
        if (wurzel == null) {
            return null;
        }
        return findeWertZuSchluessel(schluessel, wurzel);
    }

    /**
     * Liefert den Wert zu einem Schlüssel im Baum. Liefert null, falls der Schlüssel nicht gefunden werden kann.
     *
     * @param schluessel Schlüssel nach dem gesucht wird.
     * @param knoten     Knoten der (rekursiv) durchsucht wird.
     */
    private U findeWertZuSchluessel(T schluessel, BBaumKnoten<T, U> knoten) {
        if (knoten == null) {
            return null;
        }
        for (int i = 0; i < knoten.getAnzahlSchluesselWertPaare(); i++) {
            SchluesselWertPaar<T, U> schluesselWertPaar = knoten.getSchluesselWertPaar(i);
            if (schluesselWertPaar.getSchluessel().compareTo(schluessel) == 0) {
                return schluesselWertPaar.getWert();
            } else if (schluesselWertPaar.getSchluessel().compareTo(schluessel) > 0) {
                return findeWertZuSchluessel(schluessel, knoten.getKind(i));
            }
        }
        return findeWertZuSchluessel(schluessel, knoten.getKind(knoten.getAnzahlKinder() - 1));
    }

    /**
     * Liefert die Anzahl der Schlüssel im Baum.
     */
    public int getAnzahlSchluessel() {
        return getAnzahlSchluessel(wurzel);
    }

    /**
     * Liefert die Anzahl der Schlüssel im Knoten und rekursiv in seinen Kindknoten.
     */
    private int getAnzahlSchluessel(BBaumKnoten<T, U> knoten) {
        if (knoten == null) {
            return 0;
        }
        int anzahlSchluessel = knoten.getAnzahlSchluesselWertPaare();
        for (int i = 0; i < knoten.getAnzahlKinder(); i++) {
            anzahlSchluessel += getAnzahlSchluessel(knoten.getKind(i));
        }
        return anzahlSchluessel;
    }

    /**
     * Liefer die Element in der Pre-order Reihenfolge.
     *
     * @return
     */
    public List<SchluesselWertPaar<T, U>> getPreOrder() {
        return getPreOrder(wurzel);
    }

    /**
     * Liefert die Elemente in der Pre-order Reihenfolge.
     */
    public List<SchluesselWertPaar<T, U>> getPreOrder(BBaumKnoten<T, U> knoten) {
        if (knoten == null) {
            return new ArrayList<>();
        }
        List<SchluesselWertPaar<T, U>> preOrderElemente = new ArrayList<>();
        for (int i = 0; i < knoten.getAnzahlSchluesselWertPaare(); i++) {
            preOrderElemente.addAll(getPreOrder(knoten.getKind(i)));
            preOrderElemente.add(knoten.getSchluesselWertPaar(i));
        }
        preOrderElemente.addAll(getPreOrder(knoten.getKind(knoten.getAnzahlSchluesselWertPaare())));
        return preOrderElemente;
    }

    /**
     * Leert den gesamten Baum.
     */
    public void leeren() {
        wurzel = null;
    }

    /**
     * Fügt das Element in den Knoten ein. Liefert den neuen Wurzelknoten des Baumes zurück.
     *
     * @param schluessel Schlüssel des Elementes, das in den aktuellen Knoten eingefügt werden soll
     * @param wert       Wert des Elementes.
     */
    public abstract void einfuegen(T schluessel, U wert);
}
