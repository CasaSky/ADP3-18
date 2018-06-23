package com.company;

/**
 * Created by michelbrueger on 21.06.18.
 */
public class QuicksortLoesung<T extends Comparable<T>, U> extends Quicksort {


    /**
     * Konstruktor
     * @param pivotStrategy
     */
    public QuicksortLoesung(PivotStrategie pivotStrategy) {
//        this.pivotStrategy = pivotStrategy;
        super(pivotStrategy);
    }

    public PivotStrategie getPivotStrategy(){
        return super.pivotStrategy;
    }

    /**
     * Sortiert das Array a in-situ
     * @param a Array mit Schlüssel-Wert-Paaren, die nach den Schlüsseln aufsteigend sortiert werden sollen.
     */
    @Override
    public void sortiere(SchluesselWertPaar[] a) {
        _sortiere(a, 0, a.length-1);
    }

    /**
     * Hilfsfunktion für rekursion in sortiere(SchluesselWertPaar[] a)
     * @param a Array mit Schlüssel-Wert-Paaren, die nach den Schlüsseln aufsteigend sortiert werden sollen.
     * @param links Index ab dem sortiert werden soll
     * @param rechts Index bis zu dem sortiert werden soll
     */
    private void _sortiere(SchluesselWertPaar[] a, int links, int rechts) {
        if(a.length-1 < links || a.length-1 < rechts) throw new IllegalArgumentException("Beide Indizes müssen innerhalb des Arrays liegen (_sortiere())");
        if(links > rechts) throw new IllegalArgumentException("Index rechts muss größer sein als Index links.");
        if(a.length <= 1) return;
        int pivotIndex = pivotStrategy.getIndex(a, links, rechts);
//        T pivotKey = (T) a[pivotIndex].getSchluessel();
//        if(a.length <= 1){ return; }

        while(links < rechts){
            T pivotKey = (T) a[pivotIndex].getSchluessel();                             // Nochmal drüber nachdenken wo man das plazieren könnte
            while(a[links].getSchluessel().compareTo(pivotKey) < 0) links += 1;
            while(a[rechts].getSchluessel().compareTo(pivotKey) > 0) rechts += 1;
            if(links<rechts) swap(a,links,rechts);
        }
        if(a[links].getSchluessel().compareTo(a[pivotIndex].getSchluessel()) > 0) swap(a, links, pivotIndex);
        _sortiere(a,links,pivotIndex);
        _sortiere(a,pivotIndex+1, a.length-1);
    }

    /**
     * 2 Elemente eines Arrays wechseln ihre Positionen (Indizes)
     * @param a Array welches an genannten Indizes Elemente enthält
     * @param links Index von Element1
     * @param rechts Index von Element2
     */
    private void swap(SchluesselWertPaar[] a, int links, int rechts) {
        if(a.length-1 < links || a.length-1 < rechts) throw new IllegalArgumentException("Beide Indizes müssen innerhalb des Arrays liegen (swap())");
        SchluesselWertPaar temp = a[links];
        a[links] = a[rechts];
        a[rechts] = temp;
    }
}
