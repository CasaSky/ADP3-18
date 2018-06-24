package bbaum;

import common.SchluesselWertPaar;

public class BBaumLoesung<T extends Comparable<T>, U> extends BBaum {

    public BBaumLoesung(int ordnung) {
        super(ordnung);
    }

    @Override
    public void einfuegen(Comparable schluessel, Object wert) {
        SchluesselWertPaar paar = new SchluesselWertPaar(schluessel, wert);

        if (wurzel==null) {
            wurzel = new BBaumKnoten(paar);
        } else if (wurzel.istBlattknoten()) {
            BBaumKnoten kind = new BBaumKnoten(paar);
            int index = schluessel.compareTo(wurzel.getSchluesselWertPaar(0).getSchluessel()) < 0 ? 0 : 1; // egal ob der wert bereits vorhanden
            wurzel.setKind(index, kind);
        } else {
            int kindIndex = suchePassendesKind(wurzel, paar.getSchluessel());
            BBaumKnoten kind = wurzel.getKind(kindIndex);
            kind = rekursivesEinfuegen(kind, paar);
            wurzel.setKind(kindIndex, kind);
        }
    }

    private BBaumKnoten rekursivesEinfuegen(BBaumKnoten knoten, SchluesselWertPaar paar) {

        if (knoten == null) {
            return new BBaumKnoten(paar);
        } else if (knoten.istBlattknoten()) {
            //TODO die genaue Position ermitteln
            int index = suchePassenderIndex(knoten, paar);
            knoten.addSchluesselWertPaar(index, paar);
            return rekursiveLimitAndSplitPruefung(knoten);
        } else {
            int kindIndex = suchePassendesKind(knoten, paar.getSchluessel());
            BBaumKnoten kind = knoten.getKind(kindIndex);
            return rekursivesEinfuegen(kind, paar);
        }
    }

    private int suchePassenderIndex(BBaumKnoten knoten, SchluesselWertPaar paar) {
        int anzahlSchluesselPaare = knoten.getAnzahlSchluesselWertPaare()-1;
        if (paar.getSchluessel().compareTo(knoten.getSchluesselWertPaar(0).getSchluessel()) < 0) {
            return 0;
        }
        for (int i=1; i<knoten.getAnzahlSchluesselWertPaare()-1; i++) {
            // Laesst keine Duplikate zu
            if (paar.getSchluessel().compareTo(knoten.getSchluesselWertPaar(i-1).getSchluessel()) > 0
                    && paar.getSchluessel().compareTo(knoten.getSchluesselWertPaar(i).getSchluessel()) < 0) {
                return i;
            }
        }
        return anzahlSchluesselPaare+1;
    }

    private int suchePassendesKind(BBaumKnoten knoten, Comparable schluessel) {
        for (int i = 0; i < knoten.getAnzahlSchluesselWertPaare(); i++) {
            SchluesselWertPaar paar = knoten.getSchluesselWertPaar(i);
            if (schluessel.compareTo(paar.getSchluessel()) < 0) {
                return i;
            }
        }
        return knoten.getAnzahlKinder()-1; //Default falls schluessel der Groesste ist
    }

    private BBaumKnoten rekursiveLimitAndSplitPruefung(BBaumKnoten knoten) {
        if (isLimitReached(knoten)) {
            int mid = knoten.getAnzahlSchluesselWertPaare()/2;
            SchluesselWertPaar teiler = knoten.getSchluesselWertPaar(mid);

            BBaumKnoten linkerKnoten = new BBaumKnoten(knoten.getSchluesselWertPaar(0));
            for (int i = 1; i < mid; i++) {
                linkerKnoten.addSchluesselWertPaar(i, knoten.getSchluesselWertPaar(i));
            }
            BBaumKnoten rechterKnoten = new BBaumKnoten(knoten.getSchluesselWertPaar(mid + 1));
            int j = 1;
            for (int i = mid + 2; i < knoten.getAnzahlSchluesselWertPaare() - 1; i++) {
                rechterKnoten.addSchluesselWertPaar(j, knoten.getSchluesselWertPaar(i));
                j++;
            }

            BBaumKnoten elternknoten = knoten.getElternknoten();
            if (elternknoten == null) {
                elternknoten = new BBaumKnoten(teiler);
                elternknoten.setKind(0, linkerKnoten);
                elternknoten.setKind(1, rechterKnoten);
                return elternknoten;
            } else {
                elternknoten.schluesselUndKindEinfuegen(linkerKnoten, teiler, rechterKnoten);
                return rekursiveLimitAndSplitPruefung(elternknoten);
            }
        }
        return knoten;
    }

    private boolean isLimitReached(BBaumKnoten knoten) {
        return knoten.getAnzahlSchluesselWertPaare() > ordnung * 2 - 1;
    }







    //else {
    //               List<SchluesselWertPaar> knotenPreOrder = getPreOrder(knoten);
    //               BBaum bBaum = new BBaumLoesung(2);
    //
    //               for (SchluesselWertPaar paar : knotenPreOrder) {
    //                   bBaum.einfuegen(paar.getSchluessel(), paar.getWert());
    //               }
    //               bBaum.einfuegen(schluessel, wert);
    //
    //               List<SchluesselWertPaar> rootPreOrder = getPreOrder();
    //               for (SchluesselWertPaar paar : rootPreOrder) {
    //                   einfuegen(paar.getSchluessel(), paar.getWert());
    //               }
}
