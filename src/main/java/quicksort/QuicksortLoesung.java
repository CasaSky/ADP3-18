package quicksort;

import common.SchluesselWertPaar;

public class QuicksortLoesung<T extends Comparable<T>, U> extends Quicksort {


    /**
     * Konstrucktor.
     *
     * @param pivotStrategy
     */
    public QuicksortLoesung(PivotStrategie pivotStrategy) {
        super(pivotStrategy);
    }

    @Override
    public void sortiere(SchluesselWertPaar[] a) {

    }
}
