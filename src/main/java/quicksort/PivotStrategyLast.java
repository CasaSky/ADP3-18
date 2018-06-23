package quicksort;

import common.SchluesselWertPaar;

/**
 * Created by michelbrueger on 21.06.18.
 */
public class PivotStrategyLast implements PivotStrategie {
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        return iRechts;
    }
}
