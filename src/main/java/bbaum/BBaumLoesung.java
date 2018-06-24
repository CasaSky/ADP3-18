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
            wurzel.addSchluesselWertPaar(suchePassenderIndex(wurzel, paar.getSchluessel()), paar);
            wurzel = limitPruefungUndSplit(wurzel);
        } else {
            int kindIndex = suchePassendesKind(wurzel, paar.getSchluessel());
            BBaumKnoten kind = wurzel.getKind(kindIndex);
            kind = rekursivesEinfuegen(kind, paar);
            // Loest Stackoverflow -_-
            if (kind.equals(wurzel)) {
                wurzel = kind;
            } else {
                wurzel.setKind(kindIndex, kind);
            }
        }
    }

    private BBaumKnoten rekursivesEinfuegen(BBaumKnoten knoten, SchluesselWertPaar paar) {

        if (knoten == null) {
            return new BBaumKnoten(paar);
        } else if (knoten.istBlattknoten()) {
            int index = suchePassenderIndex(knoten, paar.getSchluessel());
            knoten.addSchluesselWertPaar(index, paar);
            return limitPruefungUndSplit(knoten);
        } else {
            int kindIndex = suchePassendesKind(knoten, paar.getSchluessel());
            BBaumKnoten kind = knoten.getKind(kindIndex);
            return rekursivesEinfuegen(kind, paar);
        }
    }

    private boolean isLimitReached(BBaumKnoten knoten) {
        return knoten.getAnzahlSchluesselWertPaare() > ordnung * 2 - 1;
    }

    private BBaumKnoten limitPruefungUndSplit(BBaumKnoten knoten) {
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
                //elternknoten.schluesselUndKindEinfuegen(linkerKnoten, teiler, rechterKnoten);
                elternknoten.addSchluesselWertPaar(suchePassenderIndex(elternknoten, teiler.getSchluessel()), teiler);
                elternknoten.setKind(0, linkerKnoten);
                elternknoten.setKind(1, rechterKnoten);
                return limitPruefungUndSplit(elternknoten);
            }
        }
        return knoten;
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

    private int suchePassenderIndex(BBaumKnoten knoten, Comparable schluessel) {
        int anzahlSchluesselPaare = knoten.getAnzahlSchluesselWertPaare();

        if (schluessel.compareTo(knoten.getSchluesselWertPaar(0).getSchluessel()) < 0) {
            return 0;
        } else if (anzahlSchluesselPaare == 1) {
            return 1;
        } else {
            for (int i=0; i<anzahlSchluesselPaare-1; i++) {
                if (schluessel.compareTo(knoten.getSchluesselWertPaar(i).getSchluessel()) > 0
                        && schluessel.compareTo(knoten.getSchluesselWertPaar(i+1).getSchluessel()) < 0) {
                    return i+1;
                }
            }
        }
        return -1;
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
